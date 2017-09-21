package com.lapots.breed.platform.tpm.core.download;

import com.lapots.breed.platform.tpm.core.file.FilePathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.file.Path;

@AllArgsConstructor
@Data
public class DownloadThread implements Runnable {
    private String downloadLink;
    private String downloadPath;

    @Override
    public void run() {
        System.out.println("Doing download");
        Path downloadPathFile =  FilePathUtils.buildAbsolutePathFromFileLink(downloadPath, downloadLink);
        DownloadUtils.downloadReplace(downloadLink, downloadPathFile.toFile());
        System.out.println("Finished downloading from " + downloadLink);
    }
}
