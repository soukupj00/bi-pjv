package cz.cvut.fit.pjv.alsa.common.dto;

import java.io.Serializable;

public class ReturnRequest implements Serializable {

    private final int productId;

    public ReturnRequest(int productId) {
        this.productId = productId;
    }

    public int productId() {
        return productId;
    }

}
