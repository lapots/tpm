package com.lapots.breed.platform.tpm.core.api;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import lombok.Data;

@Data
public abstract class AbstractExecutionThread implements Runnable {
    private Artifact artifact;
}
