package com.scripgo.www.admingraviflex.response;

import com.google.gson.annotations.SerializedName;
import com.scripgo.www.admingraviflex.models.Usuario;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class UsuariosResponse {

    @SerializedName(value="data")
    public Usuario usuarios;
    boolean error;
    String message;

}
