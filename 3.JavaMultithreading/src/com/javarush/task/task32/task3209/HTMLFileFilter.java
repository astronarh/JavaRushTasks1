package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by ShkerdinVA on 05.06.2017.
 */
public class HTMLFileFilter extends FileFilter{
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) return true;
        else if (!f.isDirectory()) {
            String string = f.getName().toLowerCase();
            return string.endsWith(".html") || string.endsWith(".htm");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
