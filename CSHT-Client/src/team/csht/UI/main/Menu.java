package team.csht.UI.main;
import team.csht.UI.welcome.Welcome;

import javax.swing.*;
import java.awt.*;
public class Menu {
    public Box left = Box.createVerticalBox();
    public JButton a1 = new JButton("浏览商品");
    public JButton a2 = new JButton("上传商品");
    public  JButton a3 = new JButton("商品管理");
    public JButton a4 = new JButton("查看订单");
    public JButton a5 = new JButton("个人中心");
    public   Menu(){
        //1
        JPanel l1=new JPanel();
        a1.setForeground(Color.white);
        a1.setContentAreaFilled(false);
        a1.setBorder(null);
        a1.setFocusPainted(false);
        l1.add(a1);
        l1.setOpaque(false);
        //2
        JPanel l2=new JPanel();
        a2.setForeground(Color.white);
        a2.setContentAreaFilled(false);
        a2.setBorder(null);
        a2.setFocusPainted(false);
        l2.add(a2);
        l2.setOpaque(false);
        //3
        JPanel l3=new JPanel();
        a3.setForeground(Color.white);
        a3.setContentAreaFilled(false);
        a3.setBorder(null);
        a3.setFocusPainted(false);
        l3.add(a3);
        l3.setOpaque(false);
        //4
        JPanel l4=new JPanel();
        a4.setForeground(Color.white);
        a4.setContentAreaFilled(false);
        a4.setBorder(null);
        a4.setFocusPainted(false);
        l4.add(a4);
        l4.setOpaque(false);
        //5
        JPanel l5=new JPanel();
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

    }

    public Box getMenu(){return left;}

}
