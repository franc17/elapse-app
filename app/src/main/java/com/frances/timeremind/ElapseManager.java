package com.frances.timeremind;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ElapseManager {

    public static void setChime(Context context, int type, long timeSet){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(context.getApplicationContext(), com.frances.timeremind.ElapseReceiver.class);
        i.putExtra("SET", "ALARM_SET");
        i.putExtra("type", type);

        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        if(timeSet!=0) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeSet, pi);
            Log.i("alarmtest", "alarm set");
        }
        else{
            alarmManager.cancel(pi);
        }
    }
}
