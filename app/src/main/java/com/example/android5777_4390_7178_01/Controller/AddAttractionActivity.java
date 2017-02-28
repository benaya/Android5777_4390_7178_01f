package com.example.android5777_4390_7178_01.Controller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.R;
import com.example.android5777_4390_7178_01.model.datasource.TravelContent;
import com.example.android5777_4390_7178_01.model.entities.Activity_type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.android5777_4390_7178_01.R.id.spinner;

public class AddAttractionActivity extends Activity implements View.OnClickListener {

    private static int numberAtt = 99;
    private Button addAttractionButton;

    private EditText description;
    private EditText country;
    private EditText price;
    private Spinner spinner;
    private EditText idBussines;
    private String type;
    private HelpDate sDate;
    private HelpDate eDate;

    private String descriptionS;
    private String priceS;
    private String countryS;
    private String idBussiness;
    //private HelpDate sDates;
    //private HelpDate eDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attraction);
        findViews();
    }

    @Override
    public void onClick(View v) {
        if (v == addAttractionButton) {
            addAttraction();

        }
    }

    private void findViews() {

        spinner = (Spinner) findViewById(R.id.spinnerAttraction);
        spinner.setAdapter(new ArrayAdapter<Activity_type>(this, android.R.layout.simple_spinner_item, Activity_type.values()));

        sDate = (HelpDate) findViewById(R.id.Sdate);
        eDate = (HelpDate) findViewById(R.id.Edate);
        description = (EditText) findViewById(R.id.etDescription);
        country = (EditText) findViewById(R.id.etCountry);
        price = (EditText) findViewById(R.id.etPrice);

        addAttractionButton = (Button) findViewById(R.id.buttonAddAtraction);
        addAttractionButton.setOnClickListener(this);

        idBussines = (EditText) findViewById(R.id.etIdAtt);

        final Uri uri = TravelContent.Attraction.ATTRACTION_URI;
    }

    public void checkKelet() {
        priceS = price.getText().toString();
        descriptionS = description.getText().toString();
        countryS = country.getText().toString();
        idBussiness = idBussines.getText().toString();
    }

    private void addAttraction() {

        price.setError(null);
        description.setError(null);
        country.setError(null);
        try {

            Log.d("TAG", "taking data from UI");
            checkKelet();
            boolean cancel = false;
            View focusView = null;

            if (TextUtils.isEmpty(descriptionS)) {
                description.setError("אתה חייב למלא את כל השדות!");
                focusView = description;
                cancel = true;
            }

            if (TextUtils.isEmpty(countryS)) {
                country.setError("אתה חייב למלא את כל השדות!");
                focusView = country;
                cancel = true;
            }

            // Check for a valid phone, if the user entered one.
            if (sDate.getDate().after(eDate.getDate())) {
                eDate.setError("תאריך הסיום שהוכנס אינו תקין!");
                focusView = price;
                cancel = true;
            }

            if (cancel) {
                focusView.requestFocus();
            } else {
                final Uri uri = TravelContent.Attraction.ATTRACTION_URI;
                type = ((Activity_type) spinner.getSelectedItem()).name();
                final ContentValues contentValuesAttarction = new ContentValues();

                Date SDate = sDate.getDate();
                Date EDate = eDate.getDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // like MySQL Format
                String SdateString = dateFormat.format(SDate);
                String EdateString = dateFormat.format(EDate);

                // contentValuesAttarction.put("_id", "");
                contentValuesAttarction.put(TravelContent.Attraction.activity_type, type.toString());
                contentValuesAttarction.put(TravelContent.Attraction.activity_country, country.getText().toString());
                contentValuesAttarction.put(TravelContent.Attraction.activity_TStart, SdateString);
                contentValuesAttarction.put(TravelContent.Attraction.activity_TEnd, EdateString);
                contentValuesAttarction.put(TravelContent.Attraction.activity_price, price.getText().toString());
                contentValuesAttarction.put(TravelContent.Attraction.activity_description, description.getText().toString());
                if (!((TextUtils.isEmpty(idBussiness)))) {
                    //        contentValuesAttarction.put(TravelContent.Attraction.activity_id, idBussines.getText().toString());
                    contentValuesAttarction.put(TravelContent.Attraction.ID_activity, idBussines.getText().toString());
                }
                else {
                    numberAtt++;
                    //       contentValuesAttarction.put(TravelContent.Attraction.activity_id, numberAtt);
                    numberAtt++;
                    contentValuesAttarction.put(TravelContent.Attraction.ID_activity, numberAtt);
                }
                new AsyncTask<Void, Void, Uri>() {
                    @Override
                    protected Uri doInBackground(Void... params) {
                        getContentResolver().insert(uri, contentValuesAttarction);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Uri uriResult) {
                        super.onPostExecute(uriResult);
                        Toast.makeText(getBaseContext(), "insert id: " + idBussines.getText().toString(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }.execute();
            }

        } catch (Exception e) {
            Log.d("TAG", "fail to add new business due to:  " + e);
        }
    }


}

