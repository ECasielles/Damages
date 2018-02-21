package com.example.usuario.damages.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.damages.data.db.DamageContract;
import com.example.usuario.damages.data.db.DamageOpenHelper;
import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Clase DAO que realiza las operaciones con la BD
 */
public class DamageDao {

    public ArrayList<Damage> loadAll() {
        ArrayList<Damage> damages = new ArrayList<>();
        SQLiteDatabase database = DamageOpenHelper.getInstance().openDatabase();

        Cursor cursor = database.query(
                DamageContract.DamageEntries.TABLE,
                DamageContract.DamageEntries.ALL_COLUMNS,
                null, null, null, null,
                DamageContract.DamageEntries.ORDER
        );
        if(cursor.moveToFirst())
            do {
                damages.add(new Damage(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());
        cursor.close();
        DamageOpenHelper.getInstance().closeDatabase();
        return damages;
    }

    public long insert(Damage damage) {
        SQLiteDatabase database = DamageOpenHelper.getInstance().openDatabase();
        long id = database.insert(DamageContract.DamageEntries.TABLE,
                null, createContent(damage));
        DamageOpenHelper.getInstance().closeDatabase();
        return id;
    }

    private ContentValues createContent(Damage damage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DamageContract.DamageEntries.COL_CODE, damage.getCode());
        contentValues.put(DamageContract.DamageEntries.COL_CITY, damage.getCityId());
        contentValues.put(DamageContract.DamageEntries.COL_DATA, damage.getData());
        contentValues.put(DamageContract.DamageEntries.COL_DESCRIPTION, damage.getDescription());
        return contentValues;
    }

    public int delete(Damage damage) {

        String whereClause = DamageContract.DamageEntries.TABLE + "." + DamageContract.DamageEntries._ID + " = ?";
        String[] whereArgs = {String.valueOf(damage.getId())};

        SQLiteDatabase database = DamageOpenHelper.getInstance().openDatabase();
        int rows = database.delete(DamageContract.DamageEntries.TABLE,
                whereClause, whereArgs);
        DamageOpenHelper.getInstance().closeDatabase();
        return rows;
    }

    public int update(Damage damage) {
        SQLiteDatabase database = DamageOpenHelper.getInstance().openDatabase();
        String whereClause = DamageContract.DamageEntries.TABLE + "." + DamageContract.DamageEntries._ID + " = ?";
        String[] whereArgs = {String.valueOf(damage.getId())};
        int rows = database.update(DamageContract.DamageEntries.TABLE, createContent(damage),
                whereClause, whereArgs);
        DamageOpenHelper.getInstance().closeDatabase();
        return rows;
    }
}
