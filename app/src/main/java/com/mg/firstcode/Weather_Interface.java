package com.mg.firstcode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weather_Interface {

    @GET("/data/2.5/weather")
    Call<Repo> get_weather(@Query("appid") String appid, @Query("lat") double lat, @Query("lon") double lon);

}
