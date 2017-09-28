package com.lapots.breed.platform.tpm.core;

public class TpmConfig {
    private static TpmConfig instance;

    private String downloadsFolder;
    private String installationFolder;

    public static synchronized TpmConfig getInstance() {
        if (null == instance) {
            instance = new TpmConfig();
        }
        return instance;
    }

    public void init(String downloadsFolder, String installationFolder) {
        this.downloadsFolder = downloadsFolder;
        this.installationFolder = installationFolder;
    }

    public String getDownloadsFolder() {
        return downloadsFolder;
    }

    public String getInstallationFolder() {
        return installationFolder;
    }

    @Override
    public String toString() {
        return "TpmConfig{" +
                "downloadsFolder='" + downloadsFolder + '\'' +
                ", installationFolder='" + installationFolder + '\'' +
                '}';
    }
}
