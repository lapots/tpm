package com.lapots.breed.platform.tpm.core.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathUtils {

    public static String buildSimplePath(String folderPath, String filename) {
        return FilenameUtils.separatorsToSystem(folderPath + File.separatorChar + filename);
    }

    public static String buildAbsolutePathFromFileLink(String folderPath, String link) {
        return buildAbsolutePath(folderPath, FilenameUtils.getName(link));
    }

    public static String buildAbsolutePath(String folderPath, String filename) {
        return buildSimplePath(folderPath, filename);
    }

    public static File prepareFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            // TODO: investigate
            file.getParentFile().mkdir();
            file.mkdirs();
        }
        return file;
    }

    public static File getClasspathFile(String filename) {
        ClassLoader classLoader = FilePathUtils.class.getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }
}
