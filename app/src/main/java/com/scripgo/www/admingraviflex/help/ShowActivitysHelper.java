package com.scripgo.www.admingraviflex.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by BALAREZO on 30/07/2017.
 */

public class ShowActivitysHelper {
    private Context context = null;
    private Intent intent = null;
    private Activity activity = null;
    public ShowActivitysHelper(Context ctx, Activity ac) {
        context = ctx;
        activity =  ac;
    }

    public void startActivity(Class aClass){
        if (intent == null){
            intent = new Intent(context, aClass);
            activity.finishAffinity();
            activity.startActivity(intent);
        }else{
            intent = null;
        }

    }

}
