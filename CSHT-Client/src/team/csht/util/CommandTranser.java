package team.csht.util;

import java.io.Serializable;

/** @author MnAs & Fe */
public class CommandTranser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sender = null;
    private String receiver = null;
    private Object data = null;
    private boolean flag = false;
    private String command = null;

    private String result = null;

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}