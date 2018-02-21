package com.example.usuario.damages.ui.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.usuario.damages.R;
import com.example.usuario.damages.data.db.model.City;
import com.example.usuario.damages.data.db.model.Damage;
import com.example.usuario.damages.ui.activity.DamageActivity;
import com.example.usuario.damages.ui.contract.DamageViewContract;
import com.example.usuario.damages.ui.presenter.DamageViewPresenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Contiene la vista de creación y edición de una nueva avería
 */
public class DamageViewFragment extends Fragment
        implements DamageViewContract.View {

    public static final String TAG = "DamageViewFragment";

    private OnNewDamageAddedListener callback;
    private DamageViewContract.Presenter presenter;
    private EditText edtCode, edtData, edtDescription;
    private Spinner spnCities;
    private SpinnerAdapter spinnerAdapter;
    private Button btnDate;
    private boolean updating = false;
    private int damageId = -1;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (OnNewDamageAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity + " must implement OnNewDamageAddedListener interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new DamageViewPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_damage, container, false);
        edtCode = view.findViewById(R.id.edtCode);
        edtDescription = view.findViewById(R.id.edtDescription);
        edtData = view.findViewById(R.id.edtDate);
        edtData.setEnabled(false);
        spnCities = view.findViewById(R.id.spCity);
        btnDate = view.findViewById(R.id.btnDate);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((DamageActivity)getActivity()).fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtCode.getText().toString().equals("") &&
                        !edtData.getText().toString().equals("") &&
                        !edtDescription.getText().toString().equals("")) {
                    String code = edtCode.getText().toString();
                    String data = edtData.getText().toString();
                    String description = edtDescription.getText().toString();
                    int city = spnCities.getSelectedItemPosition() + 1;
                    if(updating)
                        presenter.updateDamage(new Damage(damageId, code, city, data, description));
                    presenter.addNewDamage(new Damage(damageId, code, city, data, description));
                } else {
                    showMessage(getContext().getResources().getString(R.string.error_empty_field));
                }
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog;
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        Date date = calendar.getTime();
                        String dateString = dateFormat.format(date);
                        edtData.setText(dateString);
                    }
                };
                Calendar cal = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(
                        getContext(), listener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        presenter.loadCities();
        spnCities.setAdapter(spinnerAdapter);

        if(getArguments() != null) {
            Damage damage = getArguments().getParcelable(Damage.TAG);
            edtData.setText(damage.getData());
            edtCode.setText(damage.getCode());
            edtDescription.setText(damage.getDescription());
            spnCities.setSelection(damage.getCityId());
            damageId = damage.getId();
            updating = true;
        } else {
            updating = false;
        }
    }

    @Override
    public void onNewDamageAdded() {
        callback.newDamageAdded();
    }

    @Override
    public void onCitiesLoaded(ArrayList<City> cities) {
        spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, cities);
    }

    public static DamageViewFragment newInstance(Bundle args) {
        DamageViewFragment damageViewFragment = new DamageViewFragment();
        if(args != null)
            damageViewFragment.setArguments(args);
        return damageViewFragment;
    }

    @Override
    public void onAddError(Throwable error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void showMessage(String string) {
        Snackbar.make(getView(), string, Snackbar.LENGTH_LONG).show();
    }

    public interface OnNewDamageAddedListener {
        void newDamageAdded();
    }

}
