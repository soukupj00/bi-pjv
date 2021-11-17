package cz.cvut.fit.pjv.alsa.common.dto;

import java.io.Serializable;

public class CreateResponse implements Serializable {

    private final boolean result;

    public CreateResponse(boolean result) {
        this.result = result;
    }

    public boolean result() {
        return result;
    }

}
