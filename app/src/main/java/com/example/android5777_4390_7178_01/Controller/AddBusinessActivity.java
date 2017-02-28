package com.example.android5777_4390_7178_01.Controller;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.R;
import com.example.android5777_4390_7178_01.model.datasource.TravelContent;

public class AddBusinessActivity extends Activity implements View.OnClickListener {

    private EditText etStreet;
    private EditText etCity;
    private EditText etCountry;
    private EditText etEmail;
    private EditText etPhone;
    //  private TextView  etIdBusiness;
    private EditText etIdBusiness;
    private EditText etBusinessName;
    private EditText etWebSite;
    private Button addBusinessButton;

    private String id;
    private String name;
    private String street;
    private String country;
    private String city;
    private String phone;
    private String email;
    private String WebSite;
    private boolean check;
    private Long _idb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        findViews();
    }

    @Override
    public void onClick(View v) {
        if (v == addBusinessButton) {
            addBusiness();

        }
    }

    private void findViews() {
        etStreet = (EditText) findViewById(R.id.etStreet);
        etCity = (EditText) findViewById(R.id.etCity);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etIdBusiness = (EditText) findViewById(R.id.etIdBusiness);
        etBusinessName = (EditText) findViewById(R.id.etUserName);
        etWebSite = (EditText) findViewById(R.id.etWebSite);
        addBusinessButton = (Button) findViewById(R.id.buttonAddBusines);
        addBusinessButton.setOnClickListener(this);
        final Uri uri = TravelContent.Business.BUSINESS_URI;
    }

    public void checkKelet() {
        id = etIdBusiness.getText().toString();
        name = etBusinessName.getText().toString();
        street = etStreet.getText().toString();
        country = etCountry.getText().toString();
        city = etCity.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();
        WebSite = etWebSite.getText().toString();
    }

    private boolean checkId(Long _id) {
        boolean flag = true;
        Cursor Matrix = getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
        for (Matrix.moveToFirst(); !Matrix.isAfterLast(); Matrix.moveToNext()) {
            if (_id == Matrix.getInt(Matrix.getColumnIndex(TravelContent.Business.business_id))) {
                flag = false;
            }
        }
        return flag;

    }

    private boolean isNumberValid(String num) {
        return num.matches("^[0-9]*$");
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isWebsiteValid(String website) {
        return website.length() > 3 && website.contains(".");
    }

    private void addBusiness() {

        etIdBusiness.setError(null);
        etStreet.setError(null);
        etCity.setError(null);
        etEmail.setError(null);
        etPhone.setError(null);
        etBusinessName.setError(null);
        etWebSite.setError(null);
        etCountry.setText(null);

        try {
            // Store values at the time of the login attempt.
            Log.d("TAG", "taking data from UI");
            checkKelet();
            boolean cancel = false;
            View focusView = null;

            // Check for a valid phone, if the user entered one.
            if (TextUtils.isEmpty(id)) {
                etIdBusiness.setError("אתה חייב למלא את כל השדות!");
                focusView = etIdBusiness;
                cancel = true;
            } else if (!isNumberValid(id)) {
                etIdBusiness.setError("הכנס מספרים בלבד!!");
                focusView = etIdBusiness;
                cancel = true;
            }

            // Check for a valid name.
            if (TextUtils.isEmpty(name)) {
                etBusinessName.setError("אתה חייב למלא את כל השדות!");
                focusView = etBusinessName;
                cancel = true;
            }

            // Check for a valid phone, if the user entered one.
            if (TextUtils.isEmpty(phone)) {
                etPhone.setError("אתה חייב למלא את כל השדות!");
                focusView = etPhone;
                cancel = true;
            } else if (!isNumberValid(phone)) {
                etPhone.setError("מספר הטלפון שהוכנס אינו תקין");
                focusView = etPhone;
                cancel = true;
            }

            // Check for a valid email address.
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("אתה חייב למלא את כל השדות!");
                focusView = etEmail;
                cancel = true;
            } else if (!isEmailValid(email)) {
                etEmail.setError("כתובת המייל אינה תקינה!");
                focusView = etEmail;
                cancel = true;
            }

            // Check for a valid website
            if (TextUtils.isEmpty(WebSite)) {
                etWebSite.setError("אתה חייב למלא את כל השדות!");
                focusView = etWebSite;
                cancel = true;
            } else if (!isWebsiteValid(WebSite)) {
                etWebSite.setError("כתובת האתר אינה תקינה!!");
                focusView = etWebSite;
                cancel = true;
            }

            if (cancel) {
                focusView.requestFocus();
            } else {
                final Uri uri = TravelContent.Business.BUSINESS_URI;
                final ContentValues contentValuesAddBusiness = new ContentValues();

                _idb = Long.parseLong(etIdBusiness.getText().toString());

                contentValuesAddBusiness.put(TravelContent.Business.business_id, etIdBusiness.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_name, etBusinessName.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_street, etStreet.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_country, etCountry.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_city, etCity.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_phone, etPhone.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_email, etEmail.getText().toString());
                contentValuesAddBusiness.put(TravelContent.Business.business_webSite, etWebSite.getText().toString());

                new AsyncTask<Void, Void, Uri>() {
                    @Override
                    protected Uri doInBackground(Void... params) {
                        //    Log.d("TAG","AsyTask bussines good");
                        try {
                            check = checkId(_idb);
                            if (check) {
                                getContentResolver().insert(uri, contentValuesAddBusiness);
                                //    Log.d("TAG","content bussines good");
                            } else {
                                check = false;
                            }
                        } catch (Exception e) {
                            Log.d("TAG", "fail to add new business due to:  " + e);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Uri uriResult) {
                        super.onPostExecute(uriResult);
                        //  Log.d("TAG","AsyncTask bussines NOT good");
                        if (check) {
                            Toast.makeText(getBaseContext(), "insert id: " + etIdBusiness.getText().toString(), Toast.LENGTH_LONG).show();
                            //  Intent intent = new Intent(AddBusinessActivity.this, AddAttractionActivity.class);
                            //  intent.putExtra("ID BUSSINES", etIdBusiness.getText().toString());
                            //   startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getBaseContext(), "Error...ID already exist !", Toast.LENGTH_LONG).show();
                        }
                        //  updateItemList(uri);
                    }
                }.execute();
            }
        } catch (Exception e) {
            Log.d("TAG", "fail to add new business due to:  " + e);
        }
    }
}



