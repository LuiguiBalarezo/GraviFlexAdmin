package com.scripgo.www.admingraviflex.apidapter;

import com.scripgo.www.admingraviflex.help.ConstantsHelp;
import com.scripgo.www.admingraviflex.interfaces.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class ApiAdapter {

    private static ApiService API_SERVICE;

    public static ApiService getApiService(){
        HttpLoggingInterceptor logging =  new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = ConstantsHelp.URL_BASE_API;

        if(API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);
        }

        return API_SERVICE;
    }
}
