package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by ShkerdinVA on 14.06.2017.
 */
public class CashMachine {
    public static void main(String... args) throws InterruptOperationException {
        /*Locale.setDefault(Locale.ENGLISH);
        CurrencyManipulator currencyManipulator;
        String code = ConsoleHelper.askCurrencyCode();
        String[] strs = ConsoleHelper.getValidTwoDigits(code);
        currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        currencyManipulator.addAmount(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
        System.out.print(currencyManipulator.getTotalAmount());*/
        Operation operation;
        do {
            operation = ConsoleHelper.askOperation();
            CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
    }
}
