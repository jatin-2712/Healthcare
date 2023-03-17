package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText userName, userPassword;
    Button loginButton;
    TextView signUpTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.nameSignUp);
        userPassword=findViewById(R.id.passwordLogin);
        loginButton=findViewById(R.id.button);
        signUpTextView=findViewById(R.id.gotoSignUpPage);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrName = userName.getText().toString();
                String usrPass = userPassword.getText().toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);


                if(usrName.length()==0 || usrPass.length()==0){
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }

                if(db.login(userName.getText().toString(),userPassword.getText().toString())==1){
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",userName.getText().toString());

                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });








    }

}