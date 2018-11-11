package com.example.pratik.kaushalyaproto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText EmailEditText, PasswordEditText;
    private Button SignupButton, ResetButton, SigninButton;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        EmailEditText = findViewById(R.id.email);
        PasswordEditText = findViewById(R.id.password);

        SignupButton = (Button) findViewById(R.id.sign_up_button);
        SigninButton = findViewById(R.id.sign_in_button);
        ResetButton = findViewById(R.id.btn_reset_password);

        SigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = EmailEditText.getText().toString();
                password = PasswordEditText.getText().toString();
                signin(email, password);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    private void signin(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //FirebaseUser user = mAuth.getCurrentUser();
                            // can use this to get user data

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Tagsignin", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
