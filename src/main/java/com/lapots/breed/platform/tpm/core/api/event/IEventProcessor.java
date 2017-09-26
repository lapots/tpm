package com.lapots.breed.platform.tpm.core.api.event;

public interface IEventProcessor<T extends TpmEvent> {
    void processEvent(T event);
}
