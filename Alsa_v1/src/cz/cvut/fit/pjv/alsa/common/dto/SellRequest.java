package cz.cvut.fit.pjv.alsa.common.dto;

import java.io.Serializable;

public class SellRequest implements Serializable {

    private final int productId;

    public SellRequest(int productId) {
        this.productId = productId;
    }

    public int productId() {
        return productId;
    }

}
