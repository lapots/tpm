package com.lapots.breed.platform.tpm.core.artifact.install.thread;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.artifact.install.installer.ExeInstaller;

public class ExeInstallerThread extends AbstractInstallationThread {

    public ExeInstallerThread(Artifact artifact) {
        super(artifact);
        this.setInstaller(new ExeInstaller());
    }

    @Override
    public void run() {
        this.getInstaller().install(this.getArtifact());
    }
}
