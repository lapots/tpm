package com.lapots.breed.platform.tpm.core;

public class PackageJsonUtils {

    public static ToolJsonStructure getToolById(PackageJsonStructure packageJson, String toolId) {
        for (ToolJsonStructure tool : packageJson.getTools()) {
            if (toolId.equals(tool.getId())) {
                return tool;
            }
        }
        return null;
    }
}
