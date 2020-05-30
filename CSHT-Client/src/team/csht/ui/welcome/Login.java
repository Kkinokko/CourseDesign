package team.csht.ui.welcome;

import team.csht.entity.User;
import team.csht.socket.Client;
import team.csht.util.CommandTranser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import javax.servlet.http.HttpSession;

/** @author MnAs & Fe */
public class Login{
    public Login(){
    LoginFrame log = new LoginFrame();
    }
    //public static final String userName =LoginFrame.loginUsernameTextField.getText();
    public static final String userName ="username0";

}

class LoginFrame implements ActionListener {
    final int DEFAULT_WIDTH = 800;
    final int DEFAULT_HEIGHT = 600;

    JFrame loginFrame = new JFrame();
    public static JTextField loginUsernameTextField = new JTextField(20);
    public static JPasswordField loginPasswordField = new JPasswordField(20);
    private JButton loginButton = new JButton();

    public LoginFrame() {
        /*--- 界面效果显示 ---*/
        // 创建最外层的JFrame
        loginFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loginFrame.setTitle("学生闲置物品交易平台 - 登录");
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 背景图片
        ImageIcon loginBackgroundImageIcon = new ImageIcon("src\\res\\bg\\bg2.jpg");
        JLabel loginBackgroundLabel = new JLabel();
        loginBackgroundLabel.setIcon(loginBackgroundImageIcon);
        loginBackgroundLabel.setBounds(0, 0, loginBackgroundImageIcon.getIconWidth(), loginBackgroundImageIcon.getIconHeight());
        loginFrame.getLayeredPane().add(loginBackgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
        // 创建标题
        JPanel loginTitlePanel = new JPanel();
        loginTitlePanel.setOpaque(false);
        JLabel loginTitleLabel = new JLabel("学生闲置物品交易平台 - 登录");
        loginTitleLabel.setFont(new Font("黑体", Font.BOLD,22));
        loginTitlePanel.add(loginTitleLabel);
        // 创建用户名一栏
        JPanel loginUsernamePanel = new JPanel();
        loginUsernamePanel.setOpaque(false);
        JLabel loginUsernameLabel = new JLabel("用户名：");
        loginUsernameLabel.setFont(new Font("黑体", Font.PLAIN,16));
        loginUsernameTextField.setBorder(null);
        loginUsernameTextField.setBackground(null);
        loginUsernamePanel.add(loginUsernameLabel);
        loginUsernamePanel.add(loginUsernameTextField);
        // 创建密码一栏
        JPanel loginPasswordPanel = new JPanel();
        loginPasswordPanel.setOpaque(false);
        JLabel loginPasswordLabel = new JLabel("\u3000密码：");
        loginPasswordLabel.setFont(new Font("黑体", Font.PLAIN,16));
        loginPasswordField.setBorder(null);
        loginPasswordField.setBackground(null);
        loginPasswordPanel.add(loginPasswordLabel);
        loginPasswordPanel.add(loginPasswordField);
        // 登录按钮
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setOpaque(false);
        loginButton.setBorder(null);
        loginButton.setIcon(new ImageIcon("src\\res\\icon\\login.png"));
        loginButton.setPressedIcon(new ImageIcon("src\\res\\icon\\login2.png"));
        loginButton.setContentAreaFilled(false);
        loginButtonPanel.add(loginButton);
        // 跳转到注册界面
        JPanel loginJumpToRegisterPanel = new JPanel();
        loginJumpToRegisterPanel.setOpaque(false);
        JLabel loginJumpToRegisterLabel = new JLabel("还没有账号？点这里注册");
        loginJumpToRegisterLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        loginJumpToRegisterPanel.add(loginJumpToRegisterLabel);
        // 创建一个盒子把panel装进去
        Box loginBox = Box.createVerticalBox();
        loginBox.add(loginTitlePanel);
        loginBox.add(loginUsernamePanel);
        loginBox.add(loginPasswordPanel);
        loginBox.add(loginButtonPanel);
        loginBox.add(loginJumpToRegisterPanel);
        /*--- 按键监听 ---*/
        loginButton.addActionListener(this);
        loginJumpToRegisterLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                jumpToRegister();
            }
        });
        // 显示
        loginFrame.setContentPane(loginBox);
        loginFrame.setVisible(true);
    }

    public void jumpToRegister() {
        new RegisterFrame();
        loginUsernameTextField.setText("");
        loginPasswordField.setText("");
        loginFrame.dispose();
    }
    public void jumpToMain(String username) {
        //保留这个用户名设置为空的话，后面的upload、main拿到的用户名都为“ ”了
        //所以要不要把下面第一行注释掉？
        loginUsernameTextField.setText("");
        loginPasswordField.setText("");
        // TODO:跳转到MainFrame(需保留账号信息)
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = loginUsernameTextField.getText().trim();
            String password = new String(loginPasswordField.getText()).trim();
            if ("".equals(username) || username == null) {
                JOptionPane.showMessageDialog(null, "请输入账号！");
                return;
            }
            if ("".equals(password) || password == null) {
                JOptionPane.showMessageDialog(null, "请输入密码！");
                return;
            }
            User user = new User(username, password);
            CommandTranser message = new CommandTranser();
            message.setCommand("login");
            message.setData(user);
            message.setReceiver(username);
            message.setSender(username);
            Client client = new Client();
            client.sendData(message);
            message = client.getData();
            if (message != null) {
                if (message.isFlag()) {
                    JOptionPane.showMessageDialog(null, "登陆成功！");
                    jumpToMain(username);
                }
                else {
                    JOptionPane.showMessageDialog(null, "登录失败!");
                }
            }
        }
    }

}