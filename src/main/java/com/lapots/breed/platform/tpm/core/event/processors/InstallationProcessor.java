package com.lapots.breed.platform.tpm.core.event.processors;

import com.google.common.eventbus.Subscribe;
import com.lapots.breed.platform.tpm.core.api.event.IEventProcessor;
import com.lapots.breed.platform.tpm.core.artifact.consistency.ArtifactRepository;
import com.lapots.breed.platform.tpm.core.artifact.install.InstallationContext;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;

public class InstallationProcessor implements IEventProcessor<InstallationEvent> {
    @Subscribe
    @Override
    public void processEvent(InstallationEvent event) {
        if (TpmEventCode.PENDING == event.getMetadata()) { // put it back into processing loop
            InstallationContext.getInstance().addArtifactToContext(event.getArtifact());
        } else {
            event.getArtifact().setInstalled(true);
            ArtifactRepository.PERSISTENCE.addArtifact(event.getArtifact());
        }
    }
}
