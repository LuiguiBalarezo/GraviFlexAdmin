package com.scripgo.www.admingraviflex.help;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by BALAREZO on 29/07/2017.
 */

public class MaterialDialogHelp {

    private MaterialDialog materialDialog = null;
    private Context context = null;

    public MaterialDialogHelp(Context ctx) {
        context = ctx;
    }

    public void showOrDismissIndeterninate(String texttitle, String textcontent){
        if(materialDialog == null){
            materialDialog = new MaterialDialog.Builder(context)
                    .autoDismiss(false)
                    .cancelable(false)
                    .title(texttitle)
                    .content(textcontent)
                    .progress(true, 0)
                    .progressIndeterminateStyle(true).build();
            materialDialog.show();
        }else{
            materialDialog.dismiss();
            materialDialog = null;
        }
    }


}
