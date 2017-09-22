package com.lapots.breed.platform.tpm.core.consistency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  ArtifactRepository {
    PERSISTENCE;

    private Map<String, Artifact> artifacts = new ConcurrentHashMap<>();

    public void addArtifact(Artifact artifact) {
        if (null != artifacts.get(artifact.getId())) {
            System.out.println("Updating existing artifact " + artifact.getId() + " !");
        }
        artifacts.put(artifact.getId(), artifact);
    }
}
