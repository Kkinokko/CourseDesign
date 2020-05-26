package team.csht.UI.main;

import team.csht.entity.Good;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args)
    {
        MainFrame main = new MainFrame();
        Good first = new Good("001","卡片",1);
        Good second = new Good("002","抹布",10);
        Good third = new Good("003","miku",1000);
        Good forth = new Good("004","空",2);
        main.addGood1(first);
        main.addGood1(second);
        main.addGood1(third);
        main.addGood1(forth);
    }
}

class MainFrame{
    Box right1 = Box.createVerticalBox();
    JPanel right = new JPanel();
    public  MainFrame(){
        //初始化
        JFrame mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setSize(700, 500);
        mainFrame.setTitle("学生闲置物品交易平台主界面");
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //背景
        ImageIcon bgi=new ImageIcon("src\\res\\icon\\bgm.png");
        JLabel bg = new JLabel();
        bg.setIcon(bgi);
        bg.setBounds(0,0,bgi.getIconWidth(),bgi.getIconHeight());
        mainFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
        //菜单栏
        Box left = Box.createVerticalBox();
        JPanel l1=new JPanel();
        JButton a1 = new JButton("浏览商品");
        a1.setForeground(Color.white);
        a1.setContentAreaFilled(false);
        a1.setBorder(null);
        a1.setFocusPainted(false);
        l1.add(a1);
        l1.setOpaque(false);
        JPanel l2=new JPanel();
        JButton a2 = new JButton("上传商品");
        a2.setForeground(Color.white);
        a2.setContentAreaFilled(false);
        a2.setBorder(null);
        a2.setFocusPainted(false);
        l2.add(a2);
        l2.setOpaque(false);
        JPanel l3=new JPanel();
        JButton a3 = new JButton("商品管理");
        a3.setForeground(Color.white);
        a3.setContentAreaFilled(false);
        a3.setBorder(null);
        a3.setFocusPainted(false);
        l3.add(a3);
        l3.setOpaque(false);
        JPanel l4=new JPanel();
        JButton a4 = new JButton("查看订单");
        a4.setForeground(Color.white);
        a4.setContentAreaFilled(false);
        a4.setBorder(null);
        a4.setFocusPainted(false);
        l4.add(a4);
        l4.setOpaque(false);
        JPanel l5=new JPanel();
        JButton a5 = new JButton("个人中心");
        a5.setForeground(Color.white);
        a5.setContentAreaFilled(false);
        a5.setBorder(null);
        a5.setFocusPainted(false);
        l5.add(a5);
        l5.setOpaque(false);

        left.add(l1);
        left.add(l2);
        left.add(l3);
        left.add(l4);
        left.add(l5);
        left.add(Box.createVerticalStrut(300));
        //
        JScrollPane jsp= new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setViewportView(right1);
        Box all = Box.createHorizontalBox();
        all.add(left);
        all.add(Box.createHorizontalStrut(80));
        all.add(jsp);

        mainFrame.setContentPane(all);
        mainFrame.setVisible(true);

    }
    public void addGood1(Good g){
        // picture 待会请务必把图片地址改了！！！！
        JPanel c1= new JPanel();
        JLabel g1= new JLabel();
        ImageIcon g0 = new ImageIcon("src\\res\\icon\\login2.png");
        g0.setImage(g0.getImage().getScaledInstance(200, 120, Image.SCALE_DEFAULT ));
        g1.setIcon(g0);
        g1.setSize(200,120);
        c1.add(g1);
        c1.setOpaque(false);
        // button
        JButton g2 = new JButton("详细信息");
        JButton g3 = new JButton("购买商品");
        JPanel c2 = new JPanel();
        c2.add(g2);
        c2.add(g3);
        c2.setOpaque(false);
        // right
        JLabel g4 = new JLabel("商品名称： "+g.getName());
        JLabel g5 = new JLabel("商家： "+g.getMerchant());
        JButton g6 = new JButton("联系");
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
