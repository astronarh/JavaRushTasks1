package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> treeSet = new TreeSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));) {
            while (bufferedReader.ready()) {
                char inChar = (char) Character.toLowerCase(bufferedReader.read());
                if (Character.isAlphabetic(inChar))treeSet.add(inChar);
            }
            int counter = 0;
            int mainCounter = 0;
            if (treeSet.size() > 5) counter = 5;
            else counter = treeSet.size();
            for (char x : treeSet) {
                System.out.print(x);
                mainCounter++;
                if (mainCounter == counter) break;
            }
        }
    }
}
