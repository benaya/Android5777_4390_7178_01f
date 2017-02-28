package com.example.android5777_4390_7178_01.model.datasource;

import android.content.ContentValues;
import android.net.Uri;

import com.example.android5777_4390_7178_01.model.entities.Activity_type;
import com.example.android5777_4390_7178_01.model.entities.Attractions;
import com.example.android5777_4390_7178_01.model.entities.Business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by יונתן on 08/01/2017.
 */

public class TravelContent {

    public static final String AUTHORITY = "com.example.android5777_4390_7178_01.model.datasource.CustomContentProvider";
    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static class Manager {

        static final String user_name = "user_name";
        static final String user_number = "user_number";
        static final String user_password = "user_password";
        /**
         * The content:// style URI for this table
         */
        public static final Uri MANAGER_URI = Uri.withAppendedPath(AUTHORITY_URI, "manager");
    }

    public static class Business {

        public static final String business_name = "nameBusines";
        public static final String business_id = "_id";
        public static final String business_street = "Ad_street";
        public static final String business_country = "Ad_country";
        public static final String business_city = "Ad_city";
        public static final String business_phone = "phone";
        public static final String business_email = "Email";
        public static final String business_webSite = "webSite";
        /**
         * The content:// style URI for this table
         */
        public static final Uri BUSINESS_URI = Uri.withAppendedPath(AUTHORITY_URI, "business");
    }

    public static class Attraction {
        public static final String ID_activity = "_id";
        public static final String activity_type = "types";
        public static final String activity_country = "country";
        public static final String activity_TStart = "activityStart";
        public static final String activity_TEnd = "activityEnd";
        public static final String activity_price = "price";
        public static final String activity_description = "description";
        public static final String activity_id = "IDbussiness";
        /**
         * The content:// style URI for this table
         */
        public static final Uri ATTRACTION_URI = Uri.withAppendedPath(AUTHORITY_URI, "attraction");
    }

    public static com.example.android5777_4390_7178_01.model.entities.Business ContentValuesToBusiness(ContentValues contant_business) {

        com.example.android5777_4390_7178_01.model.entities.Business business = new com.example.android5777_4390_7178_01.model.entities.Business();
        business.setIDbusines(contant_business.getAsLong(TravelContent.Business.business_id));
        business.setNameBusines(contant_business.getAsString(TravelContent.Business.business_name));
        business.setAd_street(contant_business.getAsString(TravelContent.Business.business_street));
        business.setAd_country(contant_business.getAsString(TravelContent.Business.business_country));
        business.setAd_city(contant_business.getAsString(TravelContent.Business.business_city));
        business.setPhone(contant_business.getAsInteger(TravelContent.Business.business_phone));
        business.setEmail(contant_business.getAsString(TravelContent.Business.business_email));
        business.setwebSite(contant_business.getAsString(TravelContent.Business.business_webSite));
        return business;
    }

    public static Attractions ContentValuesToAttraction(ContentValues contant_attraction) {

        Attractions attractions = new Attractions();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
        String dateStart = contant_attraction.getAsString(TravelContent.Attraction.activity_TStart);
        String dateEnd = contant_attraction.getAsString(TravelContent.Attraction.activity_TEnd);

        // Activity_type activity_type = Activity_type.valueOf(contant_attraction.getAsString(TravelContent.Attraction.activity_type));
        //  attractions.setIdActivity(contant_attraction.getAsInteger("_id"));
        attractions.setTypes(Activity_type.valueOf(contant_attraction.getAsString(TravelContent.Attraction.activity_type)));
        attractions.setCountry(contant_attraction.getAsString(TravelContent.Attraction.activity_country));
        try {
            attractions.setActivityStart(dateFormat.parse(dateStart));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            attractions.setActivityEnd(dateFormat.parse(dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        attractions.setPrice(contant_attraction.getAsInteger(TravelContent.Attraction.activity_price));
        attractions.setDescription(contant_attraction.getAsString(TravelContent.Attraction.activity_description));
        //   attractions.setIDbusines(contant_attraction.getAsLong(TravelContent.Attraction.activity_id));
        attractions.setIdActivity(contant_attraction.getAsInteger(TravelContent.Attraction.ID_activity));
        return attractions;
    }
}


