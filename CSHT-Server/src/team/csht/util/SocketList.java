package team.csht.util;

import team.csht.entity.SocketThread;

import java.net.Socket;
import java.util.HashMap;

/** @author MnAs & Fe */
public class SocketList {
    private static HashMap<String, Socket> map = new HashMap<>();
    public static void addSocket(SocketThread socketThread) {
        map.put(socketThread.getName(), socketThread.getSocket());
    }
    public static Socket getSocket(String name) {
        return map.get(name);
    }
}