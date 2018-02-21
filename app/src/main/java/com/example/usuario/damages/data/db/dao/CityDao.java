package com.example.usuario.damages.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.damages.data.db.DamageContract;
import com.example.usuario.damages.data.db.DamageOpenHelper;
import com.example.usuario.damages.data.db.model.City;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/18.
 */

public class CityDao {

    public ArrayList<City> loadAll() {
        ArrayList<City> cities = new ArrayList<>();
        SQLiteDatabase database = DamageOpenHelper.getInstance().openDatabase();

        Cursor cursor = database.query(
                DamageContract.CityEntries.TABLE,
                DamageContract.CityEntries.ALL_COLUMNS,
                null, null, null, null,
                DamageContract.CityEntries.ORDER
        );
        if(cursor.moveToFirst())
            do {
                cities.add(new City(
                        cursor.getInt(0),
                        cursor.getString(1)
                ));
            } while (cursor.moveToNext());
        cursor.close();
        DamageOpenHelper.getInstance().closeDatabase();
        return cities;
    }
}
