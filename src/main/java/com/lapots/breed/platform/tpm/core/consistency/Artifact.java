package com.lapots.breed.platform.tpm.core.consistency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artifact {
    private String id;
    private String downloadSource;
    private String downloadPath;
    private String installationPath;
    private boolean isInstalled;
    private boolean isDownloaded;
}
