package com.lapots.breed.platform.tpm.core.event.type;

import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;

public class LogNotifyEvent extends TpmEvent<String> {
    public LogNotifyEvent(String metadata, Artifact artifact) {
        super(metadata, artifact);
    }
}
