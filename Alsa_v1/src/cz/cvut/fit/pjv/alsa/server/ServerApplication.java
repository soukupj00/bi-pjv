package cz.cvut.fit.pjv.alsa.server;

import cz.cvut.fit.pjv.alsa.common.persistence.InMemoryDatabase;
import cz.cvut.fit.pjv.alsa.common.persistence.factory.DatabaseFactory;
import cz.cvut.fit.pjv.alsa.common.persistence.factory.SQLiteDatabaseFactory;
import cz.cvut.fit.pjv.alsa.common.service.EshopService;
import cz.cvut.fit.pjv.alsa.common.service.EshopServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    private static EshopService createEshop() {
        DatabaseFactory factory = new SQLiteDatabaseFactory();
        return new EshopServiceImpl(factory.createDatabase());
    }

    public static void main(String[] args) throws IOException {
        // Create e-shop
        EshopService eshopService = new EshopServiceImpl(new InMemoryDatabase());

        // Init server
        ServerSocket serverSocket = new ServerSocket(1234);
        log("Server is listening on port 1234");
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    log("New client joined");
                    new ServerWorker(socket, eshopService).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void log(String message) {
        System.out.println("[SERVER]: " + message);
    }

}
