package com.scripgo.www.admingraviflex.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.scripgo.www.admingraviflex.R;
import com.scripgo.www.admingraviflex.apidapter.ApiAdapter;
import com.scripgo.www.admingraviflex.fragments.EgresosFragment;
import com.scripgo.www.admingraviflex.fragments.IngresosFragment;
import com.scripgo.www.admingraviflex.fragments.ObrasFragment;
import com.scripgo.www.admingraviflex.fragments.ValoracionesFragment;
import com.scripgo.www.admingraviflex.help.MaterialDialogHelp;
import com.scripgo.www.admingraviflex.models.Usuario;
import com.scripgo.www.admingraviflex.response.LoginResponse;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements ObrasFragment.OnFragmentInteractionListener, EgresosFragment.OnFragmentInteractionListener, IngresosFragment.OnFragmentInteractionListener, ValoracionesFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    // VAR CONTEXT
    private Context context = null;
    private Intent intentlogin = null;

    // REALM
    private Realm realm = null;
    private RealmAsyncTask transaction = null;

    // UI
    private Toolbar toolbar = null;
    public FloatingActionButton fab = null;
    private DrawerLayout drawer = null;
    private ActionBarDrawerToggle toggle = null;
    private NavigationView navigationView = null;
    private MaterialDialogHelp materialDialogHelp  = null;

    private MaterialDialog materialdialog_obras = null;
    private MaterialDialog materialdialog_egresos = null;
    private MaterialDialog materialdialog_ingresos = null;
    private MaterialDialog materialdialog_valoraciones = null;
    // TRANSACTION
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // REALM
        realm = Realm.getDefaultInstance();
        /////////

        //HELPS
        materialDialogHelp = new MaterialDialogHelp(this);
        ////////

        materialdialog_obras = new MaterialDialog.Builder(context).autoDismiss(false)
                        .title("Ingrese Obras")
                        .customView(R.layout.dialog_obras, true)
                        .build();

        materialdialog_egresos = new MaterialDialog.Builder(context).autoDismiss(false)
                .title("Ingrese Egresos")
                .customView(R.layout.dialog_egresos, true)
                .build();

        materialdialog_ingresos = new MaterialDialog.Builder(context).autoDismiss(false)
                .title("Ingrese Ingresos")
                .customView(R.layout.dialog_ingresos, true)
                .build();

        materialdialog_valoraciones = new MaterialDialog.Builder(context).autoDismiss(false)
                .title("Ingrese Valoraciones")
                .customView(R.layout.dialog_valoraciones, true)
                .build();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getFragmentManager().findFragmentById(R.id.container_framelayout) == null) {
            selectFragment(R.id.nav_obras);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void actionFloatButton(String namefragment){
        switch (namefragment){
            case "ObrasFragment":
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "HACER ALGO EN OBRAS", Toast.LENGTH_SHORT).show();
                            materialdialog_obras.show();
                        }
                    });
                break;
            case  "EgresosFragment":
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "HACER ALGO EN EGRESOS", Toast.LENGTH_SHORT).show();
                        materialdialog_egresos.show();
                    }
                });
                break;
            case  "IngresosFragment":
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "HACER ALGO EN EGRESOS", Toast.LENGTH_SHORT).show();
                        materialdialog_ingresos.show();
                    }
                });
                break;
            case  "ValoracionesFragment":
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "HACER ALGO EN EGRESOS", Toast.LENGTH_SHORT).show();
                        materialdialog_valoraciones.show();
                    }
                });
                break;
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        selectFragment(id);
        return true;
    }

    private void selectFragment(int id){
        Fragment fragment = null;
        //Class fragmentClass;
        Class fragmentClass = null;
        switch (id){
            case R.id.nav_obras:
                Toast.makeText(this, "nav_obras", Toast.LENGTH_SHORT).show();
                fragmentClass = ObrasFragment.class;
                toolbar.setTitle("Obras");
                break;
            case R.id.nav_egresos:
                Toast.makeText(this, "nav_egresos", Toast.LENGTH_SHORT).show();
                fragmentClass = EgresosFragment.class;
                //fragment = (Fragment) EgresosFragment.newInstance("Luigui","Balarezo");
                toolbar.setTitle("Egresos");
                break;
            case R.id.nav_ingresos:
                Toast.makeText(this, "nav_ingresos", Toast.LENGTH_SHORT).show();
                fragmentClass = IngresosFragment.class;
//                fragment = (Fragment) IngresosFragment.newInstance("Luigui","Balarezo");
                toolbar.setTitle("Ingresos");
                break;
            case R.id.nav_valoraciones:
                Toast.makeText(this, "nav_valoraciones", Toast.LENGTH_SHORT).show();
                fragmentClass = ValoracionesFragment.class;
//                fragment = (Fragment) ValoracionesFragment.newInstance("Luigui","Balarezo");
                toolbar.setTitle("Valoraciones");
                break;
            case R.id.nav_salir:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                processLogout();
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            actionFloatButton(fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction().replace(R.id.container_framelayout, fragment).commit();

//        Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.container_framelayout);
//        if (fragment != null && fragment.isMenuVisible()) {
//            if (fragment instanceof ObrasFragment) {
//                ((FragmentAudioMessage) fragment).initPlay();
//            } else {
//                //do something else.
//            }
//        }

        drawer.closeDrawer(GravityCompat.START);
    }

    private void actionFloatButton(Fragment f){
        switch (f.getClass().getSimpleName()){
            case "ObrasFragment":
                ObrasFragment.actionFloatButtonObras();
                break;
            default:
                Toast.makeText(context, "" + f.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void eliminarUsuario(final Realm realm){
        transaction = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Usuario> usuarios = bgRealm.where(Usuario.class).findAll();
                usuarios.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "SUCCESS REALM CERRAR SESSION", Toast.LENGTH_SHORT).show();
                realm.close();
                materialDialogHelp.showOrDismissIndeterninate(null,null);
                mostrarLogin();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                Toast.makeText(context, "ERROR REALM CERRAR SESSION", Toast.LENGTH_SHORT).show();
                materialDialogHelp.showOrDismissIndeterninate(null,null);
                realm.close();
            }
        });
    }

    private void processLogout(){
        materialDialogHelp.showOrDismissIndeterninate(null,"Cerrando Session");
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<Usuario> usuarios = realm.where(Usuario.class).findAll();
        String id = usuarios.get(0).id;
        Call<LoginResponse> login = ApiAdapter.getApiService().processLogout(id);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.error) {

                        Toast.makeText(context, " " + loginResponse.message + " " + loginResponse.usuario, Toast.LENGTH_SHORT).show();
                        materialDialogHelp.showOrDismissIndeterninate(null,null);
                    }else{
                        Toast.makeText(context, "API CERRO SESION", Toast.LENGTH_SHORT).show();
                        eliminarUsuario(realm);
                    }
                }else{
                    Toast.makeText(context, "ERROR EN FORMATO DE RESPUESTA", Toast.LENGTH_SHORT).show();
                    materialDialogHelp.showOrDismissIndeterninate(null,null);
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                materialDialogHelp.showOrDismissIndeterninate(null,null);
            }
        });
    }

    private void mostrarLogin(){
        intentlogin = new Intent(context, LoginActivity.class);
        finishAffinity();
        startActivity(intentlogin);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "STAR MAIN ACTIVITY", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        realmStop();
        Toast.makeText(context, "STOP MAIN ACTIVITY", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmStop();
        Toast.makeText(context, "DESTROY MAIN ACTIVITY", Toast.LENGTH_SHORT).show();
    }


    private void realmStop(){
        if (realm != null) {
            realm.close();
            Toast.makeText(context, " realm  close onDestroy ", Toast.LENGTH_SHORT).show();
        }
        if (transaction != null && !transaction.isCancelled()) {
            Toast.makeText(context, " transaction cancelada onDestroy", Toast.LENGTH_SHORT).show();
            transaction.cancel();
        }
    }
}
