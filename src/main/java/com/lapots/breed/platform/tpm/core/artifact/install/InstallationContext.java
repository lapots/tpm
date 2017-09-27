package com.lapots.breed.platform.tpm.core.artifact.install;

import com.lapots.breed.platform.tpm.core.api.AbstractArtifactContext;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.DownloadUtils;
import com.lapots.breed.platform.tpm.core.artifact.install.thread.ExeInstallerThread;
import com.lapots.breed.platform.tpm.core.artifact.install.thread.MsiInstallerThread;
import com.lapots.breed.platform.tpm.core.artifact.install.thread.TarInstallerThread;
import com.lapots.breed.platform.tpm.core.artifact.install.thread.XZInstallerThread;

import java.util.Arrays;
import java.util.List;

public class InstallationContext extends AbstractArtifactContext {
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList("xz", "tar", "msi", "exe");
    private static InstallationContext instance;

    public static synchronized InstallationContext getInstance() {
        if (null == instance) {
            instance = new InstallationContext();
        }
        return instance;
    }

    @Override
    public void addArtifactToContext(Artifact artifact) {
        AbstractInstallationThread ait = generateThread(artifact);
        if (null != ait) {
            executeThread(generateThread(artifact));
        }
    }

    private AbstractInstallationThread generateThread(Artifact artifact) {
        String extension = DownloadUtils.downloadObjectExtension(artifact.getSrcLink());
        if (SUPPORTED_EXTENSIONS.contains(extension)) {
            switch (extension) {
                case "tar": return new TarInstallerThread(artifact);
                case "xz" : return new XZInstallerThread(artifact);
                case "msi": return new MsiInstallerThread(artifact);
                case "exe": return new ExeInstallerThread(artifact);
                default:
                    TpmEventBus.bus.publish(
                            new LogNotifyEvent("Cannot process further!", artifact)
                    );
                    TpmEventBus.bus.publish(
                            new InstallationEvent(TpmEventCode.SUCCESS, artifact)
                    );
            }
        } else {
            TpmEventBus.bus.publish(
                    new LogNotifyEvent("Extension " + extension + " is not supported!", artifact)
            );
        }

        return null;
    }
}
