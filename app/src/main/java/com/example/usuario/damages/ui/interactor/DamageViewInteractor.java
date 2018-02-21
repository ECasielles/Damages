package com.example.usuario.damages.ui.interactor;

import com.example.usuario.damages.data.db.model.City;
import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/18.
 */

public interface DamageViewInteractor {

    void loadCities();
    void addNewDamage(Damage damage);
    void onAddSuccess();
    void onAddError(Throwable error);

    void updateDamage(Damage damage);

    interface OnDamageViewInteractorLoaded {
        void onCitiesLoaded(ArrayList<City> cities);
        void onNewDamageAdded();
        void onAddError(Throwable error);
    }

}
