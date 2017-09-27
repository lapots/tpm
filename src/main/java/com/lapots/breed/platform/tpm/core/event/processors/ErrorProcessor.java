package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;

public class ErrorProcessor implements IEventProcessor<ErrorEvent> {
    @Subscribe
    @Override
    public void processEvent(ErrorEvent event) {
        System.out.println(event.getMetadata().getMessage());
    }
}