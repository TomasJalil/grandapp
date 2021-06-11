package com.example.grandapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText EditEmail,EditPass;
    Button BtnRegistro;
    private FirebaseAuth mAuth;

    private String Password="";
    private String ConfPassword="";
    private String Email="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditPass = findViewById(R.id.Pass);
        EditEmail = findViewById(R.id.email);
        BtnRegistro=findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void IniciaSesion(View view){
        Integer cont=0;
        Password = EditPass.getText().toString().trim();
        Email = EditEmail.getText().toString().trim();
        if (Password.length()==0){
            EditPass.setError("No ingreso su contraseña");

        }else {
            cont++;
        }
        if (Email.length()==0){
            EditEmail.setError("No ingreso su correo electronico");
        }
        else {
            cont++;
        }

        if (cont==2){
            mAuth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Iniciaste sesion",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Menu.class);
                                startActivity(i);
                               finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                               // updateUI(null);
                            }
                        }
                    });
        }


    }


}