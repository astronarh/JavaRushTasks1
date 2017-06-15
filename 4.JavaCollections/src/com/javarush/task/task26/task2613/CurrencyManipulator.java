package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShkerdinVA on 15.06.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> x : denominations.entrySet()) totalAmount += x.getKey() * x.getValue();
        return totalAmount;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }
}
