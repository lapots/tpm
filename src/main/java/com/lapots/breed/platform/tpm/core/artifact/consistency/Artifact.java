package com.lapots.breed.platform.tpm.core.artifact.consistency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artifact {
    private String id;
    private String name;
    private String srcLink;
    private String location; // for downloads - it is destination, for installation - source
    private boolean installed;
    private boolean downloaded;
}
