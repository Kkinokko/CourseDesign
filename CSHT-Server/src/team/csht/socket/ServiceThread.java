package team.csht.socket;

import team.csht.entity.Good;
import team.csht.entity.SocketThread;
import team.csht.entity.User;
import team.csht.service.GoodService;
import team.csht.service.UserService;
import team.csht.util.CommandAnalyser;
import team.csht.util.CommandTranser;
import team.csht.util.SocketList;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/** @author MnAs & Fe */
public class ServiceThread extends Thread {
    private Socket socket;
    public ServiceThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        while (socket != null) {
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                CommandTranser message = (CommandTranser)ois.readObject();
                message = CommandAnalyser.analyse(message, socket);
                if (message.getSender().equals(message.getReceiver())) {
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);
                }
                else{
                    if (message.isFlag()) {
                        oos = new ObjectOutputStream(SocketList.getSocket(
                                message.getReceiver()).getOutputStream());
                    }
                    else {
                        oos = new ObjectOutputStream(socket.getOutputStream());
                    }
                }
            }
            catch (IOException e) {
                socket = null;
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}