package com.lapots.breed.platform.tpm.core.artifact.download;

import com.lapots.breed.platform.tpm.core.api.AbstractArtifactContext;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;

public class DownloadContext extends AbstractArtifactContext {
    @Override
    public void addArtifactToContext(Artifact artifact) {
        DownloadThread th = new DownloadThread();
        th.setArtifact(artifact);
        executeThread(th);
    }
}
