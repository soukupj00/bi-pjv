package cz.cvut.fit.pjv.alsa.common.service;

import cz.cvut.fit.pjv.alsa.common.entity.Product;
import cz.cvut.fit.pjv.alsa.common.persistence.Database;

import java.util.*;
import java.util.stream.Stream;

public class EshopServiceImpl implements EshopService {

    private final Database database;

    public EshopServiceImpl(Database database) {
        this.database = database;
    }

    public List<Product> getProducts() {
        return database.findProducts();
    }

    public List<Product> getProducts(Comparator<Product> productComparator) {
        List<Product> products = new ArrayList<>(database.findProducts());
        Collections.sort(products, productComparator);
        return products;
    }

    public void addProductToStorage(Product product) {
        database.insertProduct(product);
    }

    public void addProductsToStorage(Product... products) {
        Stream.of(products).forEach(this::addProductToStorage);
    }

    public synchronized boolean sellProduct(int id) {
        Optional<Product> oProduct = database.findProductById(id);

        if (!oProduct.isPresent()) {
            return false;
        }
        Product product = oProduct.get();

        while (product.count() == 0) {
            if(!product.hasSpecialGuarantee()) {
                return false; //product without special guarantee will never get returned (count will stay at 0)
            }
            System.out.println("Waiting for a product (id " + id + ")...");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                return false;
            }
            product = database.findProductById(id).get();
        }

        Product updatedProduct = product.decreaseCount();
        database.updateProduct(updatedProduct);
        this.notifyAll();
        return true;
    }

    public synchronized boolean returnProduct(int id) {
        Optional<Product> oProduct = database.findProductById(id);

        if (!oProduct.isPresent()) {
            return false;
        }
        Product product = oProduct.get();

        if (!product.hasSpecialGuarantee()) {
            return false;
        }

        Product updatedProduct = product.increaseCount();
        database.insertProduct(updatedProduct);
        this.notifyAll();
        return true;
    }

}
