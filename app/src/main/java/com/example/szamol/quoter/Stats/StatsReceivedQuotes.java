package com.example.szamol.quoter.Stats;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class StatsReceivedQuotes {
    private static Set<String> receivedQuotes = new HashSet<>();

    public static void load() {
        receivedQuotes = StatsSaver.getSetOfReceivedQuotes();
    }

    public static void addReceivedQuote(String receivedQuote) {
        receivedQuotes.add(Integer.toString(receivedQuote.hashCode()));
        StatsSaver.saveSetOfReceivedQuotes(receivedQuotes);
    }

    public static String getPercentOfUniqueRecivedQuotes() {
        double receivedQuotesNumber = receivedQuotes.size();
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(receivedQuotesNumber / StatsAllQuotes.getTotalQuotes()) + " %";
    }
}