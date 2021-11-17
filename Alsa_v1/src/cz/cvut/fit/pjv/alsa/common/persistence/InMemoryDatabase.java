package cz.cvut.fit.pjv.alsa.common.persistence;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.util.*;

public class InMemoryDatabase implements Database {

    private final Map<Integer, Product> products = new HashMap<>();

    public List<Product> findProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findProductById(Integer id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    @Override
    public void insertProduct(Product product) {
        saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        saveProduct(product);
    }

    public void saveProduct(Product product) {
        products.put(product.id(), product);
    }

    public void removeProduct(Product product) {
        products.remove(product.id());
    }
}
