package Student2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.*;

class Xsxx extends AbstractTableModel
{
    Vector ziduan,jilu;
    PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;

    @Override
    public int getRowCount()
    {
        return this.jilu.size();
    }
    @Override
    public int getColumnCount()
    {
        return this.ziduan.size();
    }
    @Override
    public Object getValueAt(int hang, int lie)
    {
        return ((Vector)this.jilu.get(hang)).get(lie);
    }

    public Xsxx()
    {
        this.sqlyj("select * from xuesheng ");
    }
    public Xsxx(String ss)
    {
        this.sqlyj(ss);
    }
    @Override
    public String getColumnName(int e)
    {
        return (String)this.ziduan.get(e);
    }
    public void sqlyj(String sql)
    {
        ziduan=new Vector();
        ziduan.add("学号");
        ziduan.add("姓名");
        ziduan.add("性别");
        ziduan.add("年龄");
        ziduan.add("籍贯");
        ziduan.add("所在院系");
        jilu=new Vector();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/student1?serverTimezone=UTC&useSSL=false","root","521986");
            ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next())
            {
                Vector hang=new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getInt(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                jilu.add(hang);

            }
        } catch (Exception e){}
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

            } catch (Exception e){}
        }
    }
}
