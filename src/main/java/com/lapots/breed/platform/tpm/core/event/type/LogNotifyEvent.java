package com.lapots.breed.platform.tpm.core.event.type;

import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;

public class LogNotifyEvent extends TpmEvent<String> {
    public LogNotifyEvent(String metadata) {
        super(metadata);
    }
}
