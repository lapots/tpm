package com.lapots.breed.platform.tpm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapots.breed.platform.tpm.core.ConfigJsonStructure;
import com.lapots.breed.platform.tpm.core.PackageJsonStructure;
import com.lapots.breed.platform.tpm.core.ToolJsonStructure;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.download.DownloadThread;
import com.lapots.breed.platform.tpm.core.download.DownloadUtils;
import com.lapots.breed.platform.tpm.core.file.FilePathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        PackageJsonStructure packageJson = null;
        try (FileInputStream fis =
                     new FileInputStream(FilePathUtils.getClasspathFile("package.json"))) {
            packageJson = mapper.readValue(fis, PackageJsonStructure.class);
            if (null == packageJson) {
                throw new IllegalStateException("Failed to parse json.");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to parse json.", e);
        }

        // print package.json content
        System.out.println("package.json content");
        // config
        System.out.println("config ->");
        ConfigJsonStructure config = packageJson.getConfig();
        System.out.println("Download path: " + config.getDownloadsFolder());
            FilePathUtils.prepareFolder(config.getDownloadsFolder());
        System.out.println("Installation path: " + config.getInstallationsFolder());
            FilePathUtils.prepareFolder(config.getInstallationsFolder());

        List<ToolJsonStructure> tools = packageJson.getTools();
        System.out.println("tools ->");
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (ToolJsonStructure tool : tools) {
            System.out.println("Tool id -> " + tool.getId());
            System.out.println("Tool download link -> " + tool.getDownload());

            DownloadThread th = new DownloadThread(tool.getDownload(), config.getDownloadsFolder());
            service.submit(th);
        }
        System.out.println("Left the loop. Do something...");
        service.shutdown();
    }
}
