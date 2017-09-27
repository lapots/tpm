package com.lapots.breed.platform.tpm.core.artifact.install.thread;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.artifact.install.installer.XZInstaller;

public class XZInstallerThread extends AbstractInstallationThread {

    public XZInstallerThread(Artifact artifact) {
        super(artifact);
        this.setInstaller(new XZInstaller());
    }

    @Override
    public void run() {
        this.getInstaller().install(this.getArtifact());
    }
}
