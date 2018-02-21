package com.example.usuario.damages.ui.interactor;

import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Interfaz para la comunicación entre la clase
 * interactor y el presenter de DamageListFragment
 */
public interface DamageListInteractor {

    void loadDamages();

    void deleteDamage(Damage damage);

    interface OnDamageListLoaded {
        void onDamageListLoaded(ArrayList<Damage> damages);
    }
}
