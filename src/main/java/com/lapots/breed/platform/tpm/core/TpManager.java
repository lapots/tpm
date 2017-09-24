package com.lapots.breed.platform.tpm.core;

import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.download.DownloadContext;
import com.lapots.breed.platform.tpm.core.file.FilePathUtils;
import com.lapots.breed.platform.tpm.core.json.PackageJsonStructure;
import com.lapots.breed.platform.tpm.core.json.ToolJsonStructure;

import java.nio.file.Path;
import java.util.List;

public class TpManager {

    private TpmLoader loader;

    public TpManager() {
        loader = new TpmLoader();
    }

    public void loadPackages(String filename) {
        // read file
        if (null == filename || "".equals(filename)) {
            loader.loadTpmPackagesFromDefault();
        } else {
            loader.loadTpmPackageFromCustom(filename);
        }

        // process json
        System.out.println("Processing " + filename);
        PackageJsonStructure loadedJson = loader.getPackageJson();
        prepareFolders(
                loadedJson.getConfig().getDownloadsFolder(),
                loadedJson.getConfig().getInstallationsFolder()
        );

        DownloadContext downloadContext = new DownloadContext();
        downloadPackages(loadedJson.getTools(), downloadContext,
                loadedJson.getConfig().getDownloadsFolder(), loadedJson.getConfig().getInstallationsFolder());
        downloadContext.closeContext();
    }

    private void prepareFolders(String ... folders) {
        for (String folder : folders) {
            FilePathUtils.prepareFolder(folder);
        }
    }

    private void downloadPackages(List<ToolJsonStructure> tools,
                                  DownloadContext downloadContext,
                                  String downloadsFolder,
                                  String installationFolder) {
        for (ToolJsonStructure tool : tools) {
            Artifact artifact = new Artifact();
            // with filename
            Path path = FilePathUtils.buildAbsolutePathFromFileLink(downloadsFolder, tool.getDownload());
            artifact.setDownloadPath(path.toString());
            artifact.setDownloadSource(tool.getDownload());
            artifact.setId(tool.getId());
            artifact.setInstallationPath(installationFolder);
            downloadContext.addArtifactToContext(artifact);
        }
    }
}
