package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextView already;
    private EditText userName;
    private EditText userPasswors;
    private EditText userEmail;
    private Button Register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setup();

        firebaseAuth=FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(validate()){
                   String user_email=userEmail.getText().toString().trim();
                   String user_pass=userPasswors.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(RegistrationActivity.this, "registration Success", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                           }
                           else {
                               Toast.makeText(RegistrationActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });

    }

    private void setup(){
        already=(TextView)findViewById(R.id.tvalready);
        userName=(EditText)findViewById(R.id.etusername);
        userPasswors=(EditText)findViewById(R.id.etpassword);
        userEmail=(EditText)findViewById(R.id.etemail);
        Register=(Button)findViewById(R.id.btnregister);

    }
    private Boolean validate(){
        Boolean result=false;
        String name=userName.getText().toString();
        String pass=userPasswors.getText().toString();
        String ema=userEmail.getText().toString();

        if(name.isEmpty()||pass.isEmpty()||ema.isEmpty()){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            result=true;
        }
        return result;
    }

}
