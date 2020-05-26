package team.csht.UI.welcome;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** @author MnAs & Fe */
public class Welcome {
    public static void main(String[] args) {
        new WelcomeFrame();
    }
}

class WelcomeFrame {
    final int DEFAULT_WIDTH = 800;
    final int DEFAULT_HEIGHT = 600;

    JFrame welcomeFrame = new JFrame();
    public WelcomeFrame() {
        /*--- 界面效果显示 ---*/
        // 最外层的JFrame
        welcomeFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        welcomeFrame.setTitle("学生闲置物品交易平台");
        welcomeFrame.setResizable(false);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 引入背景
        ImageIcon welcomeBackgroundImageIcon = new ImageIcon("src\\res\\bg\\bg1.jpg");
        JLabel welcomeBackgroundLabel = new JLabel();
        welcomeBackgroundLabel.setIcon(welcomeBackgroundImageIcon);
        welcomeBackgroundLabel.setBounds(0, 0, welcomeBackgroundImageIcon.getIconWidth(), welcomeBackgroundImageIcon.getIconHeight());
        welcomeFrame.getLayeredPane().add(welcomeBackgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
        // space
        JPanel welcomeSpacePanel = new JPanel();
        welcomeSpacePanel.setOpaque(false);
        JLabel welcomeSpaceLabel = new JLabel(" ");
        welcomeSpacePanel.add(welcomeSpaceLabel);
        // 创建标题
        JPanel welcomeTitlePanel = new JPanel();
        welcomeTitlePanel.setOpaque(false);
        JLabel welcomeTitleLabel = new JLabel("学生闲置物品交易平台");
        welcomeTitleLabel.setFont(new Font("黑体", Font.BOLD,26));
        welcomeTitleLabel.setForeground(Color.white);
        welcomeTitlePanel.add(welcomeTitleLabel);
        // 创建登录注册按钮
        JPanel welcomeButtonPanel = new JPanel();
        welcomeButtonPanel.setOpaque(false);
        JButton welcomeRegisterButton = new JButton();
        welcomeRegisterButton.setBorder(null);
        welcomeRegisterButton.setIcon(new ImageIcon("src\\res\\icon\\register.png"));
        welcomeRegisterButton.setPressedIcon(new ImageIcon("src\\res\\icon\\register2.png"));
        welcomeRegisterButton.setContentAreaFilled(false);
        JButton welcomeLoginButton = new JButton();
        welcomeLoginButton.setBorder(null);
        welcomeLoginButton.setIcon(new ImageIcon("src\\res\\icon\\login.png"));
        welcomeLoginButton.setPressedIcon(new ImageIcon("src\\res\\icon\\login2.png"));
        welcomeLoginButton.setContentAreaFilled(false);
        welcomeButtonPanel.add(welcomeRegisterButton);
        welcomeButtonPanel.add(welcomeLoginButton);
        // 创建作者栏
        JPanel welcomeAuthorPanel = new JPanel();
        welcomeAuthorPanel.setOpaque(false);
        JLabel welcomeAuthorLabel = new JLabel("By：种豆星露谷，草盛豌豆稀");
        welcomeAuthorLabel.setFont(new Font("黑体", Font.BOLD,15));
        welcomeAuthorLabel.setForeground(Color.white);
        welcomeAuthorPanel.add(welcomeAuthorLabel);
        // 创建盒子
        Box welcomeBox = Box.createVerticalBox();
        welcomeBox.add(welcomeSpacePanel);
        welcomeBox.add(welcomeTitlePanel);
        welcomeBox.add(welcomeButtonPanel);
        welcomeBox.add(welcomeAuthorPanel);
        /*--- 按键监听 ---*/
        welcomeRegisterButton.addActionListener(e -> {
            if (e.getSource() == welcomeRegisterButton) {
                jumpToRegister();
            }
        });
        welcomeLoginButton.addActionListener(e -> {
            if (e.getSource() == welcomeLoginButton) {
                jumpToLogin();
            }
        });
        // 显示
        welcomeFrame.setContentPane(welcomeBox);
        welcomeFrame.setVisible(true);
    }

    public void jumpToRegister() {
        new RegisterFrame();
        welcomeFrame.dispose();
    }
    public void jumpToLogin() {
        new LoginFrame();
        welcomeFrame.dispose();
    }
}