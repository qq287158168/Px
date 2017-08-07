package com.sby1027.px.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sby1027.px.R;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection myBindService;
    private boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView rlHidDel = (TextView) findViewById(R.id.tv);

        rlHidDel.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);


        int bottomSelf = rlHidDel.getMeasuredHeight();

        myBindService = new MyBindService();

        Intent intent = new Intent(this, MyService.class);
        isBind = bindService(intent, myBindService, BIND_AUTO_CREATE);


//        AsyncTask asyncTask = new AsyncTask() {
//
//            @Override
//            protected void onPostExecute(Object o) {
//                super.onPostExecute(o);
//            }
//
//            @Override
//            protected void onProgressUpdate(Object[] values) {
//                super.onProgressUpdate(values);
//            }
//
//
//            @Override
//            protected Object doInBackground(Object[] params) {
//                publishProgress(params);
//                return null;
//            }
//        };

    }

    private IBinder serviceaa;


    class MyBindService implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {


            MainActivity.this.serviceaa = service;
            Intent intent = new Intent(MainActivity.this, MyService2.class);
            MainActivity.this.startService(intent);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    }

    @Override
    protected void onDestroy() {
        MyService.myService.isRunning = false;
        if (isBind) {
            unbindService(myBindService);
            myBindService = null;
        }
        Intent intent = new Intent(MainActivity.this, MyService2.class);
        MainActivity.this.stopService(intent);

        super.onDestroy();
    }
}
