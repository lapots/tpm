package com.lapots.breed.platform.tpm.core.api;

import com.lapots.breed.platform.tpm.core.exception.TpmException;
import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
public abstract class AbstractArtifactContext implements IArtifactContext {
    private ExecutorService service = Executors.newFixedThreadPool(5);

    protected void executeThread(AbstractExecutionThread thread) {
        service.submit(thread);
    }

    @Override
    public void closeContext() {
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new TpmException("Something when wrong during downloading...");
        }
    }
}
