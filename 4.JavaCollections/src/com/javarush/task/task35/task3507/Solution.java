package com.javarush.task.task35.task3507;

import com.javarush.task.task35.task3507.data.Cat;
import com.javarush.task.task35.task3507.data.Sheep;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        File dir = new File(pathToAnimals);
        String prefix = Solution.class.getPackage().getName() + ".data.";
        for (File file : dir.listFiles()) {

            System.out.println(file);

            try {
                Class<? extends Animal> cls = (Class<? extends Animal>) Class.forName(prefix + file.getName().replaceFirst(".class", ""));
                set.add(cls.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            }
        }
        return (Set<? extends Animal>) set;
    }
}

