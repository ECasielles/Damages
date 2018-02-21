package com.example.usuario.damages.data;

import com.example.usuario.damages.data.db.dao.CityDao;
import com.example.usuario.damages.data.db.model.City;

import java.util.ArrayList;

/**
 * Created by usuario on 21/02/18.
 */

public class CityRepository {

    private static CityRepository cityRepository;
    private CityDao cityDao;


    public CityRepository() {
        this.cityDao = new CityDao();
    }

    public static CityRepository getInstance() {
        if(cityRepository == null)
            cityRepository = new CityRepository();
        return cityRepository;
    }

    public ArrayList<City> getCities() {
        return cityDao.loadAll();
    }

}
