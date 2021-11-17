package cz.cvut.fit.pjv.alsa.common.persistence;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface Database {

    List<Product> findProducts();

    Optional<Product> findProductById(Integer id);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(Product product);

    public static final Database DUMMY_DATABASE = new Database() {
        @Override
        public List<Product> findProducts() {
            return Collections.emptyList();
        }

        @Override
        public Optional<Product> findProductById(Integer id) {
            return Optional.empty();
        }


        @Override
        public void insertProduct(Product product) {

        }

        @Override
        public void updateProduct(Product product) {

        }

        @Override
        public void removeProduct(Product product) {

        }
    };

}
