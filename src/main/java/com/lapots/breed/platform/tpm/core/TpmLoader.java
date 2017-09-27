package com.lapots.breed.platform.tpm.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lapots.breed.platform.tpm.core.api.exception.TpmException;
import com.lapots.breed.platform.tpm.core.utils.file.FilePathUtils;
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
                throw new TpmException("Failed to parse: " + filename);
            }
        } catch (IOException e) {
            throw new TpmException("Failed to load: " + filename, e);
        }
    }
}
