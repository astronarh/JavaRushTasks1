package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isOneEditAway(String first, String second) {
        if (first.equals("") && second.equals("")) return true;
        if(first.equals(second)) return true;
        StringBuffer stringBuffer1 = (first.length() >= second.length()) ? new StringBuffer(first) : new StringBuffer(second);
        StringBuffer stringBuffer2 = (first.length() < second.length()) ? new StringBuffer(first) : new StringBuffer(second);
        for (int i = 0; i < stringBuffer2.length(); i++) {
            int pos = stringBuffer1.indexOf(String.valueOf(stringBuffer2.charAt(i)));
            if (pos != -1) stringBuffer1.deleteCharAt(pos);
        }
        if (stringBuffer1.length() == 1) return true;
        return false;
    }
}
