package team.csht.socket;
import team.csht.entity.ShortMessage;
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
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CommandTranser message = client.getData();
            if (message != null) {
                if (message.isFlag()) {
                    ShortMessage[] shortMessageList = (ShortMessage[])message.getData();
                    setLogLabel(shortMessageList, contentTextArea);
                }
                else {
                    JOptionPane.showMessageDialog(null, message.getResult());
                }
            }
        }
    }
    public void setLogLabel(ShortMessage[] shortMessageList, JTextArea contentTextArea) {
        int length = shortMessageList.length;
        contentTextArea.setText(null);
        for (int i = 0; i < length; i ++) {
            String sender = shortMessageList[i].getSender();
            String receiver = shortMessageList[i].getReceiver();
            String content = shortMessageList[i].getContent();
            String messageString = sender + " 对 " + receiver + " 说： \n" + content + "\n";
            contentTextArea.append(messageString);
        }
    }
}