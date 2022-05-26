# Schedule Local Notification Android
How to Schedule Local Notification With AlarmManager In Android Studio Java Tutorial  

This following video demonstrate about How to schedule local notification in android.

[![Watch Full Tutorial](https://img.youtube.com/vi/Ijv0vcxNk78/0.jpg)](https://www.youtube.com/watch?v=Ijv0vcxNk78)

# Step 1
Create a new project in Android Studio, go to File ⇒ New Project and fill all required details to create a new project.

# Step 2
Add the following code to res/layout/activity_main.xml.

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="5 Seconds!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button10"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="10 Seconds!"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button5" />

    <Button
        android:id="@+id/button15"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="15 Seconds!"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button10" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
# Step 3
 Add the following code to src/MainActivity.
 
  ```
   public class MainActivity extends AppCompatActivity {
    public static String NOTIFICATION_CHANNEL_ID = "1001";
    public static String default_notification_id = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button5 = findViewById(R.id.button5);
        Button button10 = findViewById(R.id.button10);
        Button button15 = findViewById(R.id.button15);

        button5.setOnClickListener(v->scheduleNotification(getNotification("5 seconds delay"),5000));
        button10.setOnClickListener(v->scheduleNotification(getNotification("10 seconds delay"),10000));
        button15.setOnClickListener(v->scheduleNotification(getNotification("15 seconds delay"),15000));

    }
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
   ```
  # Step 4
    Add the following code to src/MyNotificationPublisher.
    
    ```
    public class MyNotificationPublisher extends BroadcastReceiver {
   public static String NOTIFICATION_ID = "notification-id" ;
   public static String NOTIFICATION = "notification" ;
   public void onReceive (Context context , Intent intent) {
      NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context. NOTIFICATION_SERVICE ) ;
      Notification notification = intent.getParcelableExtra( NOTIFICATION ) ;
      if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
         int importance = NotificationManager. IMPORTANCE_HIGH ;
         NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
         assert notificationManager != null;
         notificationManager.createNotificationChannel(notificationChannel) ;
      }
      int id = intent.getIntExtra( NOTIFICATION_ID , 0 ) ;
      assert notificationManager != null;
      notificationManager.notify(id , notification) ;
   }
}
```
```
# Step 5
 Add the following code to AndroidManifest.xml

```
<? xml version = "1.0" encoding = "utf-8" ?>
<manifest xmlns: android = "http://schemas.android.com/apk/res/android"
   package= "app.tutorialspoint.com.notifyme" >
   <uses-permission android :name = "android.permission.VIBRATE" />
   <application
      android :allowBackup = "true"
      android :icon = "@mipmap/ic_launcher"
      android :label = "@string/app_name"
      android :roundIcon = "@mipmap/ic_launcher_round"
      android :supportsRtl = "true"
      android :theme = "@style/AppTheme" >
      <activity android :name = ".MainActivity" >
         <intent-filter>
            <action android :name = "android.intent.action.MAIN" />
            <category android :name = "android.intent.category.LAUNCHER" />
         </intent-filter>
      </activity>
      <receiver android :name = ".MyNotificationPublisher" />
   </application>
</manifest>
```


Let's try to run your application. I assume you have connected your actual Android Mobile device with your computer. To run the app from android studio, open one of your project's activity files and click Run  icon from the toolbar. Select your mobile device as an option and then check your mobile device which will display your default screen −
    
   
