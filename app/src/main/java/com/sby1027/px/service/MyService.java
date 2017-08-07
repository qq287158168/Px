package com.sby1027.px.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public boolean isRunning = true;
    private MyBinder aa;

    public MyService() {
    }

    public static MyService myService;

    @Override
    public void onCreate() {
        super.onCreate();
        myService = this;
    }

    @Override
    public IBinder onBind(Intent intent) {
        aa = new MyBinder();
        return aa;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        Log.d("sss", "绑定的onDestroy");

        super.onDestroy();
    }

    private int i = 0;

    public class MyBinder extends Binder {

        public MyBinder() {
            a();
        }





        private void a() {


            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {


                        while (isRunning) {

                            Log.d("绑定的", i + "");
                            i++;
                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }


    }

}
