package com.lapots.breed.platform.tpm.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfigJsonStructure {
    @JsonProperty("downloads")
    private String downloadsFolder;
    @JsonProperty("installations")
    private String installationsFolder;
}
