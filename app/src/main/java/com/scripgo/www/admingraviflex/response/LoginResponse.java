package com.scripgo.www.admingraviflex.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class LoginResponse {
    /*@SerializedName: Si el dato retornado desde el api tiene un nombre distinto a nusetra variables, podemos zerializar
    * @data: es el Objeto retornado del api, y sera entregado al objeto Usuario*/
    @SerializedName(value="data")
    public Object usuario;
    boolean error;
    String message;

    public LoginResponse(Object usuario, boolean error, String message) {
        this.usuario = usuario;
        this.error = error;
        this.message = message;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuarios) {
        this.usuario = usuarios;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
