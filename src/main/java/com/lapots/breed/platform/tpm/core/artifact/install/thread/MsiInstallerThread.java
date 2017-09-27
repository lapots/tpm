package com.lapots.breed.platform.tpm.core.artifact.install.thread;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.artifact.install.installer.MsiInstaller;

public class MsiInstallerThread extends AbstractInstallationThread {

    public MsiInstallerThread(Artifact artifact) {
        super(artifact);
        this.setInstaller(new MsiInstaller());
    }

    @Override
    public void run() {
        this.getInstaller().install(this.getArtifact());
    }
}
