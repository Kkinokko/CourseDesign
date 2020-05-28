package team.csht.ui.im;
import team.csht.socket.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
/** @author MnAs & Fe */
public class IM {
    public static void main(String[] args) {
        new IMFrame("username0", null);
    }
}
class IMFrame implements ActionListener {
    String username = "username0";
    String friend = "username1";
    Client client = null;

    JFrame imFrame = new JFrame();
    JTextField receiverTextField = new JTextField();
    JButton receiverButton = new JButton("确认");
    JLabel logLabel = new JLabel();
    JTextArea sendTextArea = new JTextArea();
    JButton sendButton = new JButton("发送");

    public IMFrame(String username, Client client) {
        final int DEFAULT_WIDTH = 800;
        final int DEFAULT_HEIGHT = 600;

        this.username = username;
        this.client = client;
        /*--- 界面效果显示 ---*/
        // 创建最外层的JFrame
        imFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        imFrame.setTitle("学生闲置物品交易平台 - 聊天");
        imFrame.setResizable(false);
        imFrame.setLocationRelativeTo(null);
        imFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO:背景图片
        // 最上方的Panel：收件人：________ 选择
        JPanel receiverPanel = new JPanel();
        receiverPanel.setOpaque(false);
        JLabel receiverLabel = new JLabel();
        receiverLabel.setFont(new Font("黑体", Font.PLAIN,16));
        receiverTextField.setBorder(null);
        receiverTextField.setBackground(null);
        receiverPanel.add(receiverLabel);
        receiverPanel.add(receiverTextField);
        receiverPanel.add(receiverButton);
        // 中间的聊天记录框
        JPanel logPanel = new JPanel();
        logPanel.add(logLabel);
        // 下方的输入框和发送按钮
        JPanel sendPanel = new JPanel();
        sendPanel.add(sendTextArea);
        sendPanel.add(sendButton);
        // 创建一个盒子把它们装进去
        Box imBox = Box.createVerticalBox();
        imBox.add(receiverPanel);
        imBox.add(logPanel);
        imBox.add(sendPanel);
        // 显示
        imFrame.setContentPane(imBox);
        imFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == receiverButton) {
            this.friend = receiverTextField.getText();
        }
        if (e.getSource() == sendButton) {
            String content = sendTextArea.getText();

        }
    }
}
