package com.mg.firstcode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Covid19_Interface {

    @GET("/openapi/service/rest/Covid19/getCovid19InfStateJson")
        Call<Covid19_Repo> get_covid19(@Query(value = "serviceKey", encoded = true) String serviceKey, @Query("pageNo") int pageNo, @Query("numOfRows") int numOfRows,
                                       @Query("startCreateDt") int startCreateDt, @Query("endCreateDt")int endCreateDt, @Query("_type")String _type);


}
