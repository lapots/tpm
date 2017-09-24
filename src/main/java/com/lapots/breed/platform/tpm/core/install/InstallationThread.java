package com.lapots.breed.platform.tpm.core.install;

import com.lapots.breed.platform.tpm.core.api.AbstractExecutionThread;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;

public class InstallationThread extends AbstractExecutionThread {
    @Override
    public void run() {
        Artifact artifact = this.getArtifact();
        // TODO: implement (configure destinations, resolve installers...)
        // TODO: OR!!!!! You can create separate threads for different installers!
    }
}
