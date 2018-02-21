package com.example.usuario.damages.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.R;
import com.example.usuario.damages.ui.activity.DamageActivity;
import com.example.usuario.damages.ui.adapter.DamageAdapter;
import com.example.usuario.damages.ui.adapter.OnItemActionListener;
import com.example.usuario.damages.ui.contract.DamageListContract;
import com.example.usuario.damages.ui.presenter.DamageListPresenter;

import java.util.ArrayList;

/**
 * Fragment de la vista con el listado de averías.
 * @see android.support.v4.app.Fragment
 * @see OnItemActionListener
 * @see com.example.usuario.damages.ui.contract.DamageListContract.View
 */
public class DamageListFragment extends Fragment
        implements OnItemActionListener, DamageListContract.View {

    public static final String TAG = "DamageListFragment";
    private OnAddDamageListener callback;
    private RecyclerView recyclerView;
    private DamageAdapter adapter;
    private ProgressDialog progressDialog;
    private DamageListContract.Presenter presenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnAddDamageListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnAddDamageListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new DamageListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_damage, container, false);
        recyclerView = view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new DamageAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        presenter.loadDamages();

        ((DamageActivity)getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.newDamage(null);
            }
        });
    }

    @Override
    public void onItemLongClick(final Damage damage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getContext().getResources().getString(R.string.delete_damage))
                .setMessage(getContext().getResources().getString(R.string.delete_damage_message))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.deleteDamage(damage);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void onItemClick(Damage damage) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Damage.TAG, damage);
        callback.newDamage(bundle);
    }

    @Override
    public void loadingDamages() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
    }

    @Override
    public void onDamagesLoaded(ArrayList<Damage> damages) {
        progressDialog.dismiss();
        adapter.addAll(damages);
    }

    public interface OnAddDamageListener {
        void newDamage(Bundle args);
    }

}
