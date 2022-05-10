package com.example.fitnesspack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    Button update,delete;
    ImageButton calculateMenu,mealMenu,userProfile,exerciseMenu;
    EditText username,age,names,gender,email;
    DBHelper DB;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ProfileImage = (CircleImageView) findViewById(R.id.Profile_Image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select Picture"), PICK_IMAGE);

            }
        });
        username = findViewById(R.id.usernamep);
        age = findViewById(R.id.agep);
        names = findViewById(R.id.namep);
        gender = findViewById(R.id.genderp);
        email = findViewById(R.id.emailp);
        update = findViewById(R.id.updatep);
        delete = findViewById(R.id.deletep);


        DB = new DBHelper(this);

        Intent i = getIntent();
        String name = i.getStringExtra("username");
        String password = i.getStringExtra("password");
        Cursor result = DB.getdata(name);
        username.setText(name);


     while(result.moveToNext()){

         username.setText(result.getString(0));
         names.setText(result.getString(1));
         email.setText(result.getString(2));
         age.setText(result.getString(3));
         gender.setText(result.getString(4));

     }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userU = username.getText().toString();
                String passU = password;
                String nameU = names.getText().toString();
                String ageU = age.getText().toString();
                String emailU = email.getText().toString();
                String genderU = gender.getText().toString();

                Boolean checkupdatedate = DB.updateuserdata(userU,nameU,emailU,ageU,genderU,passU);

                if(checkupdatedate==true)
                    Toast.makeText(Profile.this,"entry update",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Profile.this,"entry failed",Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                Boolean checkdelete = DB.deleteuserdata(user);

                if(checkdelete==true) {
                    Toast.makeText(Profile.this, "entry delete", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),loginHome.class);
                    startActivity(intent);
                }

                else
                    Toast.makeText(Profile.this,"entry failed",Toast.LENGTH_SHORT).show();
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
                String userU = username.getText().toString();
                String passU = password;

                Intent intent = new Intent(Profile.this, Profile.class);
                intent.putExtra("username",userU);
                intent.putExtra("password",passU);
                startActivity(intent);

            }
        });

        calculateMenu = findViewById(R.id.incomeMenu4);

        //TOOLBAR BUTTON SET
        calculateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userU = username.getText().toString();
                String passU = password;
                Intent intent = new Intent(Profile.this, BMI.class);
                intent.putExtra("username",userU);
                intent.putExtra("password",passU);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                ProfileImage.setImageBitmap(bitmap);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}