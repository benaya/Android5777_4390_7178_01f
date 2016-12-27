package com.example.android5777_4390_7178_01.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.MainActivity;
import com.example.android5777_4390_7178_01.R;
import com.example.android5777_4390_7178_01.login;
import com.example.android5777_4390_7178_01.model.SharedPreferences.SharedPreferencesClass;
import com.example.android5777_4390_7178_01.model.datasource.CustomContentProvider;
import com.example.android5777_4390_7178_01.model.entities.Manager;

public class Registery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

       final Manager user = new Manager();
        Button sign = (Button) findViewById(R.id.buttonSign);

        final SharedPreferences sharedPreferences = getSharedPreferences("TEST",0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final EditText username = (EditText) findViewById(R.id.etUserName);
        final EditText password = (EditText) findViewById(R.id.etPassword);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("NAME", username.getText().toString());
                editor.putString("PASSWORD", password.getText().toString());
                editor.commit();
              //  TextView test = (TextView) findViewById(R.id.textTest);
            //    test.setText(sharedPreferences.getString("NAME",""));
                user.userNumber++;
                final long numberUser = user.userNumber;
try {

    final ContentValues contentValueManager = new ContentValues();
    contentValueManager.put("userName", username.toString());
    contentValueManager.put("password", password.toString());
    contentValueManager.put("userNumber", numberUser);
Log.d("TAG","oved");

    new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            getContentResolver().insert(CustomContentProvider.MANAGER_CONTENT_URL, contentValueManager);
            return null;
        }
    };
}
catch (Exception e)
{
    Toast.makeText(Registery.this ,  "הטעות כאן!!" , Toast.LENGTH_LONG).show();
}
                Toast.makeText(Registery.this ,  "number user = "+numberUser+"" , Toast.LENGTH_LONG).show();
                Intent sign = new Intent(Registery.this , login.class);
             startActivity(sign);
            }}
        );}


    }

