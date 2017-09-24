package com.lapots.breed.platform.tpm.core.api;

import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public abstract class AbstractArtifactContext implements IArtifactContext {
    private ExecutorService service = Executors.newFixedThreadPool(5);

    protected void executeThread(AbstractExecutionThread thread) {
        service.submit(thread);
    }

    @Override
    public void closeContext() {
        service.shutdown();
    }
}
