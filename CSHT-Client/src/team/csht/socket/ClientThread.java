package team.csht.socket;
import team.csht.util.CommandTranser;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
/** @author MnAs & Fe */
public class ClientThread extends Thread {
    private Client client;
    private boolean isOnline = true;
    private JTextArea contentTextArea;

    public ClientThread(Client client, JTextArea contentTextArea) {
        this.client = client;
        this.contentTextArea = contentTextArea;
    }

    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public void run() {
        while (isOnline) {
            CommandTranser message = client.getData();
            if (message != null) {
                if (message.isFlag()) {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                            "yy-MM-dd hh:mm:ss a");
                    String messageString = message.getSender() + ""
                            + (String)message.getData() + "\t"
                            + simpleDateFormat.format(date) + "\n";
                    contentTextArea.append(messageString);
                }
                else {
                    JOptionPane.showMessageDialog(contentTextArea, message.getResult());
                }
            }
        }
    }
}