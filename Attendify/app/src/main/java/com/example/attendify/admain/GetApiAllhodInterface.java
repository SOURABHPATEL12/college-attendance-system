package com.example.attendify.admain;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetApiAllhodInterface {

    @GET("get_hod.php")
    Call<String> gethod();
}
