package com.example.usuario.damages.data.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.usuario.damages.DamageApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Administra y encapsula el acceso a la base de datos.
 * @see android.database.sqlite.SQLiteOpenHelper
 * @see DamageApplication
 */
public class DamageOpenHelper extends SQLiteOpenHelper {
    public static final String TAG = "DamageOpenHelper";
    private volatile static DamageOpenHelper helper;
    private static SQLiteDatabase database;
    private AtomicInteger openCounter = new AtomicInteger();

    public DamageOpenHelper() {
        super(DamageApplication.getContext(), DamageContract.DATABASE_NAME, null, DamageContract.DATABASE_VERSION);
    }

    public synchronized static DamageOpenHelper getInstance() {
        if(helper == null)
            helper = new DamageOpenHelper();
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL(DamageContract.DamageEntries.CREATE_TABLE);
            db.execSQL(DamageContract.DamageEntries.INSERT_ENTRIES);
            db.execSQL(DamageContract.CityEntries.CREATE_TABLE);
            db.execSQL(DamageContract.CityEntries.INSERT_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL(DamageContract.DamageEntries.DROP_TABLE);
            db.execSQL(DamageContract.CityEntries.DROP_TABLE);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL(DamageContract.DamageEntries.DROP_TABLE);
            db.execSQL(DamageContract.CityEntries.DROP_TABLE);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public synchronized SQLiteDatabase openDatabase() {
        if(openCounter.getAndIncrement() == 1)
            database = getWritableDatabase();
        return database;
    }

    public synchronized void closeDatabase() {
        if(openCounter.getAndDecrement() == 0)
            database.close();
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if(db.isReadOnly())
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=1");
    }

}
