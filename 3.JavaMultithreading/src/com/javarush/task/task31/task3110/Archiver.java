package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 * Created by ShkerdinVA on 23.05.2017.
 */
public class Archiver {
    public static void main(String... args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("вести полный путь архива");
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(bufferedReader.readLine()));
            System.out.println("путь к файлу");
            zipFileManager.createZip(Paths.get(bufferedReader.readLine()));
        } catch (Exception e) {}
    }
}
