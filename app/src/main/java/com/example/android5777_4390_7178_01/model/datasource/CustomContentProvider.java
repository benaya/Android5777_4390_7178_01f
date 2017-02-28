package com.example.android5777_4390_7178_01.model.datasource;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.model.backend.IDSManager;
import com.example.android5777_4390_7178_01.model.backend.ManagerFactory;
import com.example.android5777_4390_7178_01.model.entities.Attractions;
import com.example.android5777_4390_7178_01.model.entities.Business;
import com.example.android5777_4390_7178_01.model.entities.Manager;

import java.util.ArrayList;

public class CustomContentProvider extends ContentProvider {
    IDSManager manager = ManagerFactory.getManager();
    final String TAG = "Travels";

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete " + uri.toString());

        return 0;
    }

    @Override
    public String getType(Uri uri) {

        Log.d(TAG, "getType " + uri.toString());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Log.d("TAG", "insert " + uri.toString());

        String listName = uri.getLastPathSegment();
        //long id = -1;
        switch (listName) {
            case "manager":
                manager.addManager(values);
              //  return ContentUris.withAppendedId(uri, id);

            case "business":
                manager.addBusiness(values);

                //return ContentUris.withAppendedId(uri, id);

            case "attraction":
                manager.addAttraction(values);

            //    return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Log.d(TAG, "query " + uri.toString());

        String listName = uri.getLastPathSegment();

        switch (listName) {
            case "manager":
                return manager.getManager();//

            case "business":
                return manager.getBusiness();//

            case "attraction":
                return manager.getAttraction();//

        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update " + uri.toString());

        return 0;
    }
}


/*
    public static IDSManager dsManager = ManagerFactory.getDSManger("ListDsManager");

    static final String PROVIDER_NAME = "com.example.android5777_4390_7178_01.model.entities";
    //  static final String URL = "content://" + PROVIDER_NAME + "/cte";
    public static final Uri MANAGER_CONTENT_URL = Uri.parse("content://" + PROVIDER_NAME + "/Manager");
    public static final Uri BUSINESS_CONTENT_URL = Uri.parse("content://" + PROVIDER_NAME + "/Business");
    public static final Uri ATTRACTION_CONTENT_URL = Uri.parse("content://" + PROVIDER_NAME + "/Attraction");

   // public static final String TAG= "enterTaimentContent";

    static final int URI_MANAGER = 1;
    static final int URI_BUSINESS = 2;
    static final int URI_ATTRACTIONS = 3;

    // private static HashMap <String , String> valuse;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"manager",URI_MANAGER);
        uriMatcher.addURI(PROVIDER_NAME,"business",URI_BUSINESS);
        uriMatcher.addURI(PROVIDER_NAME,"activities",URI_ATTRACTIONS);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri))
        {
            case URI_MANAGER:
                dsManager.addManager(values);
                break;
            case URI_BUSINESS:
                dsManager.addBusiness(values);
                break;
            case URI_ATTRACTIONS:
                dsManager.addAttraction(values);
                break;
        }
        // TODO: Implement this to handle requests to insert a new row.
        //   throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        try {
            int uriCode = uriMatcher.match(uri);

            switch (uriCode) {
                case 1: // users
                    ArrayList<Manager> users = dsManager.getManagerList();
                    MatrixCursor userMatrix = new MatrixCursor(new String[]{"name", "password", "numberUser"});
                    for (Manager user : users) {
                        userMatrix.addRow(new Object[]{user.getName(), user.getPassword(), user.getUserNumber()});
                    }
                    return userMatrix;


                case 2: // business
                    ArrayList<Business> businesses = dsManager.getBusinessList();
                    //        Business business = new Business(contentBusiness.getAsInteger("ID"), contentBusiness.getAsString("CompanyName"), contentBusiness.getAsString("Address"), contentBusiness.getAsString("Mail"), contentBusiness.getAsString("PhoneNumber"), contentBusiness.getAsInteger("UserId"));

                    MatrixCursor businessMatrix = new MatrixCursor(new String[]{"IdBusiness", "businessName", "AdStreet","Adcountry","AdCity",
                            "phoneNumber", "Email","WebSite"});
                    for (Business business : businesses) {
                        businessMatrix.addRow(new Object[]{business.getIDbusines(), business.getNameBusines(), business.getAd_street(),
                                business.getAd_country(), business.getAd_city(),business.getEmail(),business.getPhone(),business.getwebSite()});
                    }
                    return businessMatrix;


                case 3: // attraction
                    ArrayList<Attractions> attractions = dsManager.getAttraction();
                    MatrixCursor AttractionMatrix = new MatrixCursor(new String[]{"IDbusines", "type","country", "activiytStart","activiytEnd", "description", "price"});
                    for (Attractions attraction : attractions) {
                        AttractionMatrix.addRow(new Object[]{attraction.getIDbusines(), attraction.getTypes(), attraction.getCountry(), attraction.getActivityStart(),
                                attraction.getActivityEnd(), attraction.getDescription(), attraction.getPrice()});
                    }

                    return AttractionMatrix;
                default:
                    throw new IllegalArgumentException("invalid query, no such path.");
            }
        } catch (Exception ex) {
            Log.d("TAG", ex.getMessage());
            return null;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
        //  throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //  throw new UnsupportedOperationException("Not yet implemented");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //    throw new UnsupportedOperationException("Not yet implemented");
        return 0;
    }*/


