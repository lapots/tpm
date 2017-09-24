package com.lapots.breed.platform.tpm.core.install;

import com.lapots.breed.platform.tpm.core.api.AbstractArtifactContext;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;

public class InstallationContext extends AbstractArtifactContext {
    @Override
    public void addArtifactToContext(Artifact artifact) {
        InstallationThread ih = new InstallationThread();
        ih.setArtifact(artifact);
        executeThread(ih);
    }
}
