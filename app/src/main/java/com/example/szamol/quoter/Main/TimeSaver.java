package com.example.szamol.quoter.Main;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.szamol.quoter.GlobalContext;


public class TimeSaver {
    private static SharedPreferences preferences = GlobalContext.getAppContext().getSharedPreferences("boardPreferences", Activity.MODE_PRIVATE);

    static void saveTime() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("lastReceive", System.currentTimeMillis());
        editor.apply();
    }

    static boolean isQuoteReady() {
        boolean result = false;
        long lastReceive = preferences.getLong("lastReceive", 0);
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastReceive > 10000) {
            result = true;
        }
        return result;
    }


    static void setNotification() {
        PendingIntent mAlarmSender = PendingIntent.getBroadcast(GlobalContext.getAppContext(), 0, new Intent(GlobalContext.getAppContext(), QuoteNotification.class), 0);
        AlarmManager am = (AlarmManager) GlobalContext.getAppContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 20000, mAlarmSender);
    }
}
