package com.lapots.breed.platform.tpm.core.api.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TpmEvent<T> {
    // TODO: aside metadata add artifact
    private T metadata;

    public TpmEvent(T metadata) {
        this.metadata = metadata;
    }
}
