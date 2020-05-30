package team.csht.ui.im;
import team.csht.entity.ShortMessage;
import team.csht.socket.Client;
import team.csht.socket.ClientThread;
import team.csht.util.CommandTranser;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
/** @author MnAs & Fe */
public class IM {
    public static void main(String[] args) {
        new IMFrame("username0", null);
    }
}
class IMFrame implements ActionListener {
    String username = "username0";
    String receiver = "username1";
    Client client = null;
    ClientThread thread;

    JFrame imFrame = new JFrame();
    JTextField receiverTextField = new JTextField(15);
    JButton receiverButton = new JButton("确认");
    JTextArea logLabel = new JTextArea();
    JTextArea sendTextArea = new JTextArea(2,38);
    JButton sendButton = new JButton("发送");

    public IMFrame(String username, Client client) {
        final int DEFAULT_WIDTH = 700;
        final int DEFAULT_HEIGHT = 500;

        this.username = username;
        this.client = client;
        /*--- 界面效果显示 ---*/
        // 创建最外层的JFrame
        imFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        imFrame.setTitle("学生闲置物品交易平台 - 聊天");
        imFrame.setResizable(false);
        imFrame.setLocationRelativeTo(null);
        imFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 背景图片
        ImageIcon bgi=new ImageIcon("src\\res\\bg\\chat.png");
        JLabel bg = new JLabel();
        bg.setIcon(bgi);
        bg.setBounds(0,0,bgi.getIconWidth(),bgi.getIconHeight());
        imFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
        // 最上方的Panel：收件人：________ 选择
        JPanel receiverPanel = new JPanel();
        receiverPanel.setOpaque(false);
        JLabel receiverLabel = new JLabel("收件人");
        receiverLabel.setFont(new Font("黑体", Font.PLAIN,18));
        receiverTextField.setBorder(null);
        receiverTextField.setFont(new Font("黑体", Font.PLAIN,18));
        receiverPanel.add(receiverLabel);
        receiverPanel.add(receiverTextField);
        receiverPanel.add(receiverButton);
        // 中间的聊天记录框
        JPanel logPanel = new JPanel();
        logLabel.setLineWrap(true);
        logLabel.setEditable(false);
        logLabel.setBackground(null);
        logLabel.setFont(new Font(null, Font.PLAIN,18));
        logPanel.add(logLabel);
        logLabel.setSize(30,500);
        JScrollPane jsp= new JScrollPane(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER){//sp1滚动面板的大小
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(150,300);//括号内参数，可以根据需要更改
            }
        };
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setViewportView(logPanel);

        // 下方的输入框和发送按钮
        JPanel sendPanel = new JPanel();
        sendPanel.setOpaque(false);
        sendTextArea.setLineWrap(true);
        sendPanel.add(sendTextArea);
        sendPanel.add(sendButton);

        // 创建一个盒子把它们装进去
        Box imBox = Box.createVerticalBox();
        imBox.add(Box.createVerticalStrut(20));
        imBox.add(receiverPanel);
        imBox.add(jsp);
        imBox.add(Box.createVerticalStrut(30));
        imBox.add(sendPanel);
        //创一个JSP把它们装进去

        Box lastBox = Box.createHorizontalBox();
        lastBox.add(Box.createHorizontalStrut(40));
        lastBox.add(imBox);
        lastBox.add(Box.createHorizontalStrut(40));

        // 显示
        imFrame.setContentPane(lastBox);
        imFrame.setVisible(true);

        /*--- 线程 ---*/
        // TODO:如下
        thread = new ClientThread(client, logLabel);
        thread.start();

        /*--- 事件 ---*/
        imFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thread.setOnline(false);
            }
            @Override
            public void windowClosed(WindowEvent e) {
                thread.setOnline(false);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receiverButton) {
            this.receiver = receiverTextField.getText();
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setSender(username);
            shortMessage.setReceiver(receiver);
            CommandTranser message = new CommandTranser();
            message.setCommand("getShortMessage");
            message.setSender(username);
            message.setReceiver(username);
            client.sendData(message);

            ShortMessage[] shortMessageList = (ShortMessage[])client.getData().getData();
            setLogLabel(shortMessageList);
        }
        if (e.getSource() == sendButton) {
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setSender(username);
            shortMessage.setReceiver(receiver);
            shortMessage.setContent(sendTextArea.getText().trim());

            CommandTranser message = new CommandTranser();
            message.setCommand("addShortMessage");
            message.setSender(username);
            message.setReceiver(receiver);
            message.setData(shortMessage);
            client.sendData(message);

            sendTextArea.setText(null);
            message.setCommand("getShortMessage");
            client.sendData(message);
            ShortMessage[] shortMessageList = (ShortMessage[])client.getData().getData();
            setLogLabel(shortMessageList);
        }
    }

    public void setLogLabel(ShortMessage[] shortMessageList) {
        int length = shortMessageList.length;
        logLabel.setText(null);
        for (int i = 0; i < length; i ++) {
            String sender = shortMessageList[i].getSender();
            String receiver = shortMessageList[i].getReceiver();
            String content = shortMessageList[i].getContent();
            String messageString = sender + " 对 " + receiver + " 说： \n" + content + "\n";
            logLabel.append(messageString);
        }
    }
}