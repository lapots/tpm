package com.lapots.breed.platform.tpm.core.artifact.install;

import com.lapots.breed.platform.tpm.core.api.AbstractExecutionThread;
import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public abstract class AbstractInstallationThread extends AbstractExecutionThread {
    private Installer installer;

    public AbstractInstallationThread(Artifact artifact) {
        this.setArtifact(artifact);
    }
}
