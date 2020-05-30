package team.csht.entity;
import java.sql.Timestamp;

/** @author MnAs & Fe */
public class ShortMessage {
    private static final long serialVersionUID = 1L;

    private int number;
    private String sender;
    private String receiver;
    private String content;
    private Timestamp time;

    public ShortMessage() {
        super();
    }
    public ShortMessage(String sender, String receiver, String content) {
        super();
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
