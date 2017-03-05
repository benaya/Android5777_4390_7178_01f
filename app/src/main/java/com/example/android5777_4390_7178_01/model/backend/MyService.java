package com.example.android5777_4390_7178_01.model.backend;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.android5777_4390_7178_01.model.datasource.ListDsManager;
import com.example.android5777_4390_7178_01.model.datasource.TravelContent;

public class MyService extends Service {
/*
    IDSManager db;
    private final int timeToSleep = 3500;
    Thread background;
    boolean running;


    public MyService() {
        db = ManagerFactory.getManager();
        running = true;

        background = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (running) {
                        searchForChange();
                        Thread.sleep(timeToSleep);
                    }
                } catch (Exception e) {
                    Log.d("TAG", "Error while service search for changes:  " + e);
                    stopSearch();
                }
            }
        });
    }

    private void searchForChange() throws Exception {
        if (db.isBusinessChanged())
        {
            Log.d("TAG", "APP1");
            businessBroadcastIntent();
        }
        if(db.isActivityChanged())
        {
            activityBroadcastIntent();
        }
        Log.d("TAG", "service running");
        Thread.sleep(timeToSleep);
    }


    public void businessBroadcastIntent() {
        Intent intent = new Intent();
        //intent.putExtra("message", "Activity/BusinnessUpdade");
        intent.setAction("HAGER.VAKNIN.ACTION_SERVICE.BUS");
        Log.d("TAG", "APP2");
        sendBroadcast(intent);
    }

    public void activityBroadcastIntent() {
        Intent intent = new Intent();
        //intent.putExtra("message", "Activity/BusinnessUpdade");
        intent.setAction("HAGER.VAKNIN.ACTION_SERVICE.ACT");
        sendBroadcast(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        background.start();
        return START_STICKY;
    }


    public void onDestroy() {
        super.onDestroy();
        stopSearch();
    }

    private void stopSearch() {
        running = false;
    }
}
*/
   final String TAG = "myservice";

    static boolean ServiceRun = false;

    static final String ACTION_SERVICE = "com.example.android5777_4390_7178_01.ACTION_SERVICE";
    IDSManager manager = ManagerFactory.getManager();

    public MyService() {
    }

    @Override
    public void onCreate() {


        ServiceRun = true;

        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");
        Log.i(TAG, "Service onStartCommand1");
        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {


                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                while (true) {
                    try {
                        ServiceRun = true;
                        try {

                            //CHECKING IF ACTIVITUES AND BUSINESS UPDATED:
                            Thread.sleep(1000);

                            if (manager.isActivityChanged()) {
                                Log.d("TAG", "-----------Activity updated----------");
                                //SEND message to second app
                                Intent intent = new Intent("MyCustomIntent");
                                // add data to the Intent
                                intent.putExtra("message", "NEW ACTIVITY ADDED TO DATA BASE");
                                intent.setAction("HAGER.VAKNIN.ACTION_SERVICE.ACT");
                                sendBroadcast(intent);
                            } else {
                                Log.d("TAG", "Activity not updated");
                            }
                            Thread.sleep(1000);
                            if (manager.isBusinessChanged()) {
                                Log.d("TAG", "-----------Business updated----------");
                                //SEND message to second app
                                Intent intent = new Intent("MyCustomIntent");
                                // add data to the Intent
                                intent.putExtra("message", "NEW Business ADDED TO DATA BASE");
                                intent.setAction("HAGER.VAKNIN.ACTION_SERVICE.BUS");
                                sendBroadcast(intent);
                            } else {
                                Log.d("TAG", "Business not updated");
                            }

                        } catch (Exception e) {
                            Log.d("TAG", "checking if updated NOT WORKING " + e.getMessage());
                        }
                        //SLEEP FOR 30 SEC BEFORE YOU CHECK AGAIN
                        ServiceRun = false;
                        Thread.sleep(60000);

                    } catch (Exception e) {
                        Log.d("TAG", "SERVICE NOT WORKING " + e.getMessage());
                    }

                    if (ServiceRun) {
                        Log.i(TAG, "Service running");
                    }
                }

                //Stop service once it finishes its task
                //  stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
