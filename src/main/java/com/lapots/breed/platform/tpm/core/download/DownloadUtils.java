package com.lapots.breed.platform.tpm.core.download;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownloadUtils {

    public static void downloadReplace(String link, File downloadPathFile) {
        try {
            FileUtils.copyURLToFile(new URL(link), downloadPathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
