package com.lapots.breed.platform.tpm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapots.breed.platform.tpm.core.ConfigJsonStructure;
import com.lapots.breed.platform.tpm.core.PackageJsonStructure;
import com.lapots.breed.platform.tpm.core.ToolJsonStructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        PackageJsonStructure packageJson = null;
        try (FileInputStream fis = new FileInputStream(getClasspathFile("package.json"))) {
            packageJson = mapper.readValue(fis, PackageJsonStructure.class);
            if (null == packageJson) {
                throw new IllegalStateException("Failed to parse json.");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse json.", e);
        }

        // print package.json content
        System.out.println("package.json content");
        System.out.println("config ->");
        ConfigJsonStructure config = packageJson.getConfig();
        System.out.println("Download path: " + config.getDownloads());
        System.out.println("Installation path: " + config.getInstallations());
        List<ToolJsonStructure> tools = packageJson.getTools();
        System.out.println("tools ->");
        for (ToolJsonStructure tool : tools) {
            System.out.println("Tool id -> " + tool.getId());
            System.out.println("Tool download link -> " + tool.getDownload());
        }
    }

    private static File getClasspathFile(String filename) {
        ClassLoader classLoader = ObjectMapper.class.getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }
}
