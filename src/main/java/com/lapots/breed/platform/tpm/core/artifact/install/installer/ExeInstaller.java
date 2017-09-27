package com.lapots.breed.platform.tpm.core.artifact.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.TpmEventCode;
import com.lapots.breed.platform.tpm.core.event.type.InstallationEvent;

// TODO: investigate EXE installation
public class ExeInstaller implements Installer {
    @Override
    public void install(Artifact artifact) {
        TpmEventBus.bus.publish(new InstallationEvent(TpmEventCode.SUCCESS, artifact));
    }
}
