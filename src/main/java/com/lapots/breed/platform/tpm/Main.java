package com.lapots.breed.platform.tpm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapots.breed.platform.tpm.core.ConfigJsonStructure;
import com.lapots.breed.platform.tpm.core.PackageJsonStructure;
import com.lapots.breed.platform.tpm.core.ToolJsonStructure;
import com.lapots.breed.platform.tpm.core.download.DownloadContext;
import com.lapots.breed.platform.tpm.core.file.FilePathUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        PackageJsonStructure packageJson;
        try (FileInputStream fis =
                     new FileInputStream(FilePathUtils.getClasspathFile("package.json"))) {
            packageJson = mapper.readValue(fis, PackageJsonStructure.class);
            if (null == packageJson) {
                throw new IllegalStateException("Failed to parse json.");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse json.", e);
        }

        ConfigJsonStructure config = packageJson.getConfig();
        FilePathUtils.prepareFolder(config.getDownloadsFolder());
        FilePathUtils.prepareFolder(config.getInstallationsFolder());
        DownloadContext dContext = new DownloadContext();
        for (ToolJsonStructure tool : packageJson.getTools()) {
            dContext.addToDownloads(tool.getDownload(), config.getDownloadsFolder());
        }
        dContext.closeContext();
    }
}
