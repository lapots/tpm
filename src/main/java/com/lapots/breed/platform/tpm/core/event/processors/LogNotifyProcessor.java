package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;

public class LogNotifyProcessor implements IEventProcessor<LogNotifyEvent> {
    @Subscribe
    @Override
    public void processEvent(LogNotifyEvent event) {
        System.out.println(event.getMetadata());
    }
}
