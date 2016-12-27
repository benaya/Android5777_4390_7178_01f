package com.example.android5777_4390_7178_01.model.datasource;

import android.content.ContentValues;
import android.util.Log;

import com.example.android5777_4390_7178_01.model.backend.IDSManager;
import com.example.android5777_4390_7178_01.model.entities.Attractions;
import com.example.android5777_4390_7178_01.model.entities.Business;
import com.example.android5777_4390_7178_01.model.entities.Manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by יונתן on 08/12/2016.
 */

public class ListDsManager implements IDSManager {


    final ArrayList<Manager> managerList = new ArrayList<Manager>();
    final ArrayList<Business> businessList = new ArrayList<Business>();
    final ArrayList<Attractions> attractionsesList = new ArrayList< Attractions>();

    @Override
    public void addManager(ContentValues contant_manager)  {
        managerList.add(new Manager(contant_manager.getAsLong("userNumber"),contant_manager.getAsString("password"),
                contant_manager.getAsString("userName")));
        Log.d("TAG","user added");
      //  Toast.makeText(ListDsManager.this,"user added",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addBusiness(ContentValues contant_business){
       businessList.add(new Business(contant_business.getAsLong("IdBusiness"),contant_business.getAsString("businessName"),
               contant_business.getAsString("AStreet"),contant_business.getAsString("ACountry"), contant_business.getAsString("ACity"),
               contant_business.getAsInteger("Phone"),contant_business.getAsString("Email"),contant_business.getAsString("WebSite")));
    }

    @Override
    public void addAttraction(ContentValues contant_attraction) {
        SimpleDateFormat dateAttraction = new SimpleDateFormat("DD/MM/YYYY", Locale.ENGLISH);

        Calendar dateS = dateAttraction.getCalendar();
        Calendar dateE = dateAttraction.getCalendar();

        try {
            dateS.setTime(dateAttraction.parse(contant_attraction.getAsString("startDate")));
            dateE.setTime(dateAttraction.parse(contant_attraction.getAsString("endDate")));

           /*   attractionsesList.add(new Attractions(contant_attraction.getAsString("type"),contant_attraction.getAsString("country")
                ,dateS, dateE,contant_attraction.getAsInteger("price"),contant_attraction.getAsString("description"),
                contant_attraction.getAsLong("idBussines")
                      ));*/
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public ArrayList<Manager> getManagerList() {
        return null;
    }

    @Override
    public ArrayList<Business> getBusinessList() {
        return null;
    }

    @Override
    public ArrayList<Attractions> getAttraction() {
        return null;
    }


    @Override
    public Boolean checkChanges() {
        return null;
    }

    @Override
    public void reportChanges() {

    }
}
