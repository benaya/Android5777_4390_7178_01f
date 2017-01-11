package com.example.android5777_4390_7178_01.model.entities;

/**
 * Created by יונתן on 22/12/2016.
 */

public class Attractions {

    private int idActivity;
    private Activity_type types;
    private String country;
    private String activityStart;
    private String activityEnd;
    private int price;
    private String description;
    private long IDbusines;

    public  Attractions(int idActivity, Activity_type types, String country, String activityStart, String activityEnd,
                        int price, String description, Long IDbusiness) {
        this.idActivity = idActivity;
        this.types = types;
        this.country = country;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.price = price;
        this.description = description;
        this.IDbusines = IDbusines;
    }

    public  Attractions(){};

    public Activity_type getTypes() {
        return types;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public void setTypes(Activity_type types) {
        this.types = types;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(String activityStart) {
        this.activityStart = activityStart;
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(String activityEnd) {
        this.activityEnd = activityEnd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIDbusines() {
        return IDbusines;
    }

    public void setIDbusines(long IDbusines) {
        this.IDbusines = IDbusines;
    }
}
