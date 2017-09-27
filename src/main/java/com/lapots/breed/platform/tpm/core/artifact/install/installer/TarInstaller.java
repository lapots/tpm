package com.lapots.breed.platform.tpm.core.artifact.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.download.DownloadUtils;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
import com.lapots.breed.platform.tpm.core.utils.file.FilePathUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class TarInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        String extractedFilePath = FilePathUtils.buildSimplePath(artifact.getInstallationPath(),
                DownloadUtils.downloadObjectName(artifact.getDownloadSource()));
        System.out.println("Attempt to extract tar: " + artifact.getDownloadPath());
        System.out.println("Extracted file name: " + extractedFilePath);
        try (InputStream io = new FileInputStream(artifact.getDownloadPath());
             TarArchiveInputStream ts = new TarArchiveInputStream(io)) {
            TarArchiveEntry archiveEntry = ts.getNextTarEntry();
            while (archiveEntry != null) {
                File file = new File(FilePathUtils.buildSimplePath(extractedFilePath, archiveEntry.getName()));
                if (file.isDirectory()) {
                    file.mkdirs();
                } else {
                    IOUtils.copy(ts, new FileOutputStream(file));
                }
                archiveEntry = ts.getNextTarEntry();
            }
        } catch (IOException e) {
            TpmEventBus.bus.publish(new ErrorEvent(e));
        }
    }
}
