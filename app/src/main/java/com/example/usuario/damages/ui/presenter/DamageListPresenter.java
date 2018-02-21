package com.example.usuario.damages.ui.presenter;

import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.ui.contract.DamageListContract;
import com.example.usuario.damages.ui.fragment.DamageListFragment;
import com.example.usuario.damages.ui.interactor.DamageListInteractor;
import com.example.usuario.damages.ui.interactor.DamageListInteractorImpl;

import java.util.ArrayList;

/**
 * Presentador de la vista DamageListFragment
 * @see com.example.usuario.damages.ui.contract.DamageListContract.Presenter
 * @see DamageListFragment
 */
public class DamageListPresenter
        implements DamageListContract.Presenter, DamageListInteractor.OnDamageListLoaded {

    private DamageListInteractor interactor;
    private DamageListContract.View view;

    public DamageListPresenter(DamageListContract.View view) {
        this.view = view;
        this.interactor = new DamageListInteractorImpl(this);
    }

    @Override
    public void loadDamages() {
        view.loadingDamages();
        interactor.loadDamages();
    }

    @Override
    public void deleteDamage(Damage damage) {
        interactor.deleteDamage(damage);
    }

    @Override
    public void onDamageListLoaded(ArrayList<Damage> damages) {
        view.onDamagesLoaded(damages);
    }

}
