package com.lapots.breed.platform.tpm.core.artifact.consistency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  ArtifactRepository {
    PERSISTENCE;

    private Map<String, Artifact> artifacts = new ConcurrentHashMap<>();

    public synchronized void addArtifact(Artifact artifact) {
        if (null != artifacts.get(artifact.getId())) {
            System.out.println("Updating existing artifact " + artifact.getId() + " !");
        }
        artifacts.put(artifact.getId(), artifact);
    }

    public List<Artifact> getNotInstalledArtifacts() {
        List<Artifact> notInstalled = new ArrayList<>();
        artifacts.forEach((key, value) -> {
            if (!value.isInstalled()) {
                notInstalled.add(value);
            }
        });
        return notInstalled;
    }
}
