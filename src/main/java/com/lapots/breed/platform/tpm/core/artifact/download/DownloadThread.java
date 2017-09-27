package com.lapots.breed.platform.tpm.core.artifact.download;

import com.lapots.breed.platform.tpm.core.api.AbstractExecutionThread;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.DownloadEvent;
import com.lapots.breed.platform.tpm.core.event.type.LogNotifyEvent;
import com.lapots.breed.platform.tpm.core.utils.DownloadUtils;

import java.io.File;

public class DownloadThread extends AbstractExecutionThread {
    @Override
    public void run() {
        Artifact artifact = this.getArtifact();
        TpmEventBus.bus.publish(
                new LogNotifyEvent("Attempt to download artifact: " + artifact.getId(), artifact)
        );
        DownloadUtils.downloadReplace(artifact.getDownloadSource(), new File(artifact.getDownloadPath()));
        TpmEventBus.bus.publish(new DownloadEvent(TpmEventCode.SUCCESS, artifact));
    }
}
