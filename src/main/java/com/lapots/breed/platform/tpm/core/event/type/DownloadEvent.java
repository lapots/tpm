package com.lapots.breed.platform.tpm.core.event.type;

import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;

public class DownloadEvent extends TpmEvent<TpmEventCode> {
    public DownloadEvent(TpmEventCode metadata, Artifact artifact) {
        super(metadata, artifact);
    }
}
