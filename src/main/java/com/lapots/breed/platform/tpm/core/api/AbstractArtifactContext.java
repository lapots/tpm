package com.lapots.breed.platform.tpm.core.api;

import com.lapots.breed.platform.tpm.core.api.exception.TpmException;
import lombok.Data;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
public abstract class AbstractArtifactContext implements IArtifactContext {
    private ExecutorService service = Executors.newFixedThreadPool(5);

    protected void executeThread(AbstractExecutionThread thread) {
        service.submit(thread);
    }

    @PreDestroy
    @Override
    public void closeContext() {
        System.out.println("Terminating...");
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new TpmException("Something when wrong during downloading...");
        }
    }
}
