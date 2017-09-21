package com.lapots.breed.platform.tpm.core.consistency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Artifact {
    private String id;
    private String installationPath;
}
