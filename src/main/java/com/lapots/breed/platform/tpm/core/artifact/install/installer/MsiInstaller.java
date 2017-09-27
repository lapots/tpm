package com.lapots.breed.platform.tpm.core.artifact.install.installer;

import com.lapots.breed.platform.tpm.core.api.installer.Installer;
import com.lapots.breed.platform.tpm.core.artifact.consistency.Artifact;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

import java.io.IOException;

public class MsiInstaller implements Installer {
    private static final String MSI_EXEC = "msiexec.exe";
    private static final long INSTALLATION_TIME = 60000; // switch to infinite?
    private static final int SUCCESS_INSTALL_CODE = 1;

    @Override
    public void install(Artifact artifact) {
        CommandLine cmd = new CommandLine(MSI_EXEC);
        // TODO: configure cmd line

        Executor exec = new DefaultExecutor();
        exec.setExitValue(SUCCESS_INSTALL_CODE);
        exec.setWatchdog(new ExecuteWatchdog(INSTALLATION_TIME));
        try {
            int exitValue = exec.execute(cmd);
            if (exitValue != SUCCESS_INSTALL_CODE) {
                // TODO: throw something or goto 30 line
            }
        } catch (IOException e) {
            TpmEventBus.bus.publish(new ErrorEvent(e, artifact));
        }
    }
}
