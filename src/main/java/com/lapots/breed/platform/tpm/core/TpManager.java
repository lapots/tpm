package com.lapots.breed.platform.tpm.core;

import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.artifact.download.DownloadContext;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.DownloadUtils;
import com.lapots.breed.platform.tpm.core.utils.FilePathUtils;
import com.lapots.breed.platform.tpm.core.json.PackageJsonStructure;
import com.lapots.breed.platform.tpm.core.json.ToolJsonStructure;
import org.apache.commons.io.FilenameUtils;

import java.util.List;

public class TpManager {

    private TpmLoader loader;

    public TpManager() {
        loader = new TpmLoader();
    }

    public void loadPackages(String filename) {
        if (null == filename || "".equals(filename)) {
            loader.loadTpmPackagesFromDefault();
        } else {
            loader.loadTpmPackageFromCustom(filename);
        }

        PackageJsonStructure loadedJson = doLoadEvent(filename);

        TpmEventBus.bus.publish(new LogNotifyEvent("downloading artifacts...", null));
        downloadPackages(loadedJson.getTools(), loadedJson.getConfig().getDownloadsFolder(),
                loadedJson.getConfig().getInstallationsFolder());
    }

    private PackageJsonStructure doLoadEvent(String filename) {
        TpmEventBus.bus.publish(new LogNotifyEvent("Processing " + filename, null));
        PackageJsonStructure loadedJson = loader.getPackageJson();
        prepareFolders(
                loadedJson.getConfig().getDownloadsFolder(),
                loadedJson.getConfig().getInstallationsFolder()
        );
        TpmEventBus.bus.publish(new LogNotifyEvent("finished loading json file", null));
        return loadedJson;
    }

    private void prepareFolders(String ... folders) {
        for (String folder : folders) {
            FilePathUtils.prepareFolder(folder);
        }
    }

    private void downloadPackages(List<ToolJsonStructure> tools, String downloadsFolder, String installationFolder) {
        DownloadContext downloadContext = DownloadContext.getInstance();
        for (ToolJsonStructure tool : tools) {
            Artifact artifact = new Artifact();
            // with filename
            String location = FilePathUtils.buildAbsolutePathFromFileLink(downloadsFolder,
                    FilenameUtils.getName(tool.getDownload()));
            artifact.setLocation(location);
            artifact.setSrcLink(tool.getDownload());
            artifact.setId(tool.getId());
            artifact.setInstallers(installationFolder);
            artifact.setName(DownloadUtils.downloadObjectName(tool.getDownload()));
            downloadContext.addArtifactToContext(artifact);
        }
    }
}
