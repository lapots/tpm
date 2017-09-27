package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;

public class InstallationProcessor implements IEventProcessor<InstallationEvent> {
    @Subscribe
    @Override
    public void processEvent(InstallationEvent event) {
        // TODO: implement
    }
}
