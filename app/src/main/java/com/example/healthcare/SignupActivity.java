package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    EditText usernameSignUp, userEmailSignUp, passwordSignUp, confirmPassSignUp;
    Button signUp;
    TextView goToLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        usernameSignUp=findViewById(R.id.usernameSignUp);
        userEmailSignUp=findViewById(R.id.userEmailSignUp);
        passwordSignUp=findViewById(R.id.passwordLogin);
        confirmPassSignUp=findViewById(R.id.confirmPasswordSignUp);
        signUp=findViewById(R.id.button);
        goToLoginPage=findViewById(R.id.gotoLoginPage);

        goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Database db = new Database(getApplicationContext(),"healthcare",null,1);



                if(usernameSignUp.getText().toString().length()==0
                        || userEmailSignUp.getText().toString().length()==0
                        || passwordSignUp.getText().toString().length()==0
                        || confirmPassSignUp.getText().toString().length()==0){

                    Toast.makeText(SignupActivity.this, "Enter the Required Feilds", Toast.LENGTH_SHORT).show();
                }

               if(validateEmail()==true){


                   if(passwordSignUp.getText().toString().equals(confirmPassSignUp.getText().toString())==false){
                       Toast.makeText(SignupActivity.this, "Both Passwords did't match", Toast.LENGTH_SHORT).show();
                   }

                   if(validatePassword()==true)
                   {   db.register(usernameSignUp.getText().toString(),userEmailSignUp.getText().toString(),passwordSignUp.getText().toString());
                       Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                   }


               }



            }
        });






    }

    private boolean validateEmail() {

        // Extract input from EditText
        String emailInput = userEmailSignUp.getText().toString().trim();

        // if the email input field is empty
        if (emailInput.isEmpty()) {
            userEmailSignUp.setError("Field can not be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            userEmailSignUp.setError("Please enter a valid email address");
            return false;
        } else {
            userEmailSignUp.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = passwordSignUp.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            passwordSignUp.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordSignUp.setError("Password is too weak");
            return false;
        } else {
            passwordSignUp.setError(null);
            return true;
        }
    }
}