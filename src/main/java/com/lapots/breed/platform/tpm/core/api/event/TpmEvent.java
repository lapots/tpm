package com.lapots.breed.platform.tpm.core.api.event;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TpmEvent<T> {
    private Artifact artifact;
    private T metadata;

    public TpmEvent(T metadata, Artifact artifact) {
        this.metadata = metadata;
        this.artifact = artifact;
    }
}
