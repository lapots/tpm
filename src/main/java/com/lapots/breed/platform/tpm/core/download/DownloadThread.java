package com.lapots.breed.platform.tpm.core.download;

import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.consistency.ArtifactRepository;
import com.lapots.breed.platform.tpm.core.file.FilePathUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;

@AllArgsConstructor
@Data
public class DownloadThread implements Runnable {
    private String downloadLink;
    private String downloadPath;

    @Override
    public void run() {
        System.out.println("Attempt to download from " + downloadLink);
        Path downloadPathFile =  FilePathUtils.buildAbsolutePathFromFileLink(downloadPath, downloadLink);
        DownloadUtils.downloadReplace(downloadLink, downloadPathFile.toFile());
        Artifact artifact = new Artifact(FilenameUtils.getName(downloadLink), downloadPathFile.toString());
        ArtifactRepository.PERSISTENCE.addArtifact(artifact);
    }
}
