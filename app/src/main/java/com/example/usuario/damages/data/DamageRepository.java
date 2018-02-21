package com.example.usuario.damages.data;

import android.content.res.Resources;

import com.example.usuario.damages.R;
import com.example.usuario.damages.data.db.dao.DamageDao;
import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.ui.interactor.DamageViewInteractor;

import java.util.ArrayList;

/**
 * Clase repositorio que guarda las referencias
 * a las distintas fuentes de datos y realiza
 * operaciones con las mismas.
 * @see DamageDao
 */
public class DamageRepository {

    private static DamageRepository damageRepository;
    private DamageDao damageDao;


    public DamageRepository() {
        this.damageDao = new DamageDao();
    }

    public static DamageRepository getInstance() {
        if(damageRepository == null)
            damageRepository = new DamageRepository();
        return damageRepository;
    }

    public ArrayList<Damage> getDamages() {
        return damageDao.loadAll();
    }

    public void addDamage(Damage damage, DamageViewInteractor interactor) {
        if(damageDao.insert(damage) != -1)
            interactor.onAddSuccess();
        else
            interactor.onAddError(new Throwable(Resources.getSystem().getString(R.string.error_db_add)));
    }

    public void deleteDamage(Damage damage) {
        damageDao.delete(damage);
    }

    public void updateDamage(Damage damage) {
        damageDao.update(damage);
    }
}
