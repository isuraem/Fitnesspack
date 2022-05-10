package com.example.fitnesspack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText username,password,repassword,name,email,age,gender;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.susername);
        password = findViewById(R.id.spassword);
        repassword = findViewById(R.id.srepassword);
        name = findViewById(R.id.sname);
        email = findViewById(R.id.semail);
        age = findViewById(R.id.sage);
        gender = findViewById(R.id.sgender);
        signup = findViewById(R.id.signup_1);
        signin = findViewById(R.id.signin1);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String names = name.getText().toString();
                String emails = email.getText().toString();
                String ages = age.getText().toString();
                String genders = gender.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass) || TextUtils.isEmpty(names) || TextUtils.isEmpty(emails) || TextUtils.isEmpty(ages) ||
                        TextUtils.isEmpty(genders)){

                    Toast.makeText(SignUp.this,"ALL fields Required",Toast.LENGTH_SHORT).show();

                }
                if(!validateEmail()|!validateName()){
                    return;
                }
                else{

                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);

                        if(checkuser == false){


                            Boolean insert = DB.insertData(user,names,emails,ages,genders,pass);

                            if (insert == true){
                                Toast.makeText(SignUp.this,"register Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUp.this,"register failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUp.this,"User Already Exits",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
            }
        });


    }
    private boolean validateEmail() {
        String val = email.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }
    }

    private boolean validateName() {
        String val = name.getText().toString().trim();
        if (val.isEmpty()) {
            name.setError("Field can not be empty");
            return false;
        } else {
            name.setError(null);
            name.setEnabled(false);
            return true;
        }
    }
    //checking spaces and length
    private boolean validateUsername() {
        String val = username.getText().toString().trim();
        String checkspaces = "Aw{1,8}z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 8) {
            username.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No White spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            username.setEnabled(false);
            return true;
        }
    }
}