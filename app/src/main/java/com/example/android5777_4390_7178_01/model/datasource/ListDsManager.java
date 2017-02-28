package com.example.android5777_4390_7178_01.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;

import com.example.android5777_4390_7178_01.model.entities.Activity_type;
import com.example.android5777_4390_7178_01.model.backend.IDSManager;
import com.example.android5777_4390_7178_01.model.entities.Attractions;
import com.example.android5777_4390_7178_01.model.entities.Business;
import com.example.android5777_4390_7178_01.model.entities.Manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by יונתן on 08/12/2016.
 */

public class ListDsManager implements IDSManager {


    final ArrayList<Manager> managerList = new ArrayList<Manager>();
    final ArrayList<Business> businessList = new ArrayList<Business>();
    final ArrayList<Attractions> attractionsesList = new ArrayList< Attractions>();
    boolean checkUpBussines = false;
    boolean checkUpAttraction = false;

    @Override
    public void addManager(ContentValues contant_manager)  {

        try {
            managerList.add(new Manager(contant_manager.getAsLong("userNumber"), contant_manager.getAsString("password"),
                    contant_manager.getAsString("userName")));
            Log.d("TAG", "user added");
        }
        catch (Exception e) {
            Log.d("TAG", "user not added");
        }
    }

    @Override
    public void addBusiness(ContentValues contant_business){
        try
        {

      /* businessList.add(new Business(contant_business.getAsLong(TravelContent.Business.business_id)
               ,contant_business.getAsString(TravelContent.Business.business_name),
               contant_business.getAsString(TravelContent.Business.business_street)
               ,contant_business.getAsString(TravelContent.Business.business_country),
               contant_business.getAsString(TravelContent.Business.business_city),
               contant_business.getAsInteger(TravelContent.Business.business_phone),
               contant_business.getAsString(TravelContent.Business.business_email),
               contant_business.getAsString(TravelContent.Business.business_webSite)));*/
            Business buss = TravelContent.ContentValuesToBusiness(contant_business);
            businessList.add(buss);
            checkUpBussines = true;
            //  return student.getId();
        }
        catch (Exception e) {
            Log.d("TAG", "bussines not added");
        }
    }

    @Override
    public void addAttraction(ContentValues contant_attraction) {
        // SimpleDateFormat dateAttraction = new SimpleDateFormat("DD/MM/YYYY", Locale.ENGLISH);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
        String dateStart = contant_attraction.getAsString(TravelContent.Attraction.activity_TStart);
        String dateEnd = contant_attraction.getAsString(TravelContent.Attraction.activity_TEnd);
        try {
           /* Activity_type activity_type = Activity_type.valueOf(contant_attraction.getAsString(TravelContent.Attraction.activity_type));

            attractionsesList.add(new Attractions(contant_attraction.getAsInteger("_id"),
                    activity_type,
                    contant_attraction.getAsString(TravelContent.Attraction.activity_country),
                    dateFormat.parse(dateStart),
                    dateFormat.parse(dateEnd),
                    contant_attraction.getAsInteger(TravelContent.Attraction.activity_price),
                    contant_attraction.getAsString(TravelContent.Attraction.activity_description),
                    contant_attraction.getAsLong(TravelContent.Attraction.activity_id)
            ));*/
            Attractions att = TravelContent.ContentValuesToAttraction(contant_attraction);
            attractionsesList.add(att);
            checkUpAttraction = true;
            //    Log.d("TAG", "Attraction added");
        } catch (Exception ex) {
            //   Log.d("TAG", "Attraction not added");
        }
    }

    @Override
    public Cursor getManager() {
        String[] columns = new String[]
                {
                        TravelContent.Manager.user_name,
                        TravelContent.Manager.user_number,
                        TravelContent.Manager.user_password,
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Manager m : managerList) {
            matrixCursor.addRow(new Object[]
                    {
                            m.getUserNumber(),
                            m.getName(),
                            m.getPassword()
                    });
        }
        return matrixCursor;
    }

    @Override
    public Cursor getBusiness(){
        String[] columns = new String[]
                {
                        TravelContent.Business.business_id,
                        TravelContent.Business.business_name,
                        TravelContent.Business.business_street,
                        TravelContent.Business.business_country,
                        TravelContent.Business.business_city,
                        TravelContent.Business.business_phone,
                        TravelContent.Business.business_email,
                        TravelContent.Business.business_webSite
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Business b : businessList) {
            matrixCursor.addRow(new Object[]
                    {
                            b.getIDbusines(),
                            b.getNameBusines(),
                            b.getAd_street(),
                            b.getAd_country(),
                            b.getAd_city(),
                            b.getPhone(),
                            b.getEmail(),
                            b.getwebSite()
                    });
        }

        return matrixCursor;
    }
    @Override
    public Cursor getAttraction() {
        String[] columns = new String[]
                {
                        TravelContent.Attraction.ID_activity,
                        TravelContent.Attraction.activity_type,
                        TravelContent.Attraction.activity_country,
                        TravelContent.Attraction.activity_TStart,
                        TravelContent.Attraction.activity_TEnd,
                        TravelContent.Attraction.activity_price,
                        TravelContent.Attraction.activity_description
                        //   TravelContent.Attraction.activity_id
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Attractions a : attractionsesList) {
            matrixCursor.addRow(new Object[]
                    {
                            a.getIdActivity(),
                            a.getTypes(),
                            a.getCountry(),
                            a.getActivityEnd(),
                            a.getActivityEnd(),
                            a.getPrice(),
                            a.getDescription()
                            //    a.getIDbusines()
                    });
        }
        return matrixCursor;
    }

    public void updateBusInListDB(Cursor b)
    {
        try {
            b.moveToFirst();
            while ((!b.isAfterLast()))//add all busines
            {
                businessList.add(new Business(b.getLong(0),b.getString(1),b.getString(2),b.getString(3),b.getString(4),b.getInt(5),
                        b.getString(6),b.getString(7)));
                b.moveToNext();
            }
            Log.d("DB", "bussines updated");
            b.close();
        }
        catch (Exception e) {
            Log.d("DB", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public boolean checkChanges() {
        if (checkUpBussines || checkUpAttraction)
        {
            checkUpBussines = false;
            checkUpAttraction = false;
            return true;
        }
        return false;
    }


    public boolean isBusinessChanged()throws Exception
    {return true;};
    public boolean isActivityChanged()throws Exception
    {return true;};
}
