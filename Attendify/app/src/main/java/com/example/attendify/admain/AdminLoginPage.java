package com.example.attendify.admain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendify.R;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AdminLoginPage extends AppCompatActivity {

    Button loginBtn,sendOtpBtn;
    EditText adminEmail,otpField;

    // store otp
    String finalotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login_page);
        loginBtn = findViewById(R.id.loginBtn);
        sendOtpBtn= findViewById(R.id.sendOtpBtn);
        adminEmail= findViewById(R.id.adminEmail);
        otpField= findViewById(R.id.otpField);

        // send otp
        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String inputgmail = adminEmail.getText().toString().trim();
                   String  gmail ="sp824768@gmail.com";
                   if(inputgmail.equals(gmail)) {
                       
                       // api calling
                       //http://192.168.1.3/adminapi1/send_otp.php
                       //192.168.1.3   change daily
                       Retrofit retrofit = new Retrofit.Builder()
                               .baseUrl("http://192.168.1.6/adminapi1/")
                               .addConverterFactory(ScalarsConverterFactory.create())
                               .build();

                       OtpInterface api = retrofit.create(OtpInterface.class);

                       Call<String> call = api.getotpdata();

                       call.enqueue(new Callback<String>() {
                           @Override
                           public void onResponse(Call<String> call, Response<String> response) {
                               // data parsing
                               String apiresponse = response.body();
                               try {
                                   JSONObject jsonObject = new JSONObject(apiresponse);
                                   String otp = jsonObject.getString("otp");

                                   finalotp=otp;
                                   Toast.makeText(AdminLoginPage.this,"otp is send to your gmail",Toast.LENGTH_LONG).show();

                               } catch (JSONException e) {

                                   Toast.makeText(AdminLoginPage.this,"catch error",Toast.LENGTH_LONG).show();

                                   throw new RuntimeException(e);
                               }

                           }

                           @Override
                           public void onFailure(Call<String> call, Throwable t) {

                               Log.e("apifail","why api fail",t);

                               Toast.makeText(AdminLoginPage.this,"api fail",Toast.LENGTH_LONG).show();


                           }
                       });

                   }else{
                       Toast.makeText(AdminLoginPage.this,"plz enter admin gmail",Toast.LENGTH_LONG).show();


                        }
            }
        });


        //login button and do opt match
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String inputotp=otpField.getText().toString();
                    if(inputotp.equals(finalotp)){
                        Intent nextToAdmainDeshboard;
                        nextToAdmainDeshboard = new Intent(AdminLoginPage.this, AdminDashboardPage.class);
                        startActivity(nextToAdmainDeshboard);
                        finish();
                    }else{
                        Toast.makeText(AdminLoginPage.this,"Wrong opt",Toast.LENGTH_LONG).show();
                    }

            }
        });



    }
}