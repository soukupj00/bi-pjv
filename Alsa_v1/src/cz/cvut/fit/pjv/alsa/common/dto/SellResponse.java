package cz.cvut.fit.pjv.alsa.common.dto;

import java.io.Serializable;

public class SellResponse implements Serializable {

    private final boolean result;

    public SellResponse(boolean result) {
        this.result = result;
    }

    public boolean result() {
        return result;
    }

}
