package com.scripgo.www.admingraviflex.response;

import com.google.gson.annotations.SerializedName;
import com.scripgo.www.admingraviflex.models.Usuario;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class LoginResponse {
    /*@SerializedName: Si el dato retornado desde el api tiene un nombre distinto a nusetra variables, podemos zerializar
    * @data: es el Objeto retornado del api, y sera entregado al objeto Usuario*/
    @SerializedName(value="data")
    public Usuario usuario;
    public boolean error;
    public String message;
}
