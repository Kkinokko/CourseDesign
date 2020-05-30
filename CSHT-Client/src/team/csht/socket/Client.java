package team.csht.socket;

import team.csht.entity.Good;
import team.csht.entity.User;
import team.csht.util.CommandTranser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/** @author MnAs & Fe */
public class Client {
    private Socket socket;

    public Client() {
        try {
            int port = 8848;
            String address = "127.0.0.1";
            socket = new Socket(address, port);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误码01：服务器未开启");
        }
    }

    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendData(CommandTranser message) {
        ObjectOutputStream oos = null;
        try {
            if (socket == null) {
                return;
            }
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        }
        catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "错误码02");
            e.printStackTrace();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "错误码03");
            e.printStackTrace();
        }
    }
    public CommandTranser getData() {
        ObjectInputStream ois = null;
        CommandTranser message = null;
        if (socket == null) {
            return null;
        }
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            message = (CommandTranser)ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "错误码04");
            e.printStackTrace();
            return null;
        }
        return message;
    }
}