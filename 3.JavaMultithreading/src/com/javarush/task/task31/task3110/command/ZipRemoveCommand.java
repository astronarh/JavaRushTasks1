package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ShkerdinVA on 23.05.2017.
 */
public class ZipRemoveCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление файла из архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите полный путь файла в архиве:");
        Path path = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(path);
        ConsoleHelper.writeMessage("Удаление из архива завершено.");
    }
}
