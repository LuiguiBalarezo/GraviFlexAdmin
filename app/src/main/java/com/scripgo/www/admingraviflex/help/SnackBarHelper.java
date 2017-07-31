package com.scripgo.www.admingraviflex.help;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.scripgo.www.admingraviflex.R;

/**
 * Created by BALAREZO on 30/07/2017.
 */

public class SnackBarHelper {
    private Snackbar mSnackbar = null;
    private View view = null;
    private View snackView = null;
    private Context context = null;
    public SnackBarHelper(View v, Context cntx) {
        view = v;
        context = cntx;
    }

    public void showOrDismissSnackBarError(String message){
        if (mSnackbar != null && mSnackbar.isShown()) {
            mSnackbar.dismiss();
            mSnackbar = null;
            snackView = null;
        } else {
            mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            snackView = mSnackbar.getView();
            snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_400));
            mSnackbar.show();
        }
    }

    public void showOrDismissSnackBarSuccess(String message){
        if (mSnackbar != null && mSnackbar.isShown()) {
            mSnackbar.dismiss();
            mSnackbar = null;
            snackView = null;
        } else {
            mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            snackView = mSnackbar.getView();
            snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.green_400));
            mSnackbar.show();
        }
    }

}
