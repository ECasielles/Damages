package com.example.usuario.damages.ui.contract;

import com.example.usuario.damages.data.db.model.City;
import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/18.
 */

public interface DamageViewContract {

    interface View {
        void onNewDamageAdded();
        void onCitiesLoaded(ArrayList<City> cities);
        void onAddError(Throwable error);
    }
    interface Presenter {
        void loadCities();
        void addNewDamage(Damage damage);
        void updateDamage(Damage damage);
    }

}
