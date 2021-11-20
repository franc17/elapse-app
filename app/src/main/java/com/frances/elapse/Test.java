package com.frances.elapse;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Test {
    public static void setChime(Context context, int type, long timeSet){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(context.getApplicationContext(), ElapseReceiver.class);
        i.putExtra("SET", "ALARM_TEST");
        i.putExtra("type", type);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_ONE_SHOT);
//        TODO
        LocalDateTime ldtNow = LocalDateTime.now().plusSeconds(30);
        ZonedDateTime zNow = ldtNow.atZone(ZoneId.of(TimeZone.getDefault().getID()));

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, zNow.toInstant().toEpochMilli(), pi);
        Log.i("alarmtest", "test alarm set");

    }
}
