package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ShkerdinVA on 23.05.2017.
 */
public class ZipFileManager {
    private final Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {

        //New ---------------------------------
        Path path = zipFile.getParent();
        if (Files.notExists(path))
            Files.createDirectories(path);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (Files.isDirectory(source)) {
                FileManager fileManager = new FileManager(source);
                List<Path> list = fileManager.getFileList();
                for (Path x : list) addNewZipEntry(zipOutputStream, source, x);
            } else if (Files.isRegularFile(source)) {
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else {
                throw new PathIsNotFoundException();
            }
        }
        // ------------------------------------

        /*try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
             InputStream inputStream = Files.newInputStream(source); ) {
            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zipOutputStream.putNextEntry(zipEntry);
            while (inputStream.available() > 0) zipOutputStream.write(inputStream.read());
        } catch (Exception e) {}*/
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {

        //New -----------------------------------------------
        Path path = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(path)) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);
            copyData(inputStream, zipOutputStream);
            zipOutputStream.closeEntry();
        }
        //---------------------------------------------------

        /*Path path = filePath.resolve(fileName);
        try (InputStream inputStream = Files.newInputStream(path);) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] byteBuffer = new byte[8 * 1024];
            int lenght;
            while ((lenght = inputStream.read(byteBuffer)) > 0) zipOutputStream.write(byteBuffer, 0, lenght);
            zipOutputStream.closeEntry();
        }*/
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }

}
