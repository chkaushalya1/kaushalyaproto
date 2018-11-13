package com.example.pratik.kaushalyaproto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText fnameEditText, mnameEditText, lnameEditText, phoneEEditText, emailEditText;
    //private TextView UidTextView;
    private String Uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(this, mAuth.getCurrentUser().getUid() + " " + mAuth.getCurrentUser().getEmail() + " " + mAuth.getCurrentUser().getPhoneNumber(), Toast.LENGTH_SHORT).show();

        fnameEditText = findViewById(R.id.fnameEdit);
        mnameEditText = findViewById(R.id.mnameEdit);
        lnameEditText = findViewById(R.id.lnameEdit);
        phoneEEditText = findViewById(R.id.phoneEdit);
        emailEditText = findViewById(R.id.emailEdit);
        //UidTextView = findViewById(R.id.UidText);

        //UidTextView.setText(mAuth.getCurrentUser().getUid());

        if (mAuth.getCurrentUser().getEmail() != null)
            emailEditText.setText(mAuth.getCurrentUser().getEmail());
        else if (mAuth.getCurrentUser().getPhoneNumber() != null)
            phoneEEditText.setText(mAuth.getCurrentUser().getPhoneNumber());


    }
}
