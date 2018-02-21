package com.example.usuario.damages.ui.presenter;

import com.example.usuario.damages.data.db.model.City;
import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.ui.contract.DamageViewContract;
import com.example.usuario.damages.ui.fragment.DamageViewFragment;
import com.example.usuario.damages.ui.interactor.DamageViewInteractor;
import com.example.usuario.damages.ui.interactor.DamageViewInteractorImpl;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/18.
 */
public class DamageViewPresenter implements DamageViewContract.Presenter,
        DamageViewInteractor.OnDamageViewInteractorLoaded {
    private final DamageViewInteractor interactor;
    private DamageViewContract.View view;

    public DamageViewPresenter(DamageViewContract.View view) {
        this.view = view;
        this.interactor = new DamageViewInteractorImpl(this);
    }

    @Override
    public void addNewDamage(Damage damage) {
        interactor.addNewDamage(damage);
    }

    @Override
    public void updateDamage(Damage damage) {
        interactor.updateDamage(damage);
    }

    @Override
    public void loadCities() {
        interactor.loadCities();
    }

    @Override
    public void onCitiesLoaded(ArrayList<City> cities) {
        view.onCitiesLoaded(cities);
    }

    @Override
    public void onNewDamageAdded() {
        view.onNewDamageAdded();
    }

    @Override
    public void onAddError(Throwable error) {
        view.onAddError(error);
    }
}
