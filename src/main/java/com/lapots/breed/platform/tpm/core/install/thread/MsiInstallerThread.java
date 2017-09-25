package com.lapots.breed.platform.tpm.core.install.thread;

import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.install.installer.MsiInstaller;

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
