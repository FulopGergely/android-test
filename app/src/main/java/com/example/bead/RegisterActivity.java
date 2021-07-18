package com.example.bead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPasswordConfirm;
    private FirebaseAuth mAuth;
    Button myButton;
    Animation myAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        mAuth = FirebaseAuth.getInstance();
        myAnim = AnimationUtils.loadAnimation(this, R.anim.button);
        myButton = (Button) findViewById(R.id.RegiButton);
        myButton.setAnimation(myAnim);


    }


    public void registration(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String TextPasswordConfirm = editTextPasswordConfirm.getText().toString();
        Log.i(LOG_TAG, "regisztráció");

        if (!password.equals(TextPasswordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            Toast.makeText(RegisterActivity.this, "Nem egyenlő a jelszó és a megerősítése.", Toast.LENGTH_LONG).show();
            return;
        }

        /*
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
            }
        });
        */


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d(LOG_TAG, "User created successfully");
                    startShopping();
                } else {
                    Log.d(LOG_TAG, "User was't created successfully:", task.getException());
                    Toast.makeText(RegisterActivity.this, "User was't created successfully:", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void cancel(View view) {
        finish();
    }

    private void startShopping(/* registered used class */) {
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAnim = AnimationUtils.loadAnimation(this, R.anim.button);
        myButton.startAnimation(myAnim);



    }
}