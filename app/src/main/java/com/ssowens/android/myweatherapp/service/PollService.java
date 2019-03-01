package com.ssowens.android.myweatherapp.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.SystemClock;

import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.ui.MainActivity;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

/**
 * Created by Sheila Owens on 2/28/19.
 */
public class PollService extends IntentService {

    private static final String TAG = "Pollservice";

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
        Intent i = MainActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0,i,0);

        Notification notification = new Notification.Builder(this)
                .setTicker(resources.getString(R.string.current_weather_notification))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(resources.getString(R.string.new_weather_title))
                .setContentText(resources.getString(R.string.new_weather_text))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(0,notification);

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
