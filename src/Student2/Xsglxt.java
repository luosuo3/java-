package Student2;
import jxl.write.WriteException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Xsglxt extends JFrame implements ActionListener
{
    Vector ziduan,jilu;
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;
    JPanel mb1,mb2;
    JLabel bq1;
    JTextField wbk1;
    JButton an1,an2,an3,an4,an5,an6,an7,an8;
    JTable bg1;
    JScrollPane gd1;
    Xsxx xsxx2;

   /* public static void main(String[] args)
    {
        Xsglxt xs=new Xsglxt();
    }*/
    public Xsglxt()
    {
        mb1=new JPanel();
        bq1=new JLabel("请输入姓名");
        wbk1=new JTextField(10);
        an1=new JButton("查询");
        an1.addActionListener(this);
        an1.setActionCommand("chaxun");
        mb1.add(bq1); mb1.add(wbk1); mb1.add(an1);

        mb2=new JPanel();
        an2=new JButton("添加");
        an2.addActionListener(this);
        an2.setActionCommand("tianjia");
        an3=new JButton("修改");
        an3.addActionListener(this);
        an3.setActionCommand("xiugai");
        an4=new JButton("删除");
        an4.addActionListener(this);
        an4.setActionCommand("shanchu");
        an5 = new JButton("年龄排序");
        an5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from xuesheng order by nianling ";
                Xsxx xsxx = new Xsxx(sql);
                bg1.setModel(xsxx);

            }
        });
        an7 = new JButton("初始化");
        an7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from xuesheng   ";
                Xsxx xsxx = new Xsxx(sql);
                bg1.setModel(xsxx);
            }
        });
        an6 = new JButton("导出为excle");
        an6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Export export = new Export();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (WriteException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "表格导出成功，请查看D:\\excel目录文件！");
            }
        });

        mb2.add(an2);
        mb2.add(an3);
        mb2.add(an4);
        mb2.add(an5);
        mb2.add(an6);
        mb2.add(an7);

        xsxx2=new Xsxx();
        bg1=new JTable(xsxx2);
        gd1=new JScrollPane(bg1);

        this.add(gd1);
        this.add(mb1,"North");
        this.add(mb2,"South");

        this.setTitle("海滨学院学生管理系统");
        this.setSize(500,400);
        this.setLocation(201,181);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("chaxun"))
        {

            String xingming=this.wbk1.getText().trim();
            String sql="select * from xuesheng where xingming='"+xingming+"'";
            xsxx2=new Xsxx(sql);
            bg1.setModel(xsxx2);
        }
        else if(e.getActionCommand().equals("tianjia"))
        {
            Tianjia tj=new Tianjia(this,"添加学生信息",true);
            xsxx2=new Xsxx();
            bg1.setModel(xsxx2);

        }
        else if(e.getActionCommand().equals("xiugai"))
        {
            int ii=this.bg1.getSelectedRow();
            if(ii==-1)
            {
                JOptionPane.showMessageDialog(this,"请选中要修改的行");
                return;
            }
            new Xiugai(this,"修改学生信息",true,xsxx2,ii);

            xsxx2=new Xsxx();
            bg1.setModel(xsxx2);
        }
        else if(e.getActionCommand().equals("shanchu"))
        {
            int ii=this.bg1.getSelectedRow();
            if(ii==-1)
            {
                JOptionPane.showMessageDialog(this,"请选中要删除的行");
                return;
            }
            String st=(String)xsxx2.getValueAt(ii,0);
            PreparedStatement ps=null;
            Connection ct=null;
            ResultSet rs=null;
            Statement sm=null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/student1?serverTimezone=UTC&useSSL=false","root","521986");
                ps=ct.prepareStatement("delete from xuesheng where xuehao=?");
                ps.setString(1,st);
                ps.executeUpdate();
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
            xsxx2=new Xsxx();
            bg1.setModel(xsxx2);
        }
    }
}
