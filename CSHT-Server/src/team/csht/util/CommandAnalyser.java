package team.csht.util;

import team.csht.entity.*;
import team.csht.service.CommentService;
import team.csht.service.GoodService;
import team.csht.service.ShortMessageService;
import team.csht.service.UserService;

import java.net.Socket;

/** @author MnAs & Fe */
public class CommandAnalyser {
    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String GET_GOOD_LIST = "getGoodList";
    private static final String ADD_GOOD = "addGood";
    private static final String BUY_GOOD = "buyGood";
    private static final String SEARCH_GOOD = "searchGood";
    private static final String ADD_COMMENT = "addComment";
    private static final String GET_COMMENT_LIST = "getCommentList";
    private static final String ADD_SHORT_MESSAGE = "addShortMessage";
    private static final String GET_SHORT_MESSAGE = "getShortMessage";

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
                message.setResult("getGoodListFailed");
            }
        }

        else if (ADD_GOOD.equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if(goodService.addGood(good)){
                message.setFlag(true);
                message.setResult("addGoodSucceeded");
            }
            else {
                message.setResult("addGoodFailed");
            }
        }

        else if (BUY_GOOD.equals(message.getCommand())){
            GoodService goodService = new GoodService();
            Good good = (Good)message.getData();
            if (goodService.buyGood(good)) {
                message.setFlag(true);
                message.setResult("buyGoodSucceeded");
            }
            else {
                message.setResult("buyGoodFailed");
            }
        }

        else if (SEARCH_GOOD.equals(message.getCommand())) {
            GoodService goodService = new GoodService();
            String keyword = (String)message.getData();
            message.setData(goodService.searchGood(keyword));
        }

        else if (ADD_COMMENT.equals(message.getCommand())) {
            CommentService commentService = new CommentService();
            Comment comment = (Comment)message.getData();
            if (commentService.addComment(comment)) {
                message.setFlag(true);
                message.setResult("addCommentSucceeded");
            }
            else {
                message.setResult("addCommentFailed");
            }
        }

        else if (GET_COMMENT_LIST.equals(message.getCommand())) {
            CommentService commentService = new CommentService();
            Good good = (Good)message.getData();
            message.setData(commentService.getCommentList(good.getId()));
            if (message.getData() != null) {
                message.setFlag(true);
                message.setResult("getCommentListSucceeded");
            }
            else {
                message.setResult("getCommentListFailed");
            }
        }

        else if (ADD_SHORT_MESSAGE.equals(message.getCommand())) {
            ShortMessageService shortMessageService = new ShortMessageService();
            ShortMessage shortMessage = (ShortMessage)message.getData();
            if (shortMessageService.addShortMessage(shortMessage)) {
                message.setFlag(true);
                message.setResult("addShortMessageSucceeded");
            }
            else {
                message.setResult("addShortMessageFailed");
            }
        }

        else if (GET_SHORT_MESSAGE.equals(message.getCommand())) {
            ShortMessageService shortMessageService = new ShortMessageService();
            ShortMessage shortMessage = (ShortMessage)message.getData();
            String sender = shortMessage.getSender();
            String receiver = shortMessage.getReceiver();
            message.setData(shortMessageService.getShortMessage(sender, receiver));
            if (message.getData() != null) {
                message.setFlag(true);
                message.setResult("getShortMessageSucceeded");
            }
            else {
                message.setResult("getShortMessageFailed");
            }
        }

        else {
            message.setResult("failedToReadCommand");
        }
        return message;
    }
}