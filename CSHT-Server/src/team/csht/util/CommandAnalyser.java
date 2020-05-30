package team.csht.util;

import team.csht.entity.Good;
import team.csht.entity.SocketThread;
import team.csht.entity.User;
import team.csht.service.GoodService;
import team.csht.service.UserService;

import java.net.Socket;

/** @author MnAs & Fe */
public class CommandAnalyser {
    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String GETGOODS = "getgoods";

    public CommandAnalyser() {
        super();
    }

    public static CommandTranser analyse(CommandTranser message, Socket socket) {
        if (REGISTER.equals(message.getCommand())) {
            UserService userService = new UserService();
            User user = (User)message.getData();

            boolean hasUsername = userService.checkUsername(user);
            if (!hasUsername) {
                message.setFlag(userService.addUser(user));
                if (message.isFlag()) {
                    message.setResult("registerSucceeded");
                }
                else {
                    message.setResult("registerFailed");
                }
            }
            else {
                message.setResult("usernameDuplicated");
            }
        }

        else if (LOGIN.equals(message.getCommand())) {
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
        else if (GETGOODS.equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            message.setData(goodService.getGoodList());
        }





        else if ("addGood".equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if(!goodService.addGood(good)){
                message.setResult("addGoodSucceeded");
            }
            else {
                message.setResult("addGoodFailed");
            }
        }
        else if("deleteGood".equals(message.getCommand())){
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if(!goodService.checkGood(good)){
                message.setResult("goodDeleteSucceeded");
            }
            else {
                message.setResult("goodDeleteFailed");
            }
        }
        return message;
    }
}
