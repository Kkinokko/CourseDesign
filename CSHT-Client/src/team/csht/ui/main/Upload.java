package team.csht.ui.main;
import team.csht.entity.Good;
import team.csht.socket.Client;
import team.csht.util.CommandTranser;
import team.csht.ui.welcome.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


public class Upload {
    public static void  main (String[] args){
    UploadFrame a = new UploadFrame();
    }
}

class UploadFrame extends JFrame
{
    private JButton updateEnter = new JButton("确定提交");
    private ImageIcon selectedPhoto;
    private JTextField updateName = new JTextField(15);
    private  JTextField updatePrice = new JTextField(15);

    public UploadFrame()
    {
        //初始化
        JFrame updateFrame = new JFrame();
        updateFrame.setResizable(false);
        updateFrame.setSize(700, 500);
        updateFrame.setTitle("学生闲置物品交易平台主界面");
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //背景
        ImageIcon bgi=new ImageIcon("src\\res\\bg\\bgm.png");
        JLabel bg = new JLabel();
        bg.setIcon(bgi);
        bg.setBounds(0,0,bgi.getIconWidth(),bgi.getIconHeight());
        updateFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
        //菜单栏
        Menu ll = new Menu();
        ll.a1.addActionListener(e -> {
            if(e.getSource()==ll.a1){
                new Main();
                updateFrame.dispose();
            }
        });
        Box left = ll.getMenu();
        //
        Box leftUp = Box.createHorizontalBox();
        JButton picture = new JButton();
        //图片选择器
        ImageIcon a = new ImageIcon("src\\res\\icon\\login2.png");
        a.setImage(a.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT ));
        picture.setIcon(a);
        picture.setSize(300,300);
        leftUp.add(Box.createHorizontalStrut(50));
        leftUp.add(picture);
        picture.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "jpg,png","JPG","JPEG","PNG"));
        int i = fileChooser.showOpenDialog(getContentPane());
        if(i==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            selectedPhoto = new ImageIcon(selectedFile.getAbsolutePath());
            selectedPhoto.setImage(
                    selectedPhoto.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT ));
            picture.setIcon(selectedPhoto);

        }
    });
        //提交界面主体
        JPanel updateNamePanel = new JPanel();
        updateNamePanel.setOpaque(false);
        JLabel updateNameLabel = new JLabel("商品名");
        updateNamePanel.add(updateNameLabel);
        updateNamePanel.add(updateName);
        JPanel updatePricePanel = new JPanel();
        updatePricePanel.setOpaque(false);
        JLabel updatePriceLabel = new JLabel("\u3000售价");
        updatePricePanel.add(updatePriceLabel);
        //价格栏只能输入数字
        updatePrice.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //关键，屏蔽掉非法输入
                }
            }
        });
        updatePricePanel.add(updatePrice);
        JPanel enterPanel = new JPanel();
        enterPanel.setOpaque(false);

        enterPanel.add(updateEnter);
        //整合右方
        Box right =  Box.createVerticalBox();
        right.add(leftUp);
        right.add(Box.createVerticalStrut(30));
        right.add(updateNamePanel);
        right.add(updatePricePanel);
        right.add(enterPanel);
        right.setOpaque(false);
        //
        JScrollPane jsp= new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setViewportView(right);
        //
        Box all = Box.createHorizontalBox();
        all.add(left);
        all.add(Box.createHorizontalStrut(50));
        all.add(jsp);
        //监听键
        //updateEnter.addActionListener(this);
        //
        updateFrame.setContentPane(all);
        updateFrame.setVisible(true);

    }

    public void updateGood(){
        String name = updateName.getText().trim();
        String price0 = updatePrice.getText();
        if (name==null||"".equals(name)){
            JOptionPane.showMessageDialog(null, "请输入商品名称！");
            return;}
        if(price0==null||"".equals(price0)){
            JOptionPane.showMessageDialog(null, "请输入商品价格！");
            return;}
        if(selectedPhoto==null){
            JOptionPane.showMessageDialog(null, "请添加图片！");
            return;}
        //字符串转数字
        double number=0;
        int i=0;
        String num="";
        char k[]=price0.toCharArray();
        while(k.length>i){
            num=num+(k[i++]-'0');
        }
        number=Integer.parseInt(num);
        double price = number*1.0;
        Good g = new Good(name,price);
        g.setIcon(selectedPhoto);

        CommandTranser message = new CommandTranser();
        message.setCommand("addGood");
        message.setData(g);
        message.setSender(Login.userName);
        message.setReceiver(Login.userName);
        Client client = new Client();
        client.sendData(message);
        message = client.getData();

        if (message != null) {
            if (message.isFlag()) {
                JOptionPane.showMessageDialog(null, "商品提交成功！");
            }
            else {
                JOptionPane.showMessageDialog(null, "商品提交失败!");
            }
        }

    }
}
