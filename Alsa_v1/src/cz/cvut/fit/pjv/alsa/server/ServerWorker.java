package cz.cvut.fit.pjv.alsa.server;

import cz.cvut.fit.pjv.alsa.common.dto.*;
import cz.cvut.fit.pjv.alsa.common.entity.Product;
import cz.cvut.fit.pjv.alsa.common.service.EshopService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerWorker {

    private final Socket client;

    private final EshopService eshopService;

    public ServerWorker(Socket client, EshopService eshopService) {
        this.client = client;
        this.eshopService = eshopService;
    }

    public void start() {
        new Thread(this::work).start();
    }

    protected void work() {
        try (ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
            while (true) {
                Object oRequest = in.readObject();

                if (oRequest instanceof ListRequest) {
                    log("List request");
                    List<Product> products = eshopService.getProducts();
                    ListResponse response = new ListResponse(products);
                    out.writeObject(response);
                    out.flush();
                    continue;
                }

                if (oRequest instanceof CreateRequest) {
                    log("Create request");
                    CreateRequest request = (CreateRequest) oRequest;
                    eshopService.addProductsToStorage(request.product());
                    CreateResponse response = new CreateResponse(true);
                    out.writeObject(response);
                    out.flush();
                    continue;
                }

                if (oRequest instanceof SellRequest) {
                    log("Sell request");
                    SellRequest request = (SellRequest) oRequest;
                    boolean result = eshopService.sellProduct(request.productId());
                    SellResponse response = new SellResponse(result);
                    out.writeObject(response);
                    out.flush();
                    continue;
                }

                if (oRequest instanceof ReturnRequest) {
                    log("Return request");
                    ReturnRequest request = (ReturnRequest) oRequest;
                    boolean result = eshopService.returnProduct(request.productId());
                    ReturnResponse response = new ReturnResponse(result);
                    out.writeObject(response);
                    out.flush();
                    continue;
                }
            }
        } catch (EOFException e) {
            log("Client ended the connection");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void log(String message) {
        System.out.println("[WORKER]: " + message);
    }
}
