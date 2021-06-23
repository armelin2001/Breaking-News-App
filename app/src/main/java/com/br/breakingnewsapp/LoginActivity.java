package com.br.breakingnewsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.br.breakingnewsapp.model.Noticia;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    private TextView editTextTextEmailAddress;
    private TextView editTextTextPassword;


    private String messageLogin = "Erro ao realizar login";
    private String messageCreate = "Erro ao criar usuario";
    //email@gmail.com
    //12345678
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        firebaseDatabase = FirebaseDatabase.getInstance();
    }
    public void buttonCreateUser(View view){
        if(editTextTextEmailAddress.toString().equals("")||editTextTextPassword.toString().equals("")
                ||editTextTextEmailAddress.toString().equals("")&&editTextTextPassword.toString().equals("")){
            Toast.makeText(LoginActivity.this,"Erro ao realizar login",Toast.LENGTH_LONG).show();

        }else {
            createUser(editTextTextEmailAddress.getText().toString(),editTextTextPassword.getText().toString());
        }

    }
    public void buttonLoginUser(View view){
        if(editTextTextEmailAddress.toString().equals("")||editTextTextPassword.toString().equals("")
                ||editTextTextEmailAddress.toString().equals("")&&editTextTextPassword.toString().equals("")){
            Toast.makeText(LoginActivity.this, "Erro ao realizar login",Toast.LENGTH_LONG).show();

        }
        signInUser(editTextTextEmailAddress.getText().toString(),editTextTextPassword.getText().toString());
    }
    public void signInUser(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            LoginActivity.this.startActivity(intent);
                            //verificar se tem que passar o usario no intent
                            //passar o usuario para a tela da list view
                        }else {
                            Toast toast = Toast.makeText(LoginActivity.this,messageLogin,Toast.LENGTH_LONG);
                            toast.show();
                            Log.e("fireLogin",task.getException().toString());
                        }
                    }
                }
        );
    }
    public void createUser(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isCanceled()){
                            Log.e("fireAuth",task.getException().toString());
                        }else {
                            DatabaseReference rootReference = firebaseDatabase.getReference();
                            HashMap<String,Noticia> noticias = new HashMap<>();
                            //noticias.add(new Noticia("","",""));
                            noticias.put("0",new Noticia("","",""));
                            firebaseUser = firebaseAuth.getCurrentUser();
                            rootReference.child(firebaseUser.getUid()).child("news").setValue(noticias);
                            Intent intent = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            LoginActivity.this.startActivity(intent);
                        }
                    }
                });
    }
}