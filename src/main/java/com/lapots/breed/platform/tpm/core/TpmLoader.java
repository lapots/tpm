package com.lapots.breed.platform.tpm.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapots.breed.platform.tpm.core.api.exception.TpmException;
import com.lapots.breed.platform.tpm.core.event.TpmEventBus;
import com.lapots.breed.platform.tpm.core.event.type.ErrorEvent;
import com.lapots.breed.platform.tpm.core.utils.FilePathUtils;
import com.lapots.breed.platform.tpm.core.json.PackageJsonStructure;
import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;

@Data
public class TpmLoader {
    private static final String FILE = "tpm_package.json";
    private PackageJsonStructure packageJson;

    public void loadTpmPackagesFromDefault() {
        loadTpmPackageFromCustom(FILE);
    }

    public void loadTpmPackageFromCustom(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try (FileInputStream fis =
                     new FileInputStream(FilePathUtils.getClasspathFile(filename))) {
            packageJson = mapper.readValue(fis, PackageJsonStructure.class);
            if (null == packageJson) {
                TpmEventBus.bus.publish(
                        new ErrorEvent(new TpmException("Failed to parse: " + filename), null)
                );
            }
        } catch (IOException e) {
            TpmEventBus.bus.publish(new ErrorEvent(e, null));
        }
    }
}
