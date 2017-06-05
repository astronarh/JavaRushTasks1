package com.javarush.task.task38.task3804;

/**
 * Created by ShkerdinVA on 05.06.2017.
 */
public class ExceptionFactory {
    public static Throwable getException(Enum eenum) {
        if (eenum != null) {
            if (eenum instanceof ExceptionApplicationMessage) {
                return new Exception(eenum.name().charAt(0) + eenum.name().substring(1).toLowerCase().replace("_", " "));
            }
            else if (eenum instanceof ExceptionDBMessage) {
                return new RuntimeException(eenum.name().charAt(0) + eenum.name().substring(1).toLowerCase().replace("_", " "));
            }
            else if (eenum instanceof ExceptionUserMessage) {
                return new Error(eenum.name().charAt(0) + eenum.name().substring(1).toLowerCase().replace("_", " "));
            }
        }
        return new IllegalArgumentException();
    }
}
