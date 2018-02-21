package com.example.usuario.damages.ui.contract;

import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Interfaz para la comunicación entre vista y presentador
 * de la clase DamageListFragment
 */
public interface DamageListContract {

    interface View {
        void loadingDamages();
        void onDamagesLoaded(ArrayList<Damage> damages);
    }

    interface Presenter {
        void loadDamages();
        void deleteDamage(Damage damage);
    }

}
