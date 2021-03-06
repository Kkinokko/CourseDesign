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
    private static final String GET_GOOD_LIST = "getGoodList";
    private static final String ADD_GOOD = "addGood";
    private static final String BUY_GOOD = "buyGood";

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

        else if (GET_GOOD_LIST.equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            message.setData(goodService.getGoodList());
            if (message.getData() != null) {
                message.setFlag(true);
                message.setResult("getGoodListSucceeded");
            }
            else {
                message.setResult("");
            }
        }

        else if (ADD_GOOD.equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if(goodService.addGood(good)){
                message.setResult("addGoodSucceeded");
            }
            else {
                message.setResult("addGoodFailed");
            }
        }

        else if(BUY_GOOD.equals(message.getCommand())){
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if (goodService.buyGood(good)) {
                message.setResult("buyGoodSucceeded");
            }
            else {
                message.setResult("buyGoodFailed");
            }
        }
        return message;
    }
}