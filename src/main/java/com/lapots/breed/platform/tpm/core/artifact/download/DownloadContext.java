package com.lapots.breed.platform.tpm.core.artifact.download;

import com.lapots.breed.platform.tpm.core.api.AbstractArtifactContext;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;

public class DownloadContext extends AbstractArtifactContext {
    private static DownloadContext instance;

    public static synchronized DownloadContext getInstance() {
        if (null == instance) {
            instance = new DownloadContext();
        }
        return instance;
    }

    @Override
    public void addArtifactToContext(Artifact artifact) {
        DownloadThread th = new DownloadThread();
        th.setArtifact(artifact);
        executeThread(th);
    }
}
