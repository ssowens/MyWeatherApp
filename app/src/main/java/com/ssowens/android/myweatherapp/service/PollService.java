package com.ssowens.android.myweatherapp.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.SystemClock;

import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.ui.MainActivity;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Created by Sheila Owens on 2/28/19.
 */
public class PollService extends IntentService {

    private static final String TAG = "Pollservice";
    public static final String NOTIFICATION_CHANNEL_ID = "4655";
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public PollService() {
        super(TAG);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    // Set interval to 60 minutes
    private static final long POLL_INTERVAL_MS = TimeUnit.MINUTES.toMillis(60);

    public PollService(String name) {
        super(name);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PollService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Resources resources = getResources();
        Intent resultIntent = MainActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, resultIntent, 0);

        builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setTicker(resources.getString(R.string.current_weather_notification))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(resources.getString(R.string.new_weather_title))
                .setContentText(resources.getString(R.string.new_weather_text))
                .setContentIntent(pi)
                .setAutoCancel(true);
        //   .build();

        NotificationManager notificationMgr =
                (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationMgr.createNotificationChannel(notificationChannel);
        }


        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        if (notificationMgr == null) throw new AssertionError("Object cannot be null");
        notificationManager.notify(0, builder.build());

    }

    public static void setServiceAlarm(Context context, boolean isOn) {
        Intent intent = PollService.newIntent(context);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);
        if (isOn) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), POLL_INTERVAL_MS, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context) {
        Intent intent = PollService.newIntent(context);
        PendingIntent pendingIntent = PendingIntent
                .getService(context, 0, intent,
                        PendingIntent.FLAG_NO_CREATE);
        return pendingIntent != null;
    }

    private boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            isNetworkAvailable = cm.getActiveNetwork() != null;
        }
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }
}