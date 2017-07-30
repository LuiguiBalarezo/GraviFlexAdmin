package com.scripgo.www.admingraviflex.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.scripgo.www.admingraviflex.R;
import com.scripgo.www.admingraviflex.apidapter.ApiAdapter;
import com.scripgo.www.admingraviflex.models.Usuario;
import com.scripgo.www.admingraviflex.response.LoginResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A Login screen that offers Login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    private MaterialDialog materialDialog = null;
    // UI references.
    private EditText edt_usuario, edt_clave;
    private Button btn_entrar, btn_mostrar_signup;

    // REALM
    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_usuario = (EditText)findViewById(R.id.edt_usuario);
        edt_clave = (EditText)findViewById(R.id.edt_clave);
        btn_entrar = (Button)findViewById(R.id.btn_entrar);
        btn_mostrar_signup = (Button)findViewById(R.id.btn_mostrar_signup);

        btn_entrar.setOnClickListener(onClickListener);
        btn_mostrar_signup.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entrar:
                String usuario = edt_usuario.getText().toString();
                String clave = edt_clave.getText().toString();
                signInProcess(usuario, clave);
                break;
            case R.id.btn_mostrar_signup:
                showSignUp();
                break;
        }
        }
    };

    private void signInProcess(String user, String pass){
        showOrDismissDialog();
        Call<LoginResponse> login = ApiAdapter.getApiService().processLogin(user, pass);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                showOrDismissDialog();
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse.error){
                        Toast.makeText(LoginActivity.this, ""+ loginResponse.message + " " + loginResponse.usuario, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, ""+ loginResponse.message + " " + loginResponse.usuario.id, Toast.LENGTH_SHORT).show();

                        final Usuario obj = loginResponse.usuario;

                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm bgRealm) {
                                bgRealm.copyToRealmOrUpdate(obj);
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                                // Transaction was a success.
                                Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                            }
                        }, new Realm.Transaction.OnError() {
                            @Override
                            public void onError(Throwable error) {
                                Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "ERROR EN FORMATO DE RESPUESTA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                showOrDismissDialog();
                Toast.makeText(LoginActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showOrDismissDialog(){
        if(materialDialog == null){
            materialDialog = new MaterialDialog.Builder(LoginActivity.this)
                    .autoDismiss(false)
                    .cancelable(false)
                    .content("Validando Usuario")
                    .progress(true, 0)
                    .progressIndeterminateStyle(true).build();
            materialDialog.show();
        }else{
            materialDialog.dismiss();
            materialDialog = null;
        }
    }

    private void showSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}

