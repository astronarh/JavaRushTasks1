package com.javarush.task.task36.task3602;

import java.lang.reflect.Modifier;
import java.util.Collections;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            for (Class interfaze : clazz.getInterfaces()) {
                if (interfaze.getSimpleName().equals("List") && Modifier.isStatic(clazz.getModifiers())) {
                    //System.out.println(clazz.getName());
                }
            }
        }
        try {
            return Class.forName("java.util.Collections$EmptyList");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
