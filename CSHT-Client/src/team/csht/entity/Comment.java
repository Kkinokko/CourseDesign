package team.csht.entity;
import java.io.Serializable;
import java.sql.Timestamp;
/** @author MnAs & Fe */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int number;
    private int goodId;
    private String username;
    private String content;
    private Timestamp timestamp;

    public Comment() {
        super();
    }
    public Comment(String username, String content) {
        super();
        this.username = username;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getGoodId() {
        return goodId;
    }
    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}