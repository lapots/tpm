package com.lapots.breed.platform.tpm.core.json;

import lombok.Data;

import java.util.List;

@Data
public class PackageJsonStructure {
    private ConfigJsonStructure config;
    private List<ToolJsonStructure> tools;
}
