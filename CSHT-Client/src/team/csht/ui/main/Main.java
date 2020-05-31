package team.csht.ui.main;

import team.csht.entity.Good;
import team.csht.socket.Client;
import team.csht.ui.im.*;
import team.csht.ui.welcome.Login;
import team.csht.util.CommandTranser;

import javax.swing.*;
import java.awt.*;

//

 class MainFrame1 extends JFrame{
    String username = "";
    Client client;
    Box right1 = Box.createVerticalBox();
    public  MainFrame1(String username ,Client client){
        this.username = username;this.client=client;
    }
    public void addGood1(Good g){
         // picture 待会请务必把图片地址改了！！！！
         JPanel c1= new JPanel();
         JLabel g1= new JLabel();
         ImageIcon g0 = new ImageIcon("src\\res\\icon\\login2.png");
         //之后把这里改了
         g.setIcon(g0);

         //之后把这里改了
         g0.setImage(g0.getImage().getScaledInstance(200, 120, Image.SCALE_DEFAULT ));
         g1.setIcon(g0);
         g1.setSize(200,120);
         c1.add(g1);
         c1.setOpaque(false);
         // button
         JButton g2 = new JButton("详细信息");
         g2.addActionListener(e -> {
             if(e.getSource()==g2){
                 new Single(g,username,client);
                 this.dispose();
             }
         });
         JButton g3 = new JButton("购买商品");
         g3.addActionListener(e -> {
             if(e.getSource()==g3){
                 g.setExistence(false);
                 g.setBuyer(Login.userName);
                 CommandTranser message = new CommandTranser();
                 message.setCommand("buyGood");
                 message.setData(g);
                 message.setSender(Login.userName);
                 message.setReceiver(Login.userName);//这里需要返回是否购买成功吗？
                 client.sendData(message);
                 message = client.getData();
                 if (message != null) {
                     if (message.isFlag()) {
                         new IM(username,client);
                     }
                     else {
                         JOptionPane.showMessageDialog(null, "商品购买失败!");
                     }
                 }
             }
         });
         JPanel c2 = new JPanel();
         c2.add(g2);
         c2.add(g3);
         c2.setOpaque(false);
         // right
         JLabel g4 = new JLabel("商品名称： "+g.getName());
         JLabel g5 = new JLabel("卖家： "+g.getMerchant());
         JLabel g7 = new JLabel("价格： "+g.getPrice());
         JPanel c3 =new JPanel();
         JPanel c4 = new JPanel();
         JPanel c5 = new JPanel();
         c3.add(g4);
         c4.add(g5);
         c5.add(g7);
         c3.setOpaque(false);
         c4.setOpaque(false);
         c5.setOpaque(false);
         // 布局
         Box f1= Box.createVerticalBox();
         f1.add(c1);
         Box f2 = Box.createVerticalBox();
         f2.add(c3);
         f2.add(c4);
         f2.add(c5);
         f2.add(c2);
         Box f3 = Box.createHorizontalBox();
         f3.add(f1);
         f3.add(f2);
         right1.add(f3);
     }
    public void repaint(){right1= Box.createVerticalBox();}
}

public class Main {
    Good[] receive;
    String username;
    Client client;
    public Main(String username,Client client){
        //刷新并把返回的商品数组列出来
        this.client = client;
        this.username=username;
        CommandTranser message0 = new CommandTranser();
        message0.setCommand("getGoodList");
        message0.setData("come on");
        message0.setSender(username);
        message0.setReceiver(username);
        client.sendData(message0);
        message0 = client.getData();
        if (message0!= null) {
            if (message0.isFlag()) {
                //JOptionPane.showMessageDialog(null, "商品提交成功！");
                receive = (Good[])message0.getData();
            }
            else {
                JOptionPane.showMessageDialog(null, "未获取商品列表，请按左边的“浏览商品”刷新");
            }
        }

        Good first = new Good(41,"卡片",1);
        first.setMerchant("username0");
        Good second = new Good(42,"抹布",10);
        Good third = new Good(43,"miku",1000);
        Good forth = new Good(44,"空",2);
        MainFrame1 main = new MainFrame1(username,client);

        main.setResizable(false);
        main.setSize(700, 500);
        main.setTitle("学生闲置物品交易平台主界面");
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //背景
        ImageIcon bgi=new ImageIcon("src\\res\\bg\\bgm.png");
        JLabel bg = new JLabel();
        bg.setIcon(bgi);
        bg.setBounds(0,0,bgi.getIconWidth(),bgi.getIconHeight());
        main.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
        //菜单栏
        Menu ll = new Menu();
        ll.a2.addActionListener(e -> {
            if(e.getSource()==ll.a2){
                new Upload(username,client);
                main.dispose();
            }
        });
        Box left = ll.getMenu();
        //搜索栏
        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField(25);
        JButton searchButton = new JButton("搜索");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        main.right1.add(searchPanel);

        //
        JScrollPane jsp= new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setViewportView(main.right1);
        //
        Box all = Box.createHorizontalBox();
        all.add(left);
        all.add(Box.createHorizontalStrut(80));
        all.add(jsp);

        searchButton.addActionListener(e -> {
            if(e.getSource()==searchButton){
                CommandTranser message = new CommandTranser();
                message.setCommand("searchGood");
                message.setData(searchField.getText().trim());
                message.setSender(username);
                message.setReceiver(username);
                client.sendData(message);
                message = client.getData();
                if (message != null) {
                    if (message.isFlag()) {
                        //JOptionPane.showMessageDialog(null, "商品提交成功！");
                        //法一
                        Good[] result =(Good[])message.getData();
                        main.right1.removeAll();
                        main.right1.repaint();
                        for(int i=0;i<result.length;i++){
                            main.addGood1(result[i]);
                        }
                        main.right1.revalidate();
                        //法二：all.remove(jsp),然后造另一个jsp
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "未搜索到此商品!");
                    }
                }
            }
        });

        main.addGood1(first);
        main.addGood1(second);
        main.addGood1(third);
        main.addGood1(forth);
        //填装商品列表
        for(int j =0;j<receive.length;j++){
            if(receive[j].isExistence()){
                main.addGood1(receive[j]);
            }
        }

        main.setContentPane(all);
        main.setVisible(true);
    }
}