package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        /*Integer[] first = new Integer[]{13, 8, 15, 5, 17};
        Solution.sort(first);
        for (int i = 0; i < first.length; i++)
        {
            System.out.println(first[i]);
        }*/
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        double median = 0;
        Integer[] secondArray = array;
        Arrays.sort(secondArray);
        if (secondArray.length%2==0) {
            int a = secondArray[(secondArray.length/2)-1];
            int b = secondArray[secondArray.length/2];
            median = (a+b)/2.0;
        }
        else if (secondArray.length % 2 == 1) median = secondArray[(secondArray.length / 2)];
        final double finalMedian = median;
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int result =(int) ((Math.abs(o1- finalMedian)) - (Math.abs(o2 - finalMedian)));
                return result == 0 ? o1-o2 : result;
            }
        };
        Arrays.sort(array,comparator);
        return array;
    }
}
