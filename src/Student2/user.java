package Student2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class user extends JFrame{
    Boolean flag = false;
    JPanel mb1, mb2, mb3,mb4;
    JButton an1, an2,an3;
    JLabel bq1, bq2;
    JTextField wbk;
    JPasswordField mmk;


    public static void main(String[] args) {
        user u = new user();

        if (u.flag) {
            Xsglxt xsglxt = new Xsglxt();
        }

    }

    public user() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        mb1 = new JPanel();
        mb2 = new JPanel();
        mb3 = new JPanel();
        bq1 = new JLabel("用户名");
        bq2 = new JLabel("密   码");
        an1 = new JButton("登录");
        an1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wbk.getText().trim().length() == 0 || new String(mmk.getPassword()).trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "用户名或密码不允许为空");
                    return;
                }
                int i = 1;
                PreparedStatement ps=null;
                Connection ct=null;
                ResultSet rs=null;
                Statement sm=null;
                String user = null;
                String pass=null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    ct= DriverManager.getConnection("jdbc:mysql://localhost:3306/student1?serverTimezone=UTC&useSSL=false","root","521986");
                    String ss=("select  * from admin ");
                    ps=ct.prepareStatement(ss);
                     rs = ps.executeQuery();
                    while (rs.next()) {
                         user = rs.getString("user");
                         pass = rs.getString("password");
                        if (wbk.getText().trim().equals(user) && new String(mmk.getPassword()).trim().equals(pass)) {
                            JOptionPane.showMessageDialog(null, "登录成功");
                            i--;
                            Xsglxt xsglxt = new Xsglxt();
                            dispose();
                            break;

                        }
                    }
                         if (!rs.next()&&i==1){
                        JOptionPane.showMessageDialog(null, "用户名或密码错误");
                         }


                } catch (Exception e2){}

            }
        });
        an2 = new JButton("注册");
        an2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wbk.getText().trim().length() == 0 || new String(mmk.getPassword()).trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "用户名或密码不允许为空");
                    return;
                }
                PreparedStatement ps=null;
                Connection ct=null;
                ResultSet rs=null;
                Statement sm=null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    ct= DriverManager.getConnection("jdbc:mysql://localhost:3306/student1?serverTimezone=UTC&useSSL=false","root","521986");
                    String ss=("insert into admin values(?,?)");
                    ps=ct.prepareStatement(ss);
                    ps.setString(1,wbk.getText());
                    ps.setString(2,mmk.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "注冊成功请重新登录！");
                } catch (Exception e2){}
                finally
                {
                    try {
                        if(rs!=null)
                        {
                            rs.close();
                        }
                        if(ps!=null)
                        {
                            ps.close();
                        }
                        if(ct!=null)
                        {
                            ct.close();
                        }

                    } catch (Exception e3){}
                }
            }
        });
        an3 = new JButton("重置");
        an3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wbk.setText("");
                mmk.setText("");
            }
        });

        wbk = new JTextField(15);
        mmk = new JPasswordField(15);
        this.setLayout(new GridLayout(3, 1));
        mb1.add(bq1);
        mb1.add(wbk);
        mb2.add(bq2);
        mb2.add(mmk);
        mb3.add(an1);
        mb3.add(an2);
        mb3.add(an3);
        this.add(mb1);
        this.add(mb2);
        this.add(mb3);
        this.setTitle("用户登录");
        this.setSize(300, 200);
        this.setLocation(300, 280);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);


    }

    }


