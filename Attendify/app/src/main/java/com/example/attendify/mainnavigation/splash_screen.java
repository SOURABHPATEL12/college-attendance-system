package com.example.attendify.mainnavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.attendify.R;
import com.example.attendify.admain.AdminDashboardPage;
import com.example.attendify.admain.AdminLoginPage;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

                new Handler().postDelayed(new Runnable() {


                    Intent  i ;
                    @Override
                    public void run() {
                           SharedPreferences sp = getSharedPreferences("adminlogin",MODE_PRIVATE);
                           Boolean islogin = sp.getBoolean("Status",false);
                           if(islogin){
                               i = new Intent(splash_screen.this, AdminDashboardPage.class);

                           }else{

                               i = new Intent(splash_screen.this, MainActivity.class);

                           }
                            startActivity(i);
                            finish();
                    }
                },2000);

    }
}