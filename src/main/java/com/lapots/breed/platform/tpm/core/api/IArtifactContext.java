package com.lapots.breed.platform.tpm.core.api;

import com.lapots.breed.platform.tpm.core.consistency.Artifact;

public interface IArtifactContext {
    void addArtifactToContext(Artifact artifact);
    void closeContext();
}
