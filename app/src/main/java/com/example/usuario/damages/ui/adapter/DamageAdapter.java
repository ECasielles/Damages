package com.example.usuario.damages.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.R;

import java.util.ArrayList;

/**
 * Clase Adapter que maneja la colección de datos de la vista
 * DamageListFragment.
 * @see android.support.v7.widget.RecyclerView.Adapter
 * @see com.example.usuario.damages.ui.fragment.DamageListFragment
 */
public class DamageAdapter extends RecyclerView.Adapter<DamageAdapter.ViewHolder> {

    private ArrayList<Damage> damages;
    private Context context;
    private OnItemActionListener listener;

    public DamageAdapter(Context context, OnItemActionListener listener) {
        this.context = context;
        this.listener = listener;
        damages = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_damage, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txvCode.setText(damages.get(position).getCode());
        holder.txvDescription.setText(damages.get(position).getDescription());
        holder.bind(listener, damages.get(position));
    }

    @Override
    public int getItemCount() {
        return damages.size();
    }

    public void addAll(ArrayList<Damage> newDamages) {
        if(newDamages != null) {
            damages.clear();
            damages.addAll(newDamages);
            notifyItemRangeChanged(0, getItemCount());
        }
    }

    /**
     * Clase ViewHolder interna que maneja la vista de un elemento único
     * dentro del RecyclerView y se comunica con la vista mediante
     * la interfaz OnItemActionListener.
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     * @see OnItemActionListener
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvCode, txvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            txvCode = itemView.findViewById(R.id.txvCode);
            txvDescription = itemView.findViewById(R.id.txvDescription);
        }

        public void bind(final OnItemActionListener listener, final Damage damage) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(damage);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(damage);
                    return true;
                }
            });
        }

    }
}
