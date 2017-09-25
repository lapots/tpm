package com.lapots.breed.platform.tpm.core.download;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownloadUtils {

    public static String downloadObjectName(String link) {
        return FilenameUtils.getBaseName(link);
    }

    public static String downloadObjectExtension(String link) {
        return FilenameUtils.getExtension(link);
    }

    public static void downloadReplace(String link, File downloadPathFile) {
        try {
            FileUtils.copyURLToFile(new URL(link), downloadPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
