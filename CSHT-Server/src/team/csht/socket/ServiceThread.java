package team.csht.socket;

import team.csht.entity.Good;
import team.csht.entity.SocketThread;
import team.csht.entity.User;
import team.csht.service.GoodService;
import team.csht.service.UserService;
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
                message = execute(message);
                try {
                    if (socket == null) {
                        return;
                    }
                    if (message.getReceiver().equals(message.getSender())) {
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(message);
                    }
                }
                catch (UnknownHostException e) {
                    JOptionPane.showMessageDialog(null, "错误码02");
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "错误码03");
                    e.printStackTrace();
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
    private CommandTranser execute(CommandTranser message) {
        if ("login".equals(message.getCommand())) {
            UserService userService = new UserService();
            User user = (User)message.getData();
            message.setFlag(userService.checkUser(user));
            if (message.isFlag()) {
                SocketThread socketThread = new SocketThread();
                socketThread.setName(message.getSender());
                socketThread.setSocket(socket);
                SocketList.addSocket(socketThread);
                message.setResult("loginSucceeded");
            }
            else {
                message.setResult("loginFailed");
            }
        }
        else if ("register".equals(message.getCommand())) {
            UserService userService = new UserService();
            User user = (User)message.getData();
            boolean hasUsername = userService.checkUsername(user);
            if (!hasUsername) {
                message.setFlag(userService.addUser(user));
                message.setResult("registerSucceeded");
            }
            else {
                message.setResult("usernameDuplicated");
            }
        }
        else if ("addGood".equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            boolean hasGood = goodService.checkGood(good.getName(), good.getMerchant());
            if (!hasGood) {
                message.setFlag(goodService.addGood(good));
                message.setResult("addGoodSucceeded");
            }
            else {
                message.setResult("goodDuplicated");
            }
        }
        return message;
    }
}