package com.example.jack.myapplication19notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by jack on 5/31/15.
 */
//fire anything this time a reciever
public class SetAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        createNotification(context,"Times up","5 Seconds has passed","Alert");

    }

    private void createNotification(Context context, String Message, String messageText, String MessageAlert) {


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,MainActivity.class),0);


        NotificationCompat.Builder nBuiler = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(Message)
                .setContentText(messageText)
                .setTicker(MessageAlert);

        nBuiler.setContentIntent(pendingIntent);

        nBuiler.setDefaults(Notification.DEFAULT_SOUND);

        nBuiler.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1,nBuiler.build());

    }
}
