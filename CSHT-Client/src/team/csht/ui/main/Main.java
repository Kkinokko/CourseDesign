package team.csht.ui.main;

import team.csht.entity.Good;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main(){
        new MainFrame("username0");
    }
    public static void main(String[] args)
    {

        new MainFrame("username0");

    }
}

 class MainFrame1 extends JFrame{
    String username = "";
    Box right1 = Box.createVerticalBox();
     JFrame mainFrame = new JFrame();
    //JPanel right = new JPanel();
    public  MainFrame1(String username){
        this.username = username;
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
                 new Single(g);
                 this.dispose();
             }
         });
         JButton g3 = new JButton("购买商品");
         JPanel c2 = new JPanel();
         c2.add(g2);
         c2.add(g3);
         c2.setOpaque(false);
         // right
         JLabel g4 = new JLabel("商品名称： "+g.getName());
         JLabel g5 = new JLabel("商家： "+g.getMerchant());
         JButton g6 = new JButton("联系");
         //记得以后给这个按钮一个交代。。。
         JLabel g7 = new JLabel("价格： "+g.getPrice());
         JPanel c3 =new JPanel();
         JPanel c4 = new JPanel();
         JPanel c5 = new JPanel();
         c3.add(g4);
         c4.add(g5);
         c4.add(g6);
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
         right1.add(Box.createVerticalStrut(70));
     }
}

class MainFrame {
    public MainFrame(String username){

        Good first = new Good("001","卡片",1);
        Good second = new Good("002","抹布",10);
        Good third = new Good("003","miku",1000);
        Good forth = new Good("004","空",2);
        MainFrame1 main = new MainFrame1("username0");

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
                new UploadFrame();
                main.dispose();
            }
        });
        Box left = ll.getMenu();
        //
        JScrollPane jsp= new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setViewportView(main.right1);
        Box all = Box.createHorizontalBox();
        all.add(left);
        all.add(Box.createHorizontalStrut(80));
        all.add(jsp);

        main.addGood1(first);
        main.addGood1(second);
        main.addGood1(third);
        main.addGood1(forth);
        main.setContentPane(all);
        main.setVisible(true);
    }
}