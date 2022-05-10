package com.example.fitnesspack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    EditText username,password;
    Button signin,login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.lusername);
        password = findViewById(R.id.lpassword);
        DB = new DBHelper(this);
        signin = findViewById(R.id.signin_2);
        login = findViewById(R.id.btn_login1);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignIn.this,"ALL fields Required",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpassword = DB.checkusernamepassword(user,pass);

                    if (checkuserpassword == true){

                        Toast.makeText(SignIn.this,"Login Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("username", user);
                        intent.putExtra("password",pass);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignIn.this,"Login failed",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });


    }
}