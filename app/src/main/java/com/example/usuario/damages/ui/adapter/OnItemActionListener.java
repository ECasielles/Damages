package com.example.usuario.damages.ui.adapter;

import com.example.usuario.damages.data.db.model.Damage;

/**
 * Interfaz que controla los eventos entre la vista
 * DamageListFragment y su adapter DamageAdapter
 * @see com.example.usuario.damages.ui.fragment.DamageListFragment
 * @see DamageAdapter
 */
public interface OnItemActionListener {

    void onItemLongClick(Damage damage);

    void onItemClick(Damage damage);

}
