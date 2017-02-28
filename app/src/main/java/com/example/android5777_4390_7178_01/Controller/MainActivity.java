package com.example.android5777_4390_7178_01.Controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.R;
import com.example.android5777_4390_7178_01.model.backend.IDSManager;
import com.example.android5777_4390_7178_01.model.backend.ManagerFactory;
import com.example.android5777_4390_7178_01.model.backend.MyService;
import com.example.android5777_4390_7178_01.model.datasource.TravelContent;

public class MainActivity extends AppCompatActivity {
    IDSManager manager = ManagerFactory.getManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView s = (TextView) findViewById(R.id.textViewUser);
        s.setText(getIntent().getStringExtra("USERNAME"));

        Log.d("TAG"," service start!!");
        //  findViewById(R.id.bubuttonStart).setOnClickListener(new View.OnClickListener() {
        //     @Override
        //   public void onClick(View v) {
        ComponentName componentName = new ComponentName
                (
                        "com.example.android5777_4390_7178_01",
                        "com.example.android5777_4390_7178_01.model.backend.MyService"
                );

        Intent intent_2 = new Intent();
        intent_2.setComponent(componentName);
        startService(intent_2);
        /*
try {
    if (manager.isBusinessChanged())
        Toast.makeText(MainActivity.this, "ssss", Toast.LENGTH_LONG).show();
}
catch (Exception E)
{}
*/


        findViewById(R.id.buttonADDB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddBusiness = new Intent(MainActivity.this, AddBusinessActivity.class);
                startActivity(intentAddBusiness);

            }
        });

        findViewById(R.id.buttonADDA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddAtraction = new Intent(MainActivity.this, AddAttractionActivity.class);
                startActivity(intentAddAtraction);

            }
        });

        findViewById(R.id.buttonGetAtt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Spinner lecturerIdSpinner = (Spinner) findViewById(R.id.spinnerGetAtt);
                lecturerIdSpinner.setEnabled(false);

                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {

                        return getContentResolver().query(TravelContent.Attraction.ATTRACTION_URI, null, null, null, null);
                    }

                    @Override
                    protected void onPostExecute(Cursor cursor) {
                        super.onPostExecute(cursor);
                        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor) {
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                TextView tv = new TextView(context);
                                tv.setTextSize(20);
                                tv.setTextColor(Color.BLACK);
                                return tv;
                            }

                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                TextView tv = (TextView) view;
                                tv.setText("(" + cursor.getString(1) + ")  " + cursor.getString(6));
                            }
                        };
                        lecturerIdSpinner.setAdapter(adapter);
                        lecturerIdSpinner.setEnabled(true);
                    }
                }.execute();
            }
        });

        findViewById(R.id.b11111).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Spinner lecturerIdSpinner = (Spinner) findViewById(R.id.spinner);
                lecturerIdSpinner.setEnabled(false);

                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        return getContentResolver().query(TravelContent.Business.BUSINESS_URI, null, null, null, null);
                    }

                    @Override
                    protected void onPostExecute(Cursor cursor) {
                        super.onPostExecute(cursor);
                        CursorAdapter adapter = new CursorAdapter(MainActivity.this, cursor) {
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                TextView tv = new TextView(context);
                                tv.setTextSize(20);
                                tv.setTextColor(Color.BLACK);
                                return tv;
                            }

                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                TextView tv = (TextView) view;
                                tv.setText("[" + cursor.getString(0) + "]  " + cursor.getString(1));
                            }
                        };

                        lecturerIdSpinner.setAdapter(adapter);
                        lecturerIdSpinner.setEnabled(true);
                    }
                }.execute();
            }
        });
    }}




