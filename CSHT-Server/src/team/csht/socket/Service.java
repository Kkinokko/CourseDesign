package team.csht.socket;

import java.net.ServerSocket;
import java.net.Socket;

/** @author MnAs & Fe */
public class Service {
    public void startService() {
        try {
            ServerSocket serverSocket = new ServerSocket(8848);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                ServiceThread serviceThread = new ServiceThread(socket);
                serviceThread.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}