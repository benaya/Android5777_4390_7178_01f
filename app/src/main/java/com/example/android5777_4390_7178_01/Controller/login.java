package com.example.android5777_4390_7178_01.Controller;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.R;
import com.example.android5777_4390_7178_01.model.datasource.TravelContent;

public class login extends AppCompatActivity {
    private static String rememberPassword;
    private static String rememberMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sharedPreferences = getSharedPreferences("TEST", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if (getIntent().getBooleanExtra("Log out", false))
            finish();

        final EditText manegerUser = (EditText) findViewById(R.id.etUserN);
        final EditText passwordUser = (EditText) findViewById(R.id.editTextPassword);
        final CheckBox CBremember = (CheckBox) findViewById(R.id.cbRemember);

        if (sharedPreferences.getBoolean("REMEMBER ME", false)) {
            manegerUser.setText(sharedPreferences.getString("NAME", ""));
            passwordUser.setText(sharedPreferences.getString("PASSWORD", ""));
            CBremember.setChecked(true);
        }

        findViewById(R.id.buttonSign1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Registery.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonForgotP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rememberMail = sharedPreferences.getString("NAME", "");
                rememberPassword = sharedPreferences.getString("PASSWORD", "");

                if (manegerUser.getText().toString().equals(rememberMail)) {
                    final ContentValues forgotMail = new ContentValues();
                    forgotMail.put("code", rememberPassword.toString());
                    forgotMail.put("mail", rememberMail.toString());

                    final Uri Urimail = Uri.parse("content://com.example.android5777_4390_7178_01.model.datasource.CustomContentProvider/mail");
                    Log.d("TAG", "content mail good");

                    new AsyncTask<Void, Void, Uri>() {
                        @Override
                        protected Uri doInBackground(Void... params) {
                            getContentResolver().insert(Urimail, forgotMail);
                            Log.d("TAG", "content mail good");
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Uri uriResult) {
                            super.onPostExecute(uriResult);
                            Toast.makeText(getBaseContext(), "Password sent to you by e-mail", Toast.LENGTH_LONG).show();
                        }
                    }.execute();
                }
                else {
                    AlertDialog.Builder problem = new AlertDialog.Builder(login.this);
                    problem.setTitle("Error");
                    problem.setMessage("This Email Address Not Found!");
                    problem.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    problem.show();
                }
            }
        });

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  String name = sharedPreferences.getString("NAME", "no ");
                                                                  String password = sharedPreferences.getString("PASSWORD", "");

                                                                  if (manegerUser.getText().toString().equals(name) &&
                                                                          passwordUser.getText().toString().equals(password)) {
                                                                      if (CBremember.isChecked()) {
                                                                          editor.putBoolean("REMEMBER ME", true);
                                                                          editor.commit();
                                                                          Log.d("TAG", "remember");
                                                                      }
                                                                      else {
                                                                          editor.putBoolean("REMEMBER ME", false);
                                                                          editor.commit();
                                                                          Log.d("TAG", "not remember");
                                                                      }
                                                                      Intent intent = new Intent(login.this, MainActivity.class);
                                                                      intent.putExtra("USERNAME", manegerUser.getText().toString());
                                                                      startActivity(intent);
                                                                  } else {
                                                                      AlertDialog.Builder problem = new AlertDialog.Builder(login.this);
                                                                      problem.setTitle("Error");
                                                                      problem.setMessage("Wrong username or password!");
                                                                      problem.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                          @Override
                                                                          public void onClick(DialogInterface dialog, int which) {
                                                                              dialog.cancel();
                                                                          }
                                                                      });
                                                                      problem.show();
                                                                  }
             /*   final ContentValues maneger = new ContentValues();
                maneger.put("nameKey", manegerUser.toString());

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        getContentResolver().insert(CustomContentProvider.MANAGER_CONTENT_URL, maneger);
                        return null;
                    }
                };*/
                                                              }
                                                          }
        );
    } }






