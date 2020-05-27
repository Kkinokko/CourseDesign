package team.csht.ui.main;

import team.csht.entity.Good;

import javax.swing.*;
import java.awt.*;

public class Single {

        public Single(Good g) {
            //初始化
            JFrame singleFrame = new JFrame();
            singleFrame.setResizable(false);
            singleFrame.setSize(700, 500);
            singleFrame.setTitle("物品详情");
            singleFrame.setLocationRelativeTo(null);
            singleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //背景
            ImageIcon bgi = new ImageIcon("src\\res\\bg\\bgm.png");
            JLabel bg = new JLabel();
            bg.setIcon(bgi);
            bg.setBounds(0, 0, bgi.getIconWidth(), bgi.getIconHeight());
            singleFrame.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
            //菜单栏
            Menu ll = new Menu();
            ll.a1.addActionListener(e -> {
                if (e.getSource() == ll.a1) {
                    new Main();
                    singleFrame.dispose();
                }
            });
            ll.a2.addActionListener(e -> {
                if (e.getSource() == ll.a2) {
                    new UploadFrame();
                    singleFrame.dispose();
                }
            });
            Box left = ll.getMenu();
            //
            Box leftUp = Box.createHorizontalBox();
            JLabel picture = new JLabel();
            //图片展示
            /*ImageIcon a = (ImageIcon) (g.getIcon());*/
            ImageIcon a = new ImageIcon("src\\res\\icon\\login2.png");
            a.setImage(a.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            picture.setSize(300, 300);
            picture.setIcon(a);
            leftUp.add(Box.createHorizontalStrut(50));
            leftUp.add(picture);
            //下方界面主体
            JPanel updateNamePanel = new JPanel();
            updateNamePanel.setOpaque(false);
            JLabel updateNameLabel = new JLabel("商品名:"+g.getName());
            updateNamePanel.add(updateNameLabel);

            JPanel updatePricePanel = new JPanel();
            updatePricePanel.setOpaque(false);
            JLabel updatePriceLabel = new JLabel("\u3000售价"+g.getPrice());
            updatePricePanel.add(updatePriceLabel);

            JPanel singleMerchantPanel = new JPanel();
            singleMerchantPanel.setOpaque(false);
            JLabel singleMerchantLabel = new JLabel("\u3000商家");
            JButton singleMerchantButton = new JButton("联系");
            //记得给按钮一个交代.....
            singleMerchantPanel.add(singleMerchantLabel);
            singleMerchantPanel.add(singleMerchantButton);
            //整合右方
            Box right = Box.createVerticalBox();
            right.add(leftUp);
            right.add(Box.createVerticalStrut(30));
            right.add(updateNamePanel);
            right.add(updatePricePanel);
            right.add(singleMerchantPanel);
            right.setOpaque(false);
            /*


            这里加评论区
            另写一个类也挺好

            评论区Panel.add（评论区）;
            right.add(评论区Panel);


            */
            JScrollPane jsp = new JScrollPane();
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
            singleFrame.setContentPane(all);
            singleFrame.setVisible(true);
        }
    }

