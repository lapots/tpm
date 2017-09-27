package com.lapots.breed.platform.tpm.core.event.type;

import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;

public class ErrorEvent extends TpmEvent<Exception> {
    public ErrorEvent(Exception metadata, Artifact artifact) {
        super(metadata, artifact);
    }
}
