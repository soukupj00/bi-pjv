package cz.cvut.fit.pjv.alsa.common.service;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.util.Comparator;
import java.util.List;

public interface EshopService {

    List<Product> getProducts();

    List<Product> getProducts(Comparator<Product> productComparator);

    void addProductToStorage(Product product);

    void addProductsToStorage(Product... products);

    boolean sellProduct(int id);

    boolean returnProduct(int id);

}
