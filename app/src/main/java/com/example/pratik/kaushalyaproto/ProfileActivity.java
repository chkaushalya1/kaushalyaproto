package com.example.pratik.kaushalyaproto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pratik.kaushalyaproto.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText fnameEditText, mnameEditText, lnameEditText, phoneEEditText, emailEditText;
    private Button RegisterButton;
    private String Uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(this, mAuth.getCurrentUser().getUid() + " " + mAuth.getCurrentUser().getEmail() + " " + mAuth.getCurrentUser().getPhoneNumber(), Toast.LENGTH_SHORT).show();

        fnameEditText = findViewById(R.id.fnameEdit);
        mnameEditText = findViewById(R.id.mnameEdit);
        lnameEditText = findViewById(R.id.lnameEdit);
        phoneEEditText = findViewById(R.id.phoneEdit);
        emailEditText = findViewById(R.id.emailEdit);
        RegisterButton = findViewById(R.id.btn_saveandregister);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userdata = new User(fnameEditText.getText().toString(), mnameEditText.getText().toString(), lnameEditText.getText().toString(),
                        emailEditText.getText().toString(), phoneEEditText.getText().toString());

                Map<String, Object> user = new HashMap<>();


                Map<String, Object> phone = new HashMap<>();
                phone.put("mobile", phoneEEditText.getText().toString());
                phone.put("home", "7845222");
                user.put("phone", phone);
                user.put("firstname", fnameEditText.getText().toString());
                user.put("middlename", mnameEditText.getText().toString());
                user.put("lastname", lnameEditText.getText().toString());
                user.put("emailID", emailEditText.getText().toString());
                //user.put("array", Arrays.asList("west_coast", "norcal"));

                db.collection("Users").document(mAuth.getCurrentUser().getUid()).set(user);
                //db.collection("Users").document(mAuth.getCurrentUser().getUid()).set(user);

                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        if (mAuth.getCurrentUser().getEmail() != null)
            emailEditText.setText(mAuth.getCurrentUser().getEmail());
        else if (mAuth.getCurrentUser().getPhoneNumber() != null)
            phoneEEditText.setText(mAuth.getCurrentUser().getPhoneNumber());

    }
}
