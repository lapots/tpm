package com.lapots.breed.platform.tpm.core.event;

import com.google.common.eventbus.EventBus;
import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;
import com.lapots.breed.platform.tpm.core.event.processors.DownloadProcessor;
import com.lapots.breed.platform.tpm.core.event.processors.ErrorProcessor;
import com.lapots.breed.platform.tpm.core.event.processors.InstallationProcessor;
import com.lapots.breed.platform.tpm.core.event.processors.LogNotifyProcessor;

public enum TpmEventBus {
    bus;

    private EventBus eventBus = new TpmInternalEventBus();

    public <T extends TpmEvent> void publish(T event) {
        eventBus.post(event);
    }

    private class TpmInternalEventBus extends EventBus {
        TpmInternalEventBus() {
            register(new LogNotifyProcessor());
            register(new ErrorProcessor());
            register(new DownloadProcessor());
            register(new InstallationProcessor());
        }
    }
}
