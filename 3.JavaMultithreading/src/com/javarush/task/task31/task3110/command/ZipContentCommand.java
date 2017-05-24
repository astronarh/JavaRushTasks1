package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.util.List;

/**
 * Created by ShkerdinVA on 23.05.2017.
 */
public class ZipContentCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Просмотр содержимого архива.");
        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Содержимое архива:");
        List<FileProperties> list = zipFileManager.getFilesList();
        for (FileProperties x : list) ConsoleHelper.writeMessage(x.toString());
        ConsoleHelper.writeMessage("Содержимое архива прочитано.");
    }
}
