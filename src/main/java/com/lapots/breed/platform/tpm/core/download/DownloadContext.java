package com.lapots.breed.platform.tpm.core.download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadContext {
    private ExecutorService service = Executors.newFixedThreadPool(5);

    public void addToDownloads(String downloadLink, String downloadsFolder) {
        DownloadThread th = new DownloadThread(downloadLink, downloadsFolder);
        service.submit(th);
    }

    public void closeContext() {
        service.shutdown();
    }
}
