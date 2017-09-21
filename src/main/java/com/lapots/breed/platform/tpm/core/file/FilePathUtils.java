package com.lapots.breed.platform.tpm.core.file;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathUtils {

    public static Path buildAbsolutePathFromFileLink(String folderPath, String link) {
        return buildAbsolutePath(folderPath, FilenameUtils.getName(link));
    }

    public static Path buildAbsolutePath(String folderPath, String filename) {
        String completePath = FilenameUtils.separatorsToSystem(folderPath + File.separatorChar + filename);
        return Paths.get(completePath);
    }

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
                Files.createDirectories(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File getClasspathFile(String filename) {
        ClassLoader classLoader = FilePathUtils.class.getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }
}
