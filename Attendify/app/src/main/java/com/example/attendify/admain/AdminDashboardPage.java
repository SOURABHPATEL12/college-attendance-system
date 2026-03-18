package com.example.attendify.admain;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendify.R;
import com.example.attendify.mainnavigation.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AdminDashboardPage extends AppCompatActivity {

    Toolbar AdmainToolBar;
    FloatingActionButton btnAddbyadmin;

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

                // Set width & height
                dialog.getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                dialog.show();

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
                sphodDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedDept = depertment[position];
                        String[] branches = branchMap.get(selectedDept);

                        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, branches);

                        sphodBranch.setAdapter(branchAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                } );


                // add hod button work
                dilaog_addhodbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         // work left

               //////////////////////////////         /// //////////////////////////////////////////////
                            // api to Store data in database

                        //1
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://10.230.226.196/adminapi1/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .build();

                        // 2 create interface

                        // 3 conecting interface
                        HodAddDetailInterface apiserver = retrofit.create(HodAddDetailInterface.class);
                           // 4
                        String name =hodName.getText().toString().trim();
                        String gmail = hodgmail.getText().toString().trim();
                        String deperment = sphodDepartment.getSelectedItem().toString().trim();
                        String branch = sphodBranch.getSelectedItem().toString().trim();

                        //format to send data to api

                        //{
                        //  "name": "Dr Sharma",
                        //  "email": "sharma@gmail.com",
                        //  "department": "Engineering",
                        //  "branch": "CSE"
                        //}
                      //5
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("name",name);
                            jsonObject.put("email",gmail);
                            jsonObject.put("department",deperment);
                            jsonObject.put("branch",branch);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        // 6
                        String jsonString = jsonObject.toString();
                        RequestBody body = RequestBody.create(jsonString, MediaType.parse("application/json"));

                        //7
                        Call<ResponseBody> call = apiserver.AddNewHod(body);

                        //8 now below tha api respone
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String apiResponsedata = null;
                                try {
                                    apiResponsedata = response.body().string();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                //{
                                //    "status": "success",
                                //    "message": "HOD added successfully"
                                try {
                                    JSONObject jsonObject1 = new JSONObject(apiResponsedata);
                                    String message = jsonObject1.getString("message");
                                    Toast.makeText(AdminDashboardPage.this,message,Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }

                        });
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



///////////        /// //////////////////////////////////////////////////////////////////


        RecyclerView Recyclerview ;
        Recyclerview = findViewById(R.id.Recyclerview);

        // imp
        Recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //( now model class )

        // arrylist
        ArrayList<REcyclervierModelclass> arraylist = new ArrayList<>();

        //  calling api to get all hod present in database
//     http://localhost/adminapi1/get_hod.php

        //1
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://10.230.226.196/adminapi1/")
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .build();

        //2  create interface

        //3 connecting interfece
        GetApiAllhodInterface api = retrofit.create(GetApiAllhodInterface.class);

        //4
        Call<String> call = api.gethod();

        // 5
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String allhoddata = response.body();

                Log.e("api","apiresponce"+allhoddata);

                try {
                    JSONObject jsonObject = new JSONObject(allhoddata);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    arraylist.clear();

                    for(int i = 0;i<jsonArray.length();i++) {
                        JSONObject finaldata = jsonArray.getJSONObject(i);
                        String name = finaldata.getString("name");
                        String gmail = finaldata.getString("email");
                        String department = finaldata.getString("department");
                        String branch = finaldata.getString("branch");


                        Log.e("apidata","parsing data"+name+gmail+department+branch);

                        // adding details in arraylist
                        arraylist.add(new REcyclervierModelclass(R.drawable.hod_icon,name,gmail,department,branch));

                    }

                    // adapter connect
                    HodAdapter adapter = new HodAdapter(AdminDashboardPage.this, arraylist);
                    Recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




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