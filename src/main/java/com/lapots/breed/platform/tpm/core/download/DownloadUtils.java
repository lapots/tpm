package com.lapots.breed.platform.tpm.core.download;

import com.lapots.breed.platform.tpm.core.file.FilePathUtils;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URL;

public class DownloadUtils {

    public static void download(String link, String downloadPathFile) {
        try {
            FileUtils.copyURLToFile(new URL(link),
                    FilePathUtils.prepareFile(downloadPathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
