package com.javarush.task.task35.task3507;

import com.javarush.task.task35.task3507.data.Cat;
import com.javarush.task.task35.task3507.data.Sheep;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Set<Animal> result = new HashSet<>();
        File dir = new File(pathToAnimals);

        File[] files = getClassFiles(path);

        try {
            for (File classFile : files) {
                ClassLoader cl = getFileClassLoader(classFile);
                String className = classFile.getName().replace(".class", "");
                Class clazz = cl.loadClass(className);

                boolean hasInterface = false;
                Class[] interfaces = clazz.getInterfaces();
                for (Class i : interfaces)
                    if (Animal.class == i) {
                        hasInterface = true;
                        break;
                    }
                if (!hasInterface) continue;

                boolean hasConstructor = false;
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor c : constructors)
                    if (Modifier.isPublic(c.getModifiers()) && c.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                if (!hasConstructor) continue;

                result.add((Animal) clazz.newInstance());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static File[] getClassFiles(Path path) {
        File[] files = path.toFile().listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".class");
            }
        });
        return files;
    }

    public static ClassLoader getFileClassLoader(final File classFile) {
        return new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                byte[] bytes = getBytes(classFile);
                return defineClass(null, bytes, 0, bytes.length);
            }
        };
    }


    private static byte[] getBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

