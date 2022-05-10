package com.example.fitnesspack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

    Button confirm;
    EditText height,weight;
    ImageButton calculateMenu,mealMenu,userProfile,exerciseMenu;
    float h=0,w=0,bmi=0;
    String user="0";
    String res;
    TextView result,condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        confirm = findViewById(R.id.btn_bconfirm);
        height = findViewById(R.id.et_bheight);
        weight = findViewById(R.id.et_bweight);
        result = findViewById(R.id.tv_result);
        condition = findViewById(R.id.tv_condition);
        Intent i = getIntent();
        String name = i.getStringExtra("username");
        String password = i.getStringExtra("password");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heights = height.getText().toString();
                String weights = weight.getText().toString();

                if (TextUtils.isEmpty(heights)) {
                    height.setError("Enter Height");
                    height.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(weights)) {
                    weight.setError("Enter Height");
                    weight.requestFocus();
                    return;

                } else {
                    calculate();
                }
            }
        });
        exerciseMenu = findViewById(R.id.incomeMenu2);

        //TOOLBAR BUTTON SET
        exerciseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

        userProfile = findViewById(R.id.incomeMenu5);

        //TOOLBAR BUTTON SET
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMI.this, Profile.class);
                intent.putExtra("username",name);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

        calculateMenu = findViewById(R.id.incomeMenu4);

        //TOOLBAR BUTTON SET
        calculateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMI.this, BMI.class);
                startActivity(intent);
            }
        });

        mealMenu = findViewById(R.id.budgetMenu3);

        //TOOLBAR BUTTON SET
        mealMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });


    }

       private void calculate() {

           h = Float.parseFloat(height.getText().toString());
           w = Float.parseFloat(weight.getText().toString());
           float hm;
           hm = h / 100;
           bmi = (w / (hm * hm));
           result.setText(Float.toString(bmi));
           if (bmi >= 30 ) {
               res = "Obseweight";
               condition.setText(res);
           }
          else if (bmi >= 25 && bmi < 30) {
               res = "Overweight";
               condition.setText(res);
           }
           else if (bmi >= 18.5 && bmi < 25) {
               res = "IdealWeight";
               condition.setText(res);
           } else {
               res = "Underweight";
               condition.setText(res);
           }


       }


}

