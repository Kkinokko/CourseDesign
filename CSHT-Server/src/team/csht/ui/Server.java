package team.csht.ui;

import team.csht.socket.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author MnAs & Fe */
public class Server {
    public static void main(String[] args) {
        ServerFrame serverFrame = new ServerFrame();
    }
}

class ServerFrame implements ActionListener {
    JButton serverStartButton = new JButton("开启服务");
    JButton serverEndButton = new JButton("关闭服务");

    public ServerFrame() {
        JFrame serverFrame = new JFrame();
        serverFrame.setSize(400, 300);
        serverFrame.setTitle("学生闲置物品交易平台 - 服务器端");
        serverFrame.setLocationRelativeTo(null);
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel serverPanel = new JPanel();
        serverPanel.add(serverStartButton);
        serverPanel.add(serverEndButton);
        serverFrame.setContentPane(serverPanel);
        serverFrame.setVisible(true);

        // 按键监听
        serverStartButton.addActionListener(this);
        serverEndButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == serverStartButton) {
            new ServerStartThread().start();
            JOptionPane.showMessageDialog(null, "开启成功！");
        }
        if (actionEvent.getSource() == serverEndButton) {
            System.exit(0);
        }
    }
}

class ServerStartThread extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.startService();
    }
}