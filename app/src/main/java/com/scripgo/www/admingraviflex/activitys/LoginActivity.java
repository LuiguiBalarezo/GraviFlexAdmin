package com.scripgo.www.admingraviflex.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scripgo.www.admingraviflex.R;
import com.scripgo.www.admingraviflex.apidapter.ApiAdapter;
import com.scripgo.www.admingraviflex.help.MaterialDialogHelp;
import com.scripgo.www.admingraviflex.help.ShowActivitysHelper;
import com.scripgo.www.admingraviflex.models.Usuario;
import com.scripgo.www.admingraviflex.response.LoginResponse;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A Login screen that offers Login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private Context context = null;
    // UI references.
    private EditText edt_usuario, edt_clave;
    private Button btn_entrar;
    private Toolbar mToolbar;
    private CoordinatorLayout coordinatorLayout;
    // REALM
    private Realm realm = null;
    private RealmAsyncTask transaction = null;
    // HELPS
    private MaterialDialogHelp materialDialogHelp = null;
    private ShowActivitysHelper showActivitysHelper = null;

    //VARS INPUTS
    private String usuario = null;
    private String clave = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        // HELP
        materialDialogHelp = new MaterialDialogHelp(this);
        showActivitysHelper = new ShowActivitysHelper(context, LoginActivity.this);
        //////////

        if(isExistsUser(realm)){
            showActivitysHelper.startActivity(MainActivity.class);
        }

        setContentView(R.layout.activity_login);

        // INITIALS
        initialUI();
        initialOnClicks();
        //////////
    }

    private void initialOnClicks() {
        btn_entrar.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_entrar:
                    usuario = edt_usuario.getText().toString();
                    clave = edt_clave.getText().toString();
                    signInProcess(usuario, clave);
                    break;
            }
        }
    };

    private void signInProcess(String user, String pass) {
        materialDialogHelp.showOrDismissIndeterninate(null, "Validando Usuario");
        Call<LoginResponse> login = ApiAdapter.getApiService().processLogin(user, pass);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                materialDialogHelp.showOrDismissIndeterninate(null, null);
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.error) {
                        Toast.makeText(LoginActivity.this, " " + loginResponse.message + " " + loginResponse.usuario, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, " " + loginResponse.usuario.id, Toast.LENGTH_SHORT).show();
                        final Usuario usuario = loginResponse.usuario;
                        guardaUsuario(usuario);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "ERROR EN FORMATO DE RESPUESTA", Toast.LENGTH_SHORT).show();
                    realm.close();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                materialDialogHelp.showOrDismissIndeterninate(null, null);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void guardaUsuario(final Usuario usuario){
        realm = Realm.getDefaultInstance();
        transaction = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(usuario);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Toast.makeText(LoginActivity.this, "SUCCESS REALM", Toast.LENGTH_SHORT).show();
                showActivitysHelper.startActivity(MainActivity.class);
                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(LoginActivity.this, "ERROR REALM", Toast.LENGTH_SHORT).show();
                realm.close();
            }
        });
    }

    private boolean isExistsUser(Realm r){
        realm = Realm.getDefaultInstance();
        final RealmResults<Usuario> results = realm.where(Usuario.class).findAll();
        if(results.size() != 0){
            Usuario usuario = results.first();
            realm.close();
            return true;
        }else {
            realm.close();
            return false;
        }

    }

    private void initialUI(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        edt_usuario = (EditText) findViewById(R.id.edt_usuario);
        edt_clave = (EditText) findViewById(R.id.edt_clave);
        btn_entrar = (Button) findViewById(R.id.btn_entrar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}

