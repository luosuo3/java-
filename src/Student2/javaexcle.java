package Student2;



import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author 王峥
 */
class Export {
    Export () throws IOException, WriteException {
        //1. 导出Excel的路径
        String filePath = "D:/excel/test.xls";
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建Excel表的"学生"区域的数据
        WritableSheet sheet = wwb.createSheet("学生", 0);
        try {
            //2. 连接数据库的几行代码
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String url = "jdbc:mysql://localhost:3306/student1?serverTimezone=UTC";
            String sql = "select * from xuesheng";
            con = DriverManager.getConnection(url, "root", "521986");
            ps = con.prepareStatement(sql);// SQL预处理
            rs = ps.executeQuery();
            //ResultSet是数据库中的数据，将其转换为List类型
            List<Student> list = new ArrayList<Student>();
            while (rs.next()) {
                Student stu = new Student();
                stu.setXuehao(rs.getString("xuehao"));
                stu.setXingming(rs.getString("xingming"));
                stu.setXingbie(rs.getString("xingbie"));
                stu.setNianling(rs.getInt("nianling"));
                stu.setJiguan(rs.getString("jiguan"));
                stu.setYxmc(rs.getString("yxmc"));
                list.add(stu);
            }
            ps.close();
            con.close();
            for (int i = 0; i < list.size(); i++) {
                //Number对应数据库的int类型数据
                //Label对应数据库String类型数据
                sheet.addCell(new Label(0, i, list.get(i).getXuehao()));
                sheet.addCell(new Label(1, i, list.get(i).getXingming()));
                sheet.addCell(new Label(2, i, list.get(i).getXingbie()));
                sheet.addCell(new jxl.write.Number(3, i, list.get(i).getNianling()));
                sheet.addCell(new Label(4, i, list.get(i).getJiguan()));
                sheet.addCell(new Label(5, i, list.get(i).getYxmc()));


            }
            wwb.write();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            wwb.close();
        }
    }

   /* public static void main(String[] args) throws RowsExceededException, WriteException, IOException {

        Export export = new Export();


    }*/

}
