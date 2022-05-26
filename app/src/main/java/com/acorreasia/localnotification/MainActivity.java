package com.acorreasia.localnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button5, button10, button15;
    public static String NOTIFICATION_CHANNEL_ID = "1001";
    public static String default_notification_id = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button5 = findViewById(R.id.button5);
        button10 = findViewById(R.id.button10);
        button15 = findViewById(R.id.button15);

        button5.setOnClickListener(v->scheduleNotification(getNotification("5 seconds delay"),5000));
        button10.setOnClickListener(v->scheduleNotification(getNotification("10 seconds delay"),10000));
        button15.setOnClickListener(v->scheduleNotification(getNotification("15 seconds delay"),15000));

    }

    //schedule notification
    private void scheduleNotification(Notification notification, int delay){
        Intent notificationIntent = new Intent(this,MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATIONID,1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION,notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        long futureMillis = SystemClock.elapsedRealtime()+delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager !=null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureMillis, pendingIntent);
    }

    private Notification getNotification(String content){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_id);

        builder.setContentTitle("Schedule Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);

        return builder.build();
    }
}