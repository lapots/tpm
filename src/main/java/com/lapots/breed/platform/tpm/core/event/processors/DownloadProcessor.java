package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.artifact.consistency.ArtifactRepository;
import com.lapots.breed.platform.tpm.core.artifact.install.InstallationContext;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.DownloadEvent;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;

public class DownloadProcessor implements IEventProcessor<DownloadEvent> {
    @Subscribe
    @Override
    public void processEvent(DownloadEvent event) {
        event.getArtifact().setDownloaded(true);
        ArtifactRepository.PERSISTENCE.addArtifact(event.getArtifact());
        // now start installation
        InstallationContext.getInstance()
                .addArtifactToContext(event.getArtifact());
    }
}
