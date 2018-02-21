package com.example.usuario.damages.ui.interactor;

import com.example.usuario.damages.data.CityRepository;
import com.example.usuario.damages.data.DamageRepository;
import com.example.usuario.damages.data.db.model.Damage;


/**
 * Created by usuario on 21/02/18.
 */
public class DamageViewInteractorImpl implements DamageViewInteractor {

    private DamageViewInteractor.OnDamageViewInteractorLoaded listener;

    public DamageViewInteractorImpl(DamageViewInteractor.OnDamageViewInteractorLoaded listener) {
        this.listener = listener;
    }

    @Override
    public void loadCities() {
        listener.onCitiesLoaded(CityRepository.getInstance().getCities());
    }

    @Override
    public void addNewDamage(Damage damage) {
        DamageRepository.getInstance().addDamage(damage, this);
    }

    @Override
    public void onAddSuccess() {
        listener.onNewDamageAdded();
    }

    @Override
    public void onAddError(Throwable error) {
        listener.onAddError(error);
    }

    @Override
    public void updateDamage(Damage damage) {
        DamageRepository.getInstance().updateDamage(damage);
    }
}
