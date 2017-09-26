package com.lapots.breed.platform.tpm.core.event;

import com.google.common.eventbus.Subscribe;

public class TpManagerEventListener {

    @Subscribe
    public void tpmEvent(TpmEvent event) {
        System.out.println("Event occurred: " + event);
    }
}
