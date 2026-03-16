package com.example.attendify.admain;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.attendify.R;
import com.example.attendify.mainnavigation.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class AdminDashboardPage extends AppCompatActivity {

    Toolbar AdmainToolBar;
    Button btnAddbyadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_page);

        AdmainToolBar=findViewById(R.id.AdmaiToolbar);
        btnAddbyadmin=findViewById(R.id.btnAddbyadmin);

        btnAddbyadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  // add hod dialiog box
                Dialog dialog = new Dialog(AdminDashboardPage.this);
                dialog.setContentView(R.layout.dialog_add_hod);
                dialog.setCancelable(true);  // on backpress diaog box close

                EditText hodName = dialog.findViewById(R.id.hodName);
                EditText hodgmail = dialog.findViewById(R.id.hodGmail);
                Button  dilaog_addhodbutton = dialog.findViewById(R.id.dilaog_addhodbutton);

                Spinner sphodDepartment = dialog.findViewById(R.id.sphodDepartment);
                Spinner sphodBranch = dialog.findViewById(R.id.sphodBranch);

                // putting all    devertment value in depertment spinner

                String []  depertment = {"Engineering Department","Pharmacy Department","Commerce Department"};

                //connecting  sting arrya and spinner by adopter
                ArrayAdapter<String> sparrayadopter = new ArrayAdapter<>(AdminDashboardPage.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,depertment);

                sphodDepartment.setAdapter(sparrayadopter);


                // maping depertment and branch

                Map<String, String[]> branchMap = new HashMap<>();

                branchMap.put("Engineering Department", new String[]{"CSE","AIML","IT"});
                branchMap.put("Pharmacy Department", new String[]{"B.Pharm","M.Pharm"});
                branchMap.put("Commerce Department", new String[]{"B.Com","BBA"});

                // onitem click update branch automaticaly
                sphodDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedDept = depertment[position];
                        String[] branches = branchMap.get(selectedDept);

                        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, branches);

                        sphodBranch.setAdapter(branchAdapter);
                    }
                });


                // add hod button work
                dilaog_addhodbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         // work left

                    }
                });



            }
        });


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