package com.mg.firstcode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Age_Interface {

    @GET("/openapi/service/rest/Covid19/getCovid19GenAgeCaseInfJson")
    Call<Age_Repo> get_age(@Query(value = "serviceKey", encoded = true) String serviceKey, @Query("pageNo") int pageNo, @Query("numOfRows") int numOfRows,
                                   @Query("startCreateDt") int startCreateDt, @Query("endCreateDt")int endCreateDt, @Query("_type")String _type);
}
