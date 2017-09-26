package com.lapots.breed.platform.tpm.core.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TpmEvent {
    private String type;
    private String metadata;
}
