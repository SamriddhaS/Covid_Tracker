package com.example.covid_19tracker.db.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApiService {

    private static MainApiService mInstance ;
    private Retrofit retrofit ;
    private static final String BASE_URL = "https://disease.sh/" ;

    private MainApiService() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build() ;

    }


    public static MainApiService getInstance(){

        if (mInstance==null)
            mInstance = new MainApiService() ;

        return mInstance;
    }

    public <S>S createService(Class<S> serviceClass){

        return retrofit.create(serviceClass) ;
    }


}
