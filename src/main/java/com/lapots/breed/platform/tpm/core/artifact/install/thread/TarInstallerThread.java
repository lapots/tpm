package com.lapots.breed.platform.tpm.core.artifact.install.thread;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.artifact.install.installer.TarInstaller;

public class TarInstallerThread extends AbstractInstallationThread {

    public TarInstallerThread(Artifact artifact) {
        super(artifact);
        this.setInstaller(new TarInstaller());
    }

    @Override
    public void run() {
        this.getInstaller().install(this.getArtifact());
    }
}
