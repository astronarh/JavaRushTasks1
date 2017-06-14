package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.print(isPalindromePermutation("tot tot tota"));
    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        s = s.replaceAll(" ", "");
        boolean[] isOdd = new boolean[256];
        for (int i = 0; i < s.length(); i++) isOdd[s.charAt(i)] = !isOdd[s.charAt(i)];
        int numberOdds = 0;
        for (int i = 0; i < isOdd.length; i++) {
            if (isOdd[i]) numberOdds++;
            if (numberOdds > 1) return false;
        }
        return true;
    }
}
