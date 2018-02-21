package com.example.usuario.damages.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.usuario.damages.R;
import com.example.usuario.damages.ui.fragment.DamageListFragment;
import com.example.usuario.damages.ui.fragment.DamageViewFragment;

/**
 * Clase Activity que muestra el listado de datos de averías.
 * @see android.app.Activity
 * @see com.example.usuario.damages.ui.fragment.DamageListFragment.OnAddDamageListener
 */
public class DamageActivity extends AppCompatActivity implements
        DamageListFragment.OnAddDamageListener, DamageViewFragment.OnNewDamageAddedListener {

    DamageListFragment damageListFragment;
    DamageViewFragment damageViewFragment;
    Toolbar toolbar;
    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);

        damageListFragment = (DamageListFragment) getSupportFragmentManager().findFragmentByTag(DamageListFragment.TAG);
        if(damageListFragment == null) {
            damageListFragment = new DamageListFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_content, damageListFragment, DamageListFragment.TAG);
        transaction.commit();
    }

    @Override
    public void newDamage(Bundle args) {
        damageViewFragment = (DamageViewFragment) getSupportFragmentManager().findFragmentByTag(DamageViewFragment.TAG);
        if(damageViewFragment == null) {
            damageViewFragment = DamageViewFragment.newInstance(args);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, damageViewFragment, DamageViewFragment.TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void newDamageAdded() {
        getSupportFragmentManager().popBackStack();
    }
}
