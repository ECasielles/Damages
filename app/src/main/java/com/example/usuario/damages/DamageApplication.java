package com.example.usuario.damages;

import android.app.Application;
import android.content.Context;

import com.example.usuario.damages.data.db.DamageOpenHelper;

/**
 * Contiene la instancia de la aplicación.
 * @see android.app.Application
 */
public class DamageApplication extends Application {

    private static DamageApplication damageApplication;
    private static Context context;


    public DamageApplication() {
        context = this;
    }

    public static DamageApplication getInstance() {
        if(damageApplication == null)
            damageApplication = new DamageApplication();
        return damageApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DamageOpenHelper.getInstance().openDatabase();
    }

    public static Context getContext() {
        return context;
    }
}
