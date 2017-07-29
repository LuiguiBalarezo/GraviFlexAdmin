package com.scripgo.www.admingraviflex.response;

import com.google.gson.annotations.SerializedName;

import static android.R.attr.data;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class UsuariosResponse {

    @SerializedName(value="data")
    public Object usuarios;
    boolean error;
    String message;

    public UsuariosResponse(Object usuarios, boolean error, String message) {
        this.usuarios = usuarios;
        this.error = error;
        this.message = message;
    }

    public Object getUsuarios() {
        return data;
    }

    public void setUsuarios(Object usuarios) {
        this.usuarios = usuarios;
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
