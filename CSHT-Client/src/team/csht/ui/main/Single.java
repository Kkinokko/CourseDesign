package team.csht.ui.main;

import team.csht.entity.Comment;
import team.csht.entity.Good;
import team.csht.socket.Client;
import team.csht.ui.im.IM;
import team.csht.ui.welcome.Login;
import team.csht.util.CommandTranser;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static team.csht.ui.welcome.Login.userName;
class Single0 extends JFrame{
    String username = "";
    Box right0 = Box.createVerticalBox();
    JFrame mainFrame = new JFrame();
    public  Single0(String username){
        this.username = username;
    }
    public void addComment(Comment comment){
        String use = comment.getUsername();
        String content = comment.getContent();
        Timestamp time = comment.getTimestamp();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(time);

        //
        JPanel c1= new JPanel();
        JLabel g0 = new JLabel(now);
        c1.add(g0);
        JPanel c2 = new JPanel();
        JLabel g1= new JLabel(use);
        JLabel g2 = new JLabel(content);
        c2.add(g1);
        c2.add(g2);
        c1.setOpaque(false);
        c2.setOpaque(false);
        Box com = Box.createVerticalBox();
        com.add(c1);
        com.add(c2);
        right0.add(com);
    }
}


public class Single {
    private Label loginUsernameTextField;
        String username;
        //TODO:获取一个评论数组
        Comment[] receive ;
        Good g;
        public Single(Good g,String username) {
            this.g = g;
            CommandTranser message0 = new CommandTranser();
            message0.setCommand("getCommentList");
            message0.setData(g);
            message0.setSender(username);
            message0.setReceiver(username);
            Client client0 = new Client();
            client0.sendData(message0);
            message0 = client0.getData();
            if (message0!= null) {
                if (message0.isFlag()) {
                    //JOptionPane.showMessageDialog(null, "商品提交成功！");
                    receive = (Comment[]) message0.getData();
                }
                else {
                    JOptionPane.showMessageDialog(null, "未获取商品列表，请按左边的“浏览商品”刷新");
                }
            }

            //初始化
            this.username=username;
            Single0 singleFrame = new Single0(username);
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
                    new UploadFrame(username);
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
            JLabel updateNameLabel = new JLabel("商品名: "+g.getName());
            updateNamePanel.add(updateNameLabel);

            JPanel updatePricePanel = new JPanel();
            updatePricePanel.setOpaque(false);
            JLabel updatePriceLabel = new JLabel("\u3000售价: "+g.getPrice());
            updatePricePanel.add(updatePriceLabel);

            JPanel singleMerchantPanel = new JPanel();
            singleMerchantPanel.setOpaque(false);
            JLabel singleMerchantLabel = new JLabel("\u3000商家: "+g.getMerchant());
            JButton singleMerchantButton = new JButton("联系");

            JPanel reloadPanel = new JPanel();
            reloadPanel.setOpaque(false);
            JButton reload = new JButton("下架此商品");
            reload.addActionListener(e -> {
                if(e.getSource()==reload){
                    g.setExistence(false);

                    CommandTranser message = new CommandTranser();
                    message.setCommand("deleteGood");
                    message.setData(g);
                    message.setSender(username);
                    message.setReceiver(username);
                    Client client = new Client();
                    client.sendData(message);
                    message = client.getData();

                    if (message != null) {
                        if (message.isFlag()) {
                            JOptionPane.showMessageDialog(null, "商品下架成功！");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "商品下架失败!");
                        }
                    }
                }
            });
            reloadPanel.add(reload);

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
            if(username.equals(g.getMerchant())){
            right.add(reloadPanel);
            }
            right.setOpaque(false);
            //评论区
           for (int i=0;i<receive.length;i++)
           { singleFrame.addComment(receive[i]); }
           JPanel speakerPanel = new JPanel();
           speakerPanel.setOpaque(false);
           JTextField speakerField = new JTextField(30);
           JButton speakerButton = new JButton("发送评论");
           speakerPanel.add(speakerField);
           speakerPanel.add(speakerButton);
            right.add(singleFrame.right0);
            right.add(speakerPanel);
            //
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

            //
            singleFrame.setContentPane(all);
            singleFrame.setVisible(true);

            speakerButton.addActionListener(e -> {
                if(e.getSource()==speakerButton){

                    if(speakerField.getText().equals("")||speakerField.getText()==null)
                    { JOptionPane.showMessageDialog(null, "请输入评论！");}
                    else{
                        String sendContent = speakerField.getText();
                        Comment speak = new Comment(username,sendContent);
                        speak.setGoodId(g.getId());

                        CommandTranser message2 = new CommandTranser();
                        message2.setCommand("addComment");
                        message2.setData(speak);
                        message2.setSender(username);
                        message2.setReceiver(username);
                        Client client2 = new Client();
                        client2.sendData(message2);
                        message2 = client2.getData();
                        if (message2!= null) {
                            if (message2.isFlag()) {
                                JOptionPane.showMessageDialog(null, "评论发布成功！");
                                //刷新界面
                                singleFrame.right0.removeAll();
                                singleFrame.right0.repaint();
                                CommandTranser message3 = new CommandTranser();
                                message3.setCommand("getCommentList");
                                message3.setData(g);
                                message3.setSender(username);
                                message3.setReceiver(username);
                                Client client3 = new Client();
                                client0.sendData(message3);
                                message3 = client3.getData();
                                if (message3!= null) {
                                    if (message3.isFlag()) {
                                        //JOptionPane.showMessageDialog(null, "商品提交成功！");
                                        receive = (Comment[]) message3.getData();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "未获取评论列表，请刷新");
                                    }
                                }
                                for (int i=0;i<receive.length;i++)
                                { singleFrame.addComment(receive[i]); }
                                singleFrame.right0.revalidate();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "评论发布失败");
                            }

                        }

                    }
                }
            });
        }
    }


