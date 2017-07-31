package com.scripgo.www.admingraviflex.interfaces;

import com.scripgo.www.admingraviflex.help.ConstantsHelp;
import com.scripgo.www.admingraviflex.response.LoginResponse;
import com.scripgo.www.admingraviflex.response.UsuarioResponse;
import com.scripgo.www.admingraviflex.response.UsuariosResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public interface ApiService {

    /*
    * LOGIN
    * */
    @FormUrlEncoded
    @POST("signin")
    @Headers(ConstantsHelp.ACCESS_TOKEN)
    Call<LoginResponse> processLogin(@Field("edt_usuario") String usuario, @Field("edt_clave") String clave);

    @FormUrlEncoded
    @POST("logout")
    @Headers(ConstantsHelp.ACCESS_TOKEN)
    Call<LoginResponse> processLogout(@Field("edt_id") String id);


    /*
    * USUARIOS
    * */
    @GET("users")
    @Headers(ConstantsHelp.ACCESS_TOKEN)
    Call<UsuariosResponse> getAllUsuarios();

    @GET("user/{id}")
    @Headers(ConstantsHelp.ACCESS_TOKEN)
    Call<UsuarioResponse> getUsuarioById(@Path("id") String id);



}
