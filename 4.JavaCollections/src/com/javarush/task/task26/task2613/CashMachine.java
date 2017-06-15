package com.javarush.task.task26.task2613;

import java.util.Locale;

/**
 * Created by ShkerdinVA on 14.06.2017.
 */
public class CashMachine {
    public static void main(String... args) {
        Locale.setDefault(Locale.ENGLISH);
        CurrencyManipulator currencyManipulator;
        String code = ConsoleHelper.askCurrencyCode();
        String[] strs = ConsoleHelper.getValidTwoDigits(code);
        currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
    }
}
