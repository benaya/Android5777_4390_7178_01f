package com.example.android5777_4390_7178_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edit = (EditText) findViewById(R.id.editTextUser);

                if (edit.getText().toString().equals("בניה")) {
                    Intent intent = new Intent(login.this, screen1.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login.this, "רק בניה יכול!!", Toast.LENGTH_LONG).show();
                }
            }
        }
        );

       /* findViewById(R.id.buttonSign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(login.this , Manager.class);
                startActivity(sign);
            }
        });*/
    }

  /*  public void buttonLogin (View view)
    {
        EditText editText = (EditText) findViewById(R.id.editTextUser);
        String string = editText.getText().toString();

        Toast.makeText(this, string , Toast.LENGTH_SHORT).show();
    }*/

    public void buttonSign (View view)
    {
        Intent sign = new Intent(login.this , screen1.class);
        startActivity(sign);
    }
}
