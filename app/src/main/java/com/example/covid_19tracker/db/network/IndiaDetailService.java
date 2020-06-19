package com.example.covid_19tracker.db.network;

import com.example.covid_19tracker.models.DistrictName;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IndiaDetailService {


    private static IndiaDetailService mInstance ;
    private Retrofit retrofit ;
    private static final String BASE_URL = "https://api.covid19india.org/" ;

    private IndiaDetailService() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        //Custom Parser Class For Parsing Json With Dynamic Keys
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DistrictName.class,new DistrictJsonDeserializerClass()) ;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .client(httpClient)
                .build() ;

    }


    public static IndiaDetailService getInstance(){

        if (mInstance==null)
            mInstance = new IndiaDetailService() ;

        return mInstance;
    }

    public <S>S createService(Class<S> serviceClass){

        return retrofit.create(serviceClass) ;
    }
}
