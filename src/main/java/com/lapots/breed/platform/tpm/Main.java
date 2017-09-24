package com.lapots.breed.platform.tpm;

import com.lapots.breed.platform.tpm.core.TpManager;

public class Main {

    public static void main(String[] args) {
        TpManager manager = new TpManager();
        manager.loadPackages("package.json");
    }
}
