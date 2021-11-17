package cz.cvut.fit.pjv.alsa.common.persistence;

import cz.cvut.fit.pjv.alsa.common.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDatabase implements Database {

    public static final String DATABASE_FILE = "alsa.db";

    public List<Product> findProducts() {
        return loadProducts();
    }

    public Optional<Product> findProductById(Integer id) {
        return loadProducts()
                .stream()
                .filter(p -> p.id() == id)
                .findFirst();
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
        List<Product> products = loadProducts();
        products.removeIf(productInDB -> productInDB.id() == product.id());
        products.add(product);
        persistProducts(products);
    }

    public void removeProduct(Product product) {
        List<Product> products = loadProducts();
        products.removeIf(productInDB -> productInDB.id() == product.id());
        persistProducts(products);
    }

    private List<Product> loadProducts() {
        try (FileInputStream in = new FileInputStream(DATABASE_FILE); ObjectInputStream oIn = new ObjectInputStream(in)) {
            List<Product> products = (List<Product>) oIn.readObject();
            return products != null ? products : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (EOFException e) {
            System.out.println("File is empty");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void persistProducts(List<Product> products) {
        try (FileOutputStream out = new FileOutputStream(new File(DATABASE_FILE)); ObjectOutputStream oOut = new ObjectOutputStream(out)) {
            oOut.writeObject(products);
            oOut.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
