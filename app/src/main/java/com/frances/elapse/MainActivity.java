package com.frances.elapse;

import static java.time.LocalTime.now;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.frances.elapse.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;
    int selection;
    LocalDateTime timeNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        selection = preferences.getInt("type", 0);
        Log.i("selection", "selection: "+selection);


        switch(selection){
            case 15:
                binding.fifteen.setPressed(true);
                break;
            case 30:
                binding.thirty.setPressed(true);
                break;
            case 60:
                binding.sixty.setPressed(true);
                break;
            case 0:
                binding.off.setPressed(true);
                break;
        }

        timeNow = LocalDateTime.now();


        binding.fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("type", 15);
                editor.apply();
                setFifteen(getApplicationContext(), timeNow);
                finish();
            }
        });
        binding.thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("type", 30);
                editor.apply();
                setThirty(getApplicationContext(), timeNow);
                finish();
            }
        });
        binding.sixty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("type", 60);
                editor.apply();
                setSixty(getApplicationContext(), timeNow);
                finish();
            }
        });
        binding.off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("type", 0);
                editor.apply();
                setOff(getApplicationContext());
                finish();
            }
        });
    }

    public static void setFifteen(Context context, LocalDateTime now){
        LocalTime l;
        if(now.getMinute() < 15){
            l = LocalTime.of(now.getHour(), 15);
        }
        else if(now.getMinute() < 30){
            l = LocalTime.of(now.getHour(), 30);
        }
        else if(now.getMinute() < 45){
            l = LocalTime.of(now.getHour(), 45);
        }
        else{
            if(now.getHour()==23){
                l = LocalTime.of(0, 0);
            }
            else {
                l = LocalTime.of(now.plusHours(1).getHour(), 0);
            }
        }
        LocalDateTime ldsSet = l.atDate(LocalDate.now());
        ZonedDateTime zoned = ldsSet.atZone(ZoneId.of(TimeZone.getDefault().getID()));
        ElapseManager.setChime(context.getApplicationContext(), 15, zoned.toInstant().toEpochMilli());
    }

    public static void setThirty(Context context, LocalDateTime now){
        LocalTime l;
        if(now.getMinute() < 30){
            l = LocalTime.of(now.getHour(), 30);
        }
        else{
            if(now.getHour()==23){
                l = LocalTime.of(0, 0);
            }
            else {
                l = LocalTime.of(now.plusHours(1).getHour(), 0);
            }
        }
        LocalDateTime ldsSet = l.atDate(LocalDate.now());
        ZonedDateTime zoned = ldsSet.atZone(ZoneId.of(TimeZone.getDefault().getID()));
        ElapseManager.setChime(context.getApplicationContext(), 30, zoned.toInstant().toEpochMilli());
    }

    public static void setSixty(Context context, LocalDateTime now){
        LocalTime l;
        if(now.getHour()==23){
            l = LocalTime.of(0, 0);
        }
        else {
            l = LocalTime.of(now.plusHours(1).getHour(), 0);
        }
        LocalDateTime ldsSet = l.atDate(LocalDate.now());
        ZonedDateTime zoned = ldsSet.atZone(ZoneId.of(TimeZone.getDefault().getID()));
        ElapseManager.setChime(context.getApplicationContext(), 60, zoned.toInstant().toEpochMilli());
    }

    public static void setOff(Context context){
        ElapseManager.setChime(context.getApplicationContext(), 0, 0);
    }
}