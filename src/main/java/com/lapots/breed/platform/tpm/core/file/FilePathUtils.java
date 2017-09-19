package com.lapots.breed.platform.tpm.core.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilePathUtils {

    public static File prepareFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Files.createDirectories(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File prepareFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            try {
                Files.createDirectory(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File getClasspathFile(String filename) {
        ClassLoader classLoader = File.class.getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }
}
