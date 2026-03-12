package com.example.attendify.admain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendify.R;

public class AdminLoginPage extends AppCompatActivity {

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login_page);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextToAdmainDeshboard;
                NextToAdmainDeshboard = new Intent(AdminLoginPage.this, AdminDashboardPage.class);
                startActivity(NextToAdmainDeshboard);
                finish();
            }
        });


    }
}