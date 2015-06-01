package com.example.jack.myapplication19notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.GregorianCalendar;

import static android.app.PendingIntent.getBroadcast;


public class MainActivity extends ActionBarActivity {

    private Button showNotification , stopNotification,setAlarm;


    //notific the users sth happens at the background no matter what app is open up
    NotificationManager notificationManager;

    boolean isNotificActive =false;

    private static int notificID = 33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotification = (Button)findViewById(R.id.showNotification);
        stopNotification = (Button)findViewById(R.id.cancelNotication);
        setAlarm         = (Button)findViewById(R.id.alertNotification);

    }



    public void showNotification(View view) {

        NotificationCompat.Builder notificBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert New MSG");

        Intent moreInfoIntent = new Intent(this,MoreNotification.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);

        taskStackBuilder.addParentStack(MoreNotification.class);

        taskStackBuilder.addNextIntent(moreInfoIntent);

        //PendingIntent.FLAG_UPDATE_CURRENT if the intent is exist we update it but not create a new one

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        notificBuilder.setContentIntent(pendingIntent);


        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificID,notificBuilder.build());

        isNotificActive =true;



    }

    public void stopNotification(View view) {

        if (isNotificActive){

            notificationManager.cancel(notificID);
        }
    }

    public void setAlarm(View view) {

        Long waitTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alarmIntent = new Intent(this,SetAlarmReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        /**
         *  * @param context The Context in which this PendingIntent should perform
         * the broadcast.
         * @param requestCode Private request code for the sender
         * @param intent The Intent to be broadcast.
         * @param flags May be {@link #FLAG_ONE_SHOT}, {@link #FLAG_NO_CREATE},
         * {@link #FLAG_CANCEL_CURRENT}, {@link #FLAG_UPDATE_CURRENT},
         * or any of the flags as supported by
         * {@link Intent#fillIn Intent.fillIn()} to control which unspecified parts
         * of the intent that can be supplied when the actual send happens.
         *
         * @return Returns an existing or new PendingIntent matching the given
         * parameters.  May return null only if {@link #FLAG_NO_CREATE} has been
         * supplied.
         */
        alarmManager.set(AlarmManager.RTC_WAKEUP, waitTime,
                PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
