package com.example.attendify.admain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OtpInterface {
  @GET("send_otp.php")
   Call<String> getotpdata();

}
