package com.lapots.breed.platform.tpm.core.utils.download;

import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
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
            TpmEventBus.bus.publish(new ErrorEvent(e));
        }
    }
}
