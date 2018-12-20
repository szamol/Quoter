package com.example.szamol.quoter.Stats;

public class StatsButtonClicks {
    private static int numberOfReceiveButtonClicks = 0;

    public static void load() {
        numberOfReceiveButtonClicks = StatsSaver.getNumberOfReceiveButtonClicks();
    }

    public static void increment() {
        numberOfReceiveButtonClicks++;
        StatsSaver.saveNumberOfReceiveButtonClicks(numberOfReceiveButtonClicks);
    }

    public static String getNumberOfReceiveButtonClicksString() {
        return Integer.toString(numberOfReceiveButtonClicks);
    }
}
