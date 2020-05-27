package team.csht.ui.welcome;

import team.csht.entity.User;
import team.csht.socket.Client;
import team.csht.util.CommandTranser;

import java.awt.Color;
import java.awt.Font;
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

/** @author MnAs & Fe */
class RegisterFrame implements ActionListener {
    final int DEFAULT_WIDTH = 800;
    final int DEFAULT_HEIGHT = 600;

    JFrame registerFrame = new JFrame();
    JTextField registerUsernameTextField = new JTextField(20);
    JTextField registerNumberTextField = new JTextField(20);
    JPasswordField registerPasswordField = new JPasswordField(20);
    JPasswordField registerPasswordField2 = new JPasswordField(20);
    JButton registerButton = new JButton();

    public RegisterFrame() {
        // 创建最外层的JFrame
        registerFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        registerFrame.setTitle("学生闲置物品交易平台 - 注册");
        registerFrame.setResizable(false);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 引入背景
        ImageIcon registerBackgroundImageIcon = new ImageIcon("src\\res\\bg\\bg3.jpg");
        JLabel registerBackgroundLabel = new JLabel();
        registerBackgroundLabel.setIcon(registerBackgroundImageIcon);
        registerBackgroundLabel.setBounds(0, 0, registerBackgroundImageIcon.getIconWidth(), registerBackgroundImageIcon.getIconHeight());
        registerFrame.getLayeredPane().add(registerBackgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
        // 标题
        JPanel registerTitlePanel = new JPanel();
        registerTitlePanel.setOpaque(false);
        JLabel registerTitleLabel = new JLabel("学生闲置物品交易平台 - 注册");
        registerTitleLabel.setFont(new Font("黑体", Font.BOLD,22));
        registerTitlePanel.setBackground(null);
        registerTitlePanel.add(registerTitleLabel);
        // 创建用户信息输入框1：用户名
        JPanel registerUsernamePanel = new JPanel();
        registerUsernamePanel.setOpaque(false);
        JLabel registerUsernameLabel = new JLabel("\u3000\u3000\u3000用户名：");
        registerUsernameLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        registerUsernameTextField.setBorder(null);
        registerUsernameTextField.setBackground(Color.white);
        registerUsernamePanel.add(registerUsernameLabel);
        registerUsernamePanel.add(registerUsernameTextField);
        // 输入框2：确认学号
        JPanel registerNumberPanel = new JPanel();
        registerNumberPanel.setOpaque(false);
        JLabel registerNumberLabel = new JLabel("\u3000\u3000\u3000\u3000学号：");
        registerNumberLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        registerNumberTextField.setBorder(null);
        registerNumberTextField.setBackground(Color.white);
        registerNumberPanel.add(registerNumberLabel);
        registerNumberPanel.add(registerNumberTextField);
        // 输入框3：密码
        JPanel registerPasswordPanel = new JPanel();
        registerPasswordPanel.setOpaque(false);
        JLabel registerPasswordLabel = new JLabel("\u3000\u3000\u3000\u3000密码：");
        registerPasswordLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        registerPasswordField.setBorder(null);
        registerPasswordPanel.add(registerPasswordLabel);
        registerPasswordPanel.add(registerPasswordField);
        // 输入框4：重复输入密码
        JPanel registerPasswordPanel2 = new JPanel();
        registerPasswordPanel2.setOpaque(false);
        JLabel registerPasswordLabel2 = new JLabel("再次输入密码：");
        registerPasswordLabel2.setFont(new Font("黑体", Font.PLAIN, 16));
        registerPasswordField2.setBorder(null);
        registerPasswordField2.setBackground(Color.white);
        registerPasswordPanel2.add(registerPasswordLabel2);
        registerPasswordPanel2.add(registerPasswordField2);
        // 登录按钮
        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.setOpaque(false);
        registerButton.setBorder(null);
        registerButton.setIcon(new ImageIcon("src\\res\\icon\\register.png"));
        registerButton.setPressedIcon(new ImageIcon("src\\res\\icon\\register2.png"));
        registerButton.setContentAreaFilled(false);
        registerButtonPanel.add(registerButton);
        // 跳转到登陆界面
        JPanel registerJumpToLoginPanel = new JPanel();
        registerJumpToLoginPanel.setOpaque(false);
        JLabel registerJumpToLoginLabel = new JLabel("已经有账号？点这里登录");
        registerJumpToLoginLabel.setFont(new Font("黑体", Font.PLAIN, 16));
        registerJumpToLoginPanel.add(registerJumpToLoginLabel);
        // 创建一个竖直的盒子
        Box registerBox = Box.createVerticalBox();
        registerBox.setFont(new Font("黑体", Font.PLAIN,18));
        registerBox.add(registerTitlePanel);
        registerBox.add(registerUsernamePanel);
        registerBox.add(registerNumberPanel);
        registerBox.add(registerPasswordPanel);
        registerBox.add(registerPasswordPanel2);
        registerBox.add(registerButtonPanel);
        registerBox.add(registerJumpToLoginPanel);
        /*--- 按键监听 ---*/
        registerButton.addActionListener(this);
        registerJumpToLoginLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                jumpToLogin();
                registerFrame.dispose();
            }
        });
        // 显示
        registerFrame.setContentPane(registerBox);
        registerFrame.setVisible(true);
    }

    public void jumpToLogin() {
        new LoginFrame();
        registerUsernameTextField.setText("");
        registerNumberTextField.setText("");
        registerPasswordField.setText("");
        registerPasswordField2.setText("");
        registerFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = registerUsernameTextField.getText().trim();
            String password = registerPasswordField.getText().trim();
            String password2 = registerPasswordField2.getText().trim();
            if ("".equals(username) || username == null) {
                JOptionPane.showMessageDialog(null, "请输入账号！");
                return;
            }
            if ("".equals(password) || password == null) {
                JOptionPane.showMessageDialog(null, "请输入密码！");
                return;
            }
            if (password.equals(password2)) {
                User user = new User(username, password);
                CommandTranser message = new CommandTranser();
                message.setCommand("register");
                message.setData(user);
                message.setReceiver(username);
                message.setSender(username);
                Client client = new Client();
                client.sendData(message);
                message = client.getData();
                if (message != null) {
                    if (message.isFlag()) {
                        JOptionPane.showMessageDialog(null, "注册成功");
                        jumpToLogin();
                    }
                    else if ("usernameDuplicated".equals(message.getResult())) {
                        JOptionPane.showMessageDialog(null, "注册失败，请尝试更换用户名");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "注册失败，错误码11");
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "密码不一致！");
            }
        }
    }
}