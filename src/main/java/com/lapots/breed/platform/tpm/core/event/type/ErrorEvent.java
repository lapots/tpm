package com.lapots.breed.platform.tpm.core.event.type;

import com.lapots.breed.platform.tpm.core.api.event.TpmEvent;

public class ErrorEvent extends TpmEvent<Exception> {
    public ErrorEvent(Exception metadata) {
        super(metadata);
    }
}
