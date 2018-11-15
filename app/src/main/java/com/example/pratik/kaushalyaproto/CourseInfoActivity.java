package com.example.pratik.kaushalyaproto;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pratik.kaushalyaproto.Classes.Course;
import com.example.pratik.kaushalyaproto.Classes.Mycourse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CourseInfoActivity extends AppCompatActivity {

    Course course;
    TextView coursenameText;
    ImageView courseImage;
    Button enrollButton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        course = (Course) getIntent().getSerializableExtra("object");
        Toast.makeText(this, course.getId() + " " + course.getName() + " " + course.getCategory(), Toast.LENGTH_SHORT).show();

        coursenameText = findViewById(R.id.coursenametext);
        courseImage = findViewById(R.id.courseimage);
        enrollButton = findViewById(R.id.btn_enroll);

        coursenameText.setText(course.getName());

        Uri uri = Uri.parse(course.getImageurl());
        Picasso.get()
                .load(uri)
                .into(courseImage);


        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status;
                status = "ongoing";

                Mycourse mycourse = new Mycourse(course.getId(), status);

                db.collection("Users/" + mAuth.getCurrentUser().getUid() + "/" + "mycourses")
                        .add(mycourse)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });


            }
        });

    }
}
