package com.example.attendify.mainnavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendify.Hod.hodloginpage;
import com.example.attendify.R;
import com.example.attendify.admain.AdminDashboardPage;
import com.example.attendify.admain.AdminLoginPage;

public class MainActivity extends AppCompatActivity {

    Button btnTeacher,btnStudent,btnAdmin,btnHod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTeacher = findViewById(R.id.btnTeacher);
        btnStudent = findViewById(R.id.btnStudent);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnHod = findViewById(R.id.btnHod);



        //student
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // Teacher
        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Hod
        btnHod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent NextTohodlogin;
                 NextTohodlogin = new Intent(MainActivity.this, hodloginpage.class);
                 startActivity(NextTohodlogin);
                 finish();

            }
        });

        // admain
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent NextToAdmainLoginpage;
                NextToAdmainLoginpage = new Intent(MainActivity.this, AdminLoginPage.class);
                startActivity(NextToAdmainLoginpage);
                finish();
            }
        });


    }
}