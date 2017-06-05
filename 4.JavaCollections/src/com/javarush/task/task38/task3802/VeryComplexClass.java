package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream("D:/test.txt");
        int k;
        while(( k = fileInputStream.read() ) != -1)
        {
            System.out.print((char)k);
        }
        fileInputStream.close();
    }

    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        try {
            veryComplexClass.veryComplexMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
