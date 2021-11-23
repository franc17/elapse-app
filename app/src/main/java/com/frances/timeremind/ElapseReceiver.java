package com.frances.timeremind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import java.time.LocalDateTime;

public class ElapseReceiver extends BroadcastReceiver {
    int code;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("alarmtest", "chime receiver onReceive");
        code = intent.getIntExtra("type", 0);

        if(intent.getStringExtra("SET").equals("ALARM_SET")){
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VibrationEffect.createWaveform(new long[]{100,100,250}, new int[]{255, 0, 255}, -1));
        }

        if(intent.getStringExtra("SET").equals("ALARM_TEST")){
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VibrationEffect.createWaveform(new long[]{250,100,250}, new int[]{255, 0, 255}, -1));

//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel chan = new NotificationChannel("channel", "chan", NotificationManager.IMPORTANCE_HIGH);
//            chan.setDescription("Alert");
//            chan.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            chan.enableVibration(true);
//            notificationManager.createNotificationChannel(chan);
//            NotificationCompat.Builder not = new NotificationCompat.Builder(context, "channel");
//            not.setDefaults(Notification.DEFAULT_ALL)
//                    .setContentTitle("Sample Notification")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setVibrate(new long[]{0, 1000, 500, 1000});
//            notificationManager.notify(1, not.build());
        }

        if(code == 15){
            com.frances.timeremind.MainActivity.setFifteen(context.getApplicationContext(), LocalDateTime.now());
        }
        else if(code == 30){
            com.frances.timeremind.MainActivity.setThirty(context.getApplicationContext(), LocalDateTime.now());
        }
        else if(code == 60){
            com.frances.timeremind.MainActivity.setSixty(context.getApplicationContext(), LocalDateTime.now());
        }


    }
}
