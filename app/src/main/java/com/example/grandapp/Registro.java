package com.example.grandapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    EditText EditEmail,EditPass,EditConfPass;
    Button BtnRegistro;
    TextView TxtLogin;

    private String Password="";
    private String ConfPassword="";
    private String Email="";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        EditConfPass = findViewById(R.id.ConfPass);
        EditPass = findViewById(R.id.Pass);
        EditEmail = findViewById(R.id.email);
        BtnRegistro = findViewById(R.id.registroBtn);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void  registarUsuario(View view) {
        Password = EditPass.getText().toString().trim();
        ConfPassword = EditConfPass.getText().toString().trim();
        Email = EditEmail.getText().toString().trim();
        if (Email.isEmpty()) {
            EditEmail.setError("Debe ingresar el correo");
        }
        if (Password.isEmpty()) {
            EditPass.setError("Debe ingresar la contraseña");
        }
        if (ConfPassword.isEmpty()) {
            EditConfPass.setError("Debe ingresar la contraseña");
        }

        if (!Email.isEmpty() && !Password.isEmpty() && !ConfPassword.isEmpty()) {
            if (Password.length() >= 6) {
                if (Password.equals(ConfPassword)) {
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });}

                } else {
                    EditConfPass.setError("No coincide con la contraseña elegida");
                }
            } else {
                EditPass.setError("Su contraseña debe tener 6 caracteres o mas");
            }


        }

}