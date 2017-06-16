package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(2);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        StringBuilder stringBuilder = new StringBuilder(number + " =");
        List<Integer> list = new LinkedList<>();
        while (number > 0) {
            list.add(number % 3);
            number /= 3;
        }
        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            int l = listIterator.next();
            switch (l) {
                case 3:
                    listIterator.set(0);
                    if (listIterator.hasNext()) list.set(listIterator.nextIndex(), list.get(listIterator.nextIndex()) + 1);
                    else listIterator.add(1);
                    break;
                case 2:
                    listIterator.set(-1);
                    if (listIterator.hasNext()) list.set(listIterator.nextIndex(), list.get(listIterator.nextIndex()) + 1);
                    else listIterator.add(1);
                    break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 0) stringBuilder.append(list.get(i) >= 0 ? " + " : " - ").append(Math.abs((int) Math.pow(3, i) * (list.get(i)))).toString();
        }
        System.out.println(stringBuilder);
    }
}