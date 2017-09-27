package com.lapots.breed.platform.tpm.core.artifact.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.DownloadUtils;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
import com.lapots.breed.platform.tpm.core.utils.FilePathUtils;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XZInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        String nameWithoutExtension = FilenameUtils.getBaseName(artifact.getName());
        String extractedFilePath = FilePathUtils.buildSimplePath(artifact.getInstallers(), nameWithoutExtension);
        TpmEventBus.bus.publish(
                new LogNotifyEvent("Extracting into " + extractedFilePath, null)
        );
        try (InputStream io = new FileInputStream(artifact.getLocation());
                XZCompressorInputStream xz = new XZCompressorInputStream(io)) {
            IOUtils.copy(xz, new FileOutputStream(extractedFilePath));
            TpmEventBus.bus.publish(new InstallationEvent(TpmEventCode.PENDING, artifact));
        } catch (IOException e) {
            TpmEventBus.bus.publish(new ErrorEvent(e, artifact));
        }
    }
}
