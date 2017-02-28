package com.example.android5777_4390_7178_01.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.android5777_4390_7178_01.model.entities.Attractions;
import com.example.android5777_4390_7178_01.model.entities.Business;
import com.example.android5777_4390_7178_01.model.entities.Manager;

import java.util.ArrayList;

/**
 * Created by יונתן on 01/12/2016.
 */

public interface IDSManager {

    void addManager(ContentValues contant_manager);
    void addBusiness(ContentValues contant_business);
    void addAttraction(ContentValues contant_attraction);

    Cursor getManager();
    Cursor getBusiness();
    Cursor getAttraction();

    public boolean isBusinessChanged()throws Exception;
    public boolean isActivityChanged()throws Exception;
    boolean checkChanges();
//    void reportChanges();

    ///////////////////----seif h--/////////////

}
