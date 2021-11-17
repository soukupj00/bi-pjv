package cz.cvut.fit.pjv.alsa.common.dto;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.io.Serializable;

public class CreateRequest implements Serializable {

    private final Product product;

    public CreateRequest(Product product) {
        this.product = product;
    }

    public Product product() {
        return product;
    }

}
