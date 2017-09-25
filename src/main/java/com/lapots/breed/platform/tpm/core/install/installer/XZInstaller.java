package com.lapots.breed.platform.tpm.core.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.download.DownloadUtils;
import com.lapots.breed.platform.tpm.core.file.FilePathUtils;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XZInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        String extractedFilePath = FilePathUtils.buildSimplePath(artifact.getInstallationPath(),
                DownloadUtils.downloadObjectName(artifact.getDownloadPath()));
        System.out.println("Object name without extension: " + DownloadUtils.downloadObjectName(artifact.getDownloadPath()));
        System.out.println("Attempt to extract xz: " + artifact.getDownloadPath());
        System.out.println("Extracted file name: " + extractedFilePath);
        try (InputStream io = new FileInputStream(artifact.getDownloadPath());
                XZCompressorInputStream xz = new XZCompressorInputStream(io)) {
            IOUtils.copy(xz, new FileOutputStream(extractedFilePath));
        } catch (IOException e) {
            System.out.println("Failed to install " + artifact.getId());
        }
    }
}
