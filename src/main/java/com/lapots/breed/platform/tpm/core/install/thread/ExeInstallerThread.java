package com.lapots.breed.platform.tpm.core.install.thread;

import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.install.AbstractInstallationThread;
import com.lapots.breed.platform.tpm.core.install.installer.ExeInstaller;

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
