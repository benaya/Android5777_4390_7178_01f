package com.example.android5777_4390_7178_01.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;

import com.example.android5777_4390_7178_01.model.backend.IDSManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.android5777_4390_7178_01.model.datasource.PHPTools.GET;
import static com.example.android5777_4390_7178_01.model.datasource.PHPTools.POST;


/**
 * Created by יונתן on 08/01/2017.
 */

public class MySQL_DSManager implements IDSManager {

    private final String UserName = "benaya";
    private final String WEB_URL = "http://"+UserName+".vlab.jct.ac.il";

    private static String BusinessLastDateUpdated ="";
    private static String AttractionLastDateUpdated = "";
    private boolean updateFlag = false;

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }

    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }

    @Override
    public void addManager(ContentValues values) {
        try {
            String result = POST(WEB_URL + "/Manager.php", values);
            long id = Long.parseLong(result);
            if (id > 0)
                SetUpdate();
            printLog("addManager:\n" + result);
        } catch (IOException e) {
            printLog("addStudent Exception:\n" + e);
        }
    }

    @Override
    public void addBusiness(ContentValues values) {
        try {
            String result = POST(WEB_URL + "/businesss.php", values);
            //  long id = Long.parseLong(result);
            //   if (id > 0)
            SetUpdate();
            printLog("addBusiness:\n" + result);
            businessUpdateUpdatTable();
        } catch (IOException e) {
            printLog("addBusiness Exception:\n" + e);
        }
    }

    @Override
    public void addAttraction(ContentValues values) {
        try {
            String result = POST(WEB_URL + "/attraction.php", values);
            //   long id = Long.parseLong(result);
            //   if (id > 0)
            SetUpdate();
            // Log.d("TAG", "php att good");
            printLog("addAttraction:\n" +result);
            AttractionUpdateUpdatTable();
        } catch (IOException e) {
            printLog("addAttraction:\n" +e);
        }
    }

    public void forgotMail(ContentValues values) {
        try {
            String result = POST(WEB_URL + "/mail.php", values);
            //   long id = Long.parseLong(result);
            //   if (id > 0)
            SetUpdate();
            Log.d("TAG", "php mail good");
            printLog("mail:\n" +result);
        } catch (IOException e) {
            printLog("mail:\n" +e);
        }
    }

    @Override
    public Cursor getManager() {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]
                    {
                            TravelContent.Manager.user_name,
                            TravelContent.Manager.user_number,
                            TravelContent.Manager.user_password,
                    });
            String str = GET(WEB_URL + "/get_manager.php");
            JSONArray array = new JSONObject(str).getJSONArray("manager");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getString(TravelContent.Manager.user_number),
                        jsonObject.getString(TravelContent.Manager.user_name),
                        jsonObject.getString(TravelContent.Manager.user_password),
                });
            }
            return matrixCursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cursor getBusiness() {
        String[] columns = new String[]
                {
                        TravelContent.Business.business_id,
                        TravelContent.Business.business_name,
                        TravelContent.Business.business_street,
                        TravelContent.Business.business_country,
                        TravelContent.Business.business_city,
                        TravelContent.Business.business_phone,
                        TravelContent.Business.business_email,
                        TravelContent.Business.business_webSite,
                };


        MatrixCursor matrixCursor = new MatrixCursor(columns);

        try {
            String str = GET(WEB_URL + "/get_business.php");
            JSONArray array = new JSONObject(str).getJSONArray("business");

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getLong(TravelContent.Business.business_id),
                        jsonObject.getString(TravelContent.Business.business_name),
                        jsonObject.getString(TravelContent.Business.business_street),
                        jsonObject.getString(TravelContent.Business.business_country),
                        jsonObject.getString(TravelContent.Business.business_city),
                        jsonObject.getInt(TravelContent.Business.business_phone),
                        jsonObject.getString(TravelContent.Business.business_email),
                        jsonObject.getString(TravelContent.Business.business_webSite)
                });
            }

            return matrixCursor;
        } catch (Exception e) {
            Log.d("TAG","ex get business");
            return null;
        }
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
                        TravelContent.Attraction.activity_description,
                        //    TravelContent.Attraction.activity_id
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);
        try {
            String str = GET(WEB_URL + "/get_attraction.php");
            JSONArray array = new JSONObject(str).getJSONArray("attractions");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getInt(TravelContent.Attraction.ID_activity),
                        jsonObject.getString(TravelContent.Attraction.activity_type),
                        jsonObject.getString(TravelContent.Attraction.activity_country),
                        jsonObject.getString(TravelContent.Attraction.activity_TStart),
                        jsonObject.getString(TravelContent.Attraction.activity_TEnd),
                        jsonObject.getInt(TravelContent.Attraction.activity_price),
                        jsonObject.getString(TravelContent.Attraction.activity_description)
                        //   jsonObject.getLong(TravelContent.Attraction.activity_id)
                });
            }
            return matrixCursor;
        } catch (Exception e) {
            Log.d("TAG","ex get att");
            return null;
        }
    }

    private void SetUpdate()
    {
        updateFlag = true;
    }

    @Override
    public boolean checkChanges() {
        if (updateFlag) {
            updateFlag = false;
            return true;
        }
        return false;
    }


    public boolean isBusinessChanged() throws Exception {
        boolean flag = false;
        String str = GET(WEB_URL + "/get_update_table.php");
        JSONArray array = new JSONObject(str).getJSONArray("UpdateTable");
      //  JSONArray array = new JSONObject(GET(WEB_URL + "/get_update_table.php")).getJSONArray("UpdateTable");
        JSONObject updateTable=array.getJSONObject(0);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH);
        Date serverLastDate = format.parse(updateTable.getString("Business"));
        Date appLastDate =  format.parse(BusinessLastDateUpdated);
        if (!BusinessLastDateUpdated.equals(updateTable.getString("Business")) && serverLastDate.after(appLastDate))
        {
            BusinessLastDateUpdated = updateTable.getString("Business");
            flag = true;
        }
        return flag;
    }

    public boolean isActivityChanged() throws Exception {
        boolean flag = false;
        JSONArray array = new JSONObject(GET(WEB_URL + "/get_update_table.php")).getJSONArray("UpdateTable");
        JSONObject updateTable=array.getJSONObject(0);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.ENGLISH);
        Date serverLastDate = format.parse(updateTable.getString("Attraction"));
        Date appLastDate =  format.parse(AttractionLastDateUpdated);
        if (!AttractionLastDateUpdated.equals(updateTable.getString("Attraction")) && serverLastDate.after(appLastDate))
        {
            AttractionLastDateUpdated = updateTable.getString("Attraction");
            flag = true;
        }
        return flag;
    }

    public Cursor loginAction(){
        Cursor c = null;
        try {
            c = getManager();
            lastBusinessChanged();
            lastActivityChanged();
        }catch (Exception e)
        {
            Log.d("TAG","error with: " + e);
        }
        return c;
    }
    /**
     * for the first login - update the last business changed
     * @throws Exception
     */
    public void lastBusinessChanged() throws Exception {
        JSONArray array = new JSONObject(GET(WEB_URL + "/get_update_table.php")).getJSONArray("UpdateTable");
        JSONObject updateTable=array.getJSONObject(0);
        BusinessLastDateUpdated = updateTable.getString("Business");
    }

    /**
     * for the first login - update the last activities changed
     * @throws Exception
     */
    public void lastActivityChanged() throws Exception {
        JSONArray array = new JSONObject(GET(WEB_URL + "/get_update_table.php")).getJSONArray("UpdateTable");
        JSONObject updateTable=array.getJSONObject(0);
        AttractionLastDateUpdated = updateTable.getString("Attraction");
    }

    private Date _string_to_date(String str) throws ParseException {
        // get string stringDate and return Date
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return dt.parse(str);
    }

    public String _date_to_string(Date date) {
        // get date and return string
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return dt.format(date);
    }

    private void businessUpdateUpdatTable()
    {
        try
        {
         //   Map<String, Object> params = new LinkedHashMap<>();

            String currentTime=_date_to_string(new Date());
            ContentValues params = new ContentValues();
            params.put("BusinessUpdateTime",currentTime.toString());

            String results = POST(WEB_URL + "/update_business_updateTable.php", params);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 2).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(2));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void AttractionUpdateUpdatTable()
    {
        try
        {
         //   Map<String, Object> params = new LinkedHashMap<>();
            ContentValues params = new ContentValues();

            String currentTime=_date_to_string(new Date());
            params.put("AttractionUpdateTime",currentTime);


            String results = POST(WEB_URL + "/update_attractions_updateTable.php", params);
            if (results.equals("")) {
                throw new Exception("An error occurred on the server's side");
            }
            if (results.substring(0, 2).equalsIgnoreCase("error")) {
                throw new Exception(results.substring(2));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
