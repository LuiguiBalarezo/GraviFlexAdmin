package com.scripgo.www.admingraviflex.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scripgo.www.admingraviflex.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText edt_nombre, edt_apellido_paterno,
            edt_apellido_materno, edt_edad, edt_usuario, edt_clave, edt_clave_confirmacion,
            edt_correo;
    private Button btn_crear, btn_mostrar_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_nombre = (EditText)findViewById(R.id.edt_nombre);
        edt_apellido_paterno = (EditText)findViewById(R.id.edt_apellido_paterno);
        edt_apellido_materno = (EditText)findViewById(R.id.edt_apellido_materno);
        edt_edad = (EditText)findViewById(R.id.edt_edad);
        edt_usuario = (EditText)findViewById(R.id.edt_usuario);
        edt_clave = (EditText)findViewById(R.id.edt_clave);
        edt_clave_confirmacion = (EditText)findViewById(R.id.edt_clave_confirmacion);
        edt_correo = (EditText)findViewById(R.id.edt_correo);

        btn_crear = (Button)findViewById(R.id.btn_crear);
        btn_mostrar_signin = (Button)findViewById(R.id.btn_mostrar_signin);

        btn_crear.setOnClickListener(onClickListener);
        btn_mostrar_signin.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_crear:
                    Toast.makeText(SignUpActivity.this, "btn_crear", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_mostrar_signin:
                    Toast.makeText(SignUpActivity.this, "btn_mostrar_signin", Toast.LENGTH_SHORT).show();
                    showSignIn();
                    break;
            }
        }
    };

    private void showSignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        finishAffinity();
        startActivity(intent);
    }
}
