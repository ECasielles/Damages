package com.example.usuario.damages.ui.interactor;

import android.os.AsyncTask;

import com.example.usuario.damages.data.DamageRepository;
import com.example.usuario.damages.data.db.model.Damage;

import java.util.ArrayList;

/**
 * Clase Interactor de DamageListFragment que maneja
 * la importación de datos a la vista.
 * @see com.example.usuario.damages.ui.interactor.DamageListInteractor
 * @see com.example.usuario.damages.ui.interactor.DamageListInteractor.OnDamageListLoaded
 */
public class DamageListInteractorImpl implements DamageListInteractor {

    private OnDamageListLoaded listener;

    public DamageListInteractorImpl(OnDamageListLoaded listener) {
        this.listener = listener;
    }

    @Override
    public void loadDamages() {
        new MyTask(listener).execute();
    }

    @Override
    public void deleteDamage(Damage damage) {
        DamageRepository.getInstance().deleteDamage(damage);
    }

    /**
     * Clase interna que maneja la carga asíncrona de datos
     * a la vista DamageListFragment.
     * @see android.os.AsyncTask
     * @see DamageRepository
     */
    private static class MyTask extends AsyncTask<Void, Void, Void> {

        private OnDamageListLoaded listener;
        private ArrayList<Damage> damages;

        public MyTask(OnDamageListLoaded listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            damages = DamageRepository.getInstance().getDamages();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onDamageListLoaded(damages);
        }
    }

}
