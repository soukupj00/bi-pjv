package cz.cvut.fit.pjv.alsa.common.dto;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.io.Serializable;
import java.util.List;

public class ListResponse implements Serializable {

    private final List<Product> products;

    public ListResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> products() {
        return products;
    }

}
