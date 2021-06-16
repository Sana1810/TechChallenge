package com.example.techchallenge;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InterfaceGenerator {

    public static final String BASE_URL = "https://testa2.aisle.co/V1/users/";
    public static Retrofit createService(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
