package com.example.attendify.admain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.attendify.R;
import com.example.attendify.mainnavigation.MainActivity;

public class AdminDashboardPage extends AppCompatActivity {

    Toolbar AdmainToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_page);

        AdmainToolBar=findViewById(R.id.AdmaiToolbar);

        // Set toolbar as ActionBar
        //actionbar provide operations like back button ,menu ,etc predefine by android
        setSupportActionBar(AdmainToolBar);


        // 1 operation
        //  for back  arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //2 operation
        getSupportActionBar().setTitle("Admin");
        getSupportActionBar().setSubtitle("Hii sourabh patel");


        // crate resourse file by menu and add item
    }

    // create new method out of oncreate method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    // set onclick on item selected in menu by  using id
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.AdmainLogout){

            SharedPreferences sp = getSharedPreferences("adminlogin",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("Status",false);
            editor.apply();

            Intent intent = new Intent(AdminDashboardPage.this, MainActivity.class);
            startActivity(intent);
            finish(); // optional (closes dashboard)

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}