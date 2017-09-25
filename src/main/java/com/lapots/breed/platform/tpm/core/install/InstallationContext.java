package com.lapots.breed.platform.tpm.core.install;

import com.lapots.breed.platform.tpm.core.api.AbstractArtifactContext;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.download.DownloadUtils;
import com.lapots.breed.platform.tpm.core.install.thread.ExeInstallerThread;
import com.lapots.breed.platform.tpm.core.install.thread.MsiInstallerThread;
import com.lapots.breed.platform.tpm.core.install.thread.TarInstallerThread;
import com.lapots.breed.platform.tpm.core.install.thread.XZInstallerThread;

import java.util.Arrays;
import java.util.List;

public class InstallationContext extends AbstractArtifactContext {
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("xz", "tar", "msi", "exe");

    @Override
    public void addArtifactToContext(Artifact artifact) {
        AbstractInstallationThread ait = generateThread(artifact);
        if (null != ait) {
            executeThread(generateThread(artifact));
        }
    }

    private AbstractInstallationThread generateThread(Artifact artifact) {
        String extension = DownloadUtils.downloadObjectExtension(artifact.getDownloadSource());
        System.out.println("Artifact extension: " + extension);
        if (SUPPORTED_EXTENSIONS.contains(extension)) {
            switch (extension) {
                case "tar":
                    return new TarInstallerThread(artifact);
                case "xz":
                    return new XZInstallerThread(artifact);
                case "msi":
                    return new MsiInstallerThread(artifact);
                case "exe":
                    return new ExeInstallerThread(artifact);
                default:
                    System.out.println("Cannot process further!");
            }
        } else {
            System.out.println("Extension " + extension + " is not supported!");
        }

        return null;
    }
}
