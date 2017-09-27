package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.LogUtils;
import org.slf4j.Logger;

public class LogNotifyProcessor implements IEventProcessor<LogNotifyEvent> {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Subscribe
    @Override
    public void processEvent(LogNotifyEvent event) {
        LOGGER.info(event.getMetadata());
    }
}
