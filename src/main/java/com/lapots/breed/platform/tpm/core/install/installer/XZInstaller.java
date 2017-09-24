package com.lapots.breed.platform.tpm.core.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XZInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        try (InputStream io = new FileInputStream(artifact.getDownloadPath());
                XZCompressorInputStream xz = new XZCompressorInputStream(io)) {
            // TODO: implement
        } catch (IOException e) {
            System.out.println("Failed to install " + artifact.getId());
        }
    }
}
