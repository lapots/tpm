package com.lapots.breed.platform.tpm.core.download;

import com.lapots.breed.platform.tpm.core.api.AbstractExecutionThread;
import com.lapots.breed.platform.tpm.core.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.consistency.ArtifactRepository;

import java.io.File;

public class DownloadThread extends AbstractExecutionThread {
    @Override
    public void run() {
        Artifact artifact = this.getArtifact();
        System.out.println("Attempt to download artifact: " + artifact.getId());
        DownloadUtils.downloadReplace(artifact.getDownloadSource(), new File(artifact.getDownloadPath()));
        artifact.setDownloaded(true);
        ArtifactRepository.PERSISTENCE.addArtifact(artifact);
    }
}
