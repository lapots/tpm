package com.lapots.breed.platform.tpm.core.artifact.install.installer;

import com.lapots.breed.platform.tpm.core.TpmConfig;
import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.FilePathUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class TarInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        String nameWithoutExtension = FilenameUtils.getBaseName(artifact.getName());
        String extractedFilePath =
                FilePathUtils.buildSimplePath(TpmConfig.getInstance().getInstallationFolder(), nameWithoutExtension);
        TpmEventBus.bus.publish(
                new LogNotifyEvent("Extracting into " + extractedFilePath, null)
        );
        try (InputStream io = new FileInputStream(artifact.getLocation());
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

            artifact.setLocation(extractedFilePath);
            artifact.setName(nameWithoutExtension);
            TpmEventBus.bus.publish(new InstallationEvent(TpmEventCode.PENDING, artifact));
        } catch (IOException e) {
            TpmEventBus.bus.publish(new ErrorEvent(e, artifact));
        }
    }
}
