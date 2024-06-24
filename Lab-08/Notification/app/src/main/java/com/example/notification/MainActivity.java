package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID_DEFAULT = 1;
    private static final int NOTIFICATION_ID_CUSTOM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDefault = findViewById(R.id.btnDefault);
        Button btnCustom = findViewById(R.id.btnCustom);
        btnDefault.setOnClickListener(v -> {
            sendDefaultNotification();
        });
        btnCustom.setOnClickListener(v -> {
            sendCustomNotification();
        });
    }

    private void sendDefaultNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        try {
            int notificationId = generateNotificationId();
            Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_DEFAULT_ID)
                    .setContentTitle("Title of notification")
                    .setContentText("Message content")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(bitmap)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.notify(notificationId, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCustomNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        try {
            int notificationId = generateNotificationId();
            Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                    .setContentTitle("Custom Notification")
                    .setContentText("PhuotHVSE160205 had new notification!")
                    .setSmallIcon(R.drawable.baseline_notifications_24)
                    .setColor(getResources().getColor(android.R.color.holo_orange_light))
                    .setColorized(true)
//                    .setLargeIcon(bitmap)
//                    .setColor(getResources().getColor(R.color.black))
                    .build();
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.notify(notificationId, notification);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private int generateNotificationId() {
        return (int) System.currentTimeMillis();
    }
}