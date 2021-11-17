package cz.cvut.fit.pjv.alsa.client;

import cz.cvut.fit.pjv.alsa.common.dto.*;
import cz.cvut.fit.pjv.alsa.common.entity.Product;
import cz.cvut.fit.pjv.alsa.common.util.ProductUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Model {

    private ObjectOutputStream out;

    private ObjectInputStream in;

    public void initiliaze() {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 1234);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> listProducts() {
        sendRequest(new ListRequest());
        ListResponse response = retrieveResponse();
        ProductUtils.printProducts(response.products());
        return response.products();
    }

    public void createProduct(Product product) {
        sendRequest(new CreateRequest(product));
        CreateResponse response = retrieveResponse();
        System.out.printf("Create product (productId: %d, result: %b)\n", product.id(), response.result());
    }

    public void sellProduct(int productId) {
        sendRequest(new SellRequest(productId));
        SellResponse response = retrieveResponse();
        System.out.printf("Sell product (productId: %d, result: %b)\n", productId, response.result());
    }

    public void returnProduct(int productId) {
        sendRequest(new ReturnRequest(productId));
        ReturnResponse response = retrieveResponse();
        System.out.printf("Return product (productId: %d, result: %b)\n", productId, response.result());
    }

    private void sendRequest(Serializable request) {
        try {
            out.writeObject(request);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> T retrieveResponse() {
        try {
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
