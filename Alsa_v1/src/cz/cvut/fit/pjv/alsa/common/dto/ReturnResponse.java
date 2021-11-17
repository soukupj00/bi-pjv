package cz.cvut.fit.pjv.alsa.common.dto;

import java.io.Serializable;

public class ReturnResponse implements Serializable {

    private final boolean result;

    public ReturnResponse(boolean result) {
        this.result = result;
    }

    public boolean result() {
        return result;
    }

}
