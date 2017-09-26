package com.lapots.breed.platform.tpm.core.event;

import com.google.common.eventbus.EventBus;

public enum TpmEventBus {
    bus;

    private EventBus eventBus = new EventBus();

    // not needed actually
    public void registerListener(TpManagerEventListener listener) {
        eventBus.register(listener);
    }

    public void publish(TpmEvent event) {
        eventBus.post(event);
    }
}
