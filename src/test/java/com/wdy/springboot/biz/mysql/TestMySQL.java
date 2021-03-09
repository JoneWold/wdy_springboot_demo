package com.wdy.springboot.biz.mysql;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;

/**
 * @author wgch
 * @date 2021/3/3
 */
public class TestMySQL {

    public static void main(String[] args) throws Exception {
        // 驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.17.15:3306/tongzhan2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        // 加载驱动程序
        Class.forName(driver);
        // 连续数据库
        Connection conn = DriverManager.getConnection(url, user, password);
        if (!conn.isClosed())
            System.out.println("Succeeded connecting to the Database!");
        // statement用来执行SQL语句
        Statement statement = conn.createStatement();
        for (String table : getDictTables()) {
            // 要执行的SQL语句
            String sql = "select * from " + table;
            ResultSet rs = statement.executeQuery(sql);
            //获取键名
            ResultSetMetaData md = rs.getMetaData();
            //获取行的数量
            int columnCount = md.getColumnCount();
            int id = 7027;
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                // 组合SQL插入语句
                StringBuilder sb = new StringBuilder("INSERT INTO  `code_value` ");
                StringBuilder sub1 = new StringBuilder();
                StringBuilder sub2 = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    //获取键名及值
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                    sub1.append(",`").append(md.getColumnName(i)).append("`");
                    if (Objects.isNull(rs.getObject(i))) {
                        sub2.append(",").append("NULL");
                    } else {
                        sub2.append(",'").append(rs.getObject(i)).append("'");
                    }
                }
                //INSERT INTO `tongzhan2`.`code_value`(`id`, `code_type`, `meeting_code`, `CODE`, `CODE_NAME`, `CODE_ABR1`, `CODE_ABR2`, `CODE_LEVEL`, `CODE_LEAF`, `SUP_CODE`, `ININO`, `CODE_ASSIST`, `INVALID`, `CODE_ANAME`, `CODE_BSPELLING`, `CODE_SPELLING`, `START_DATE`, `STOP_DATE`, `IS_USE`, `IS_MODIFY`, `REPORT_CODE`, `CODE_SOURCE`, `REPORT_LEVEL`)
                //VALUES (1, 'ga21412', NULL, '00', '无宗教信仰', NULL, '00', NULL, '1', NULL, 1, NULL, '1', '无宗教信仰', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
                sb.append("(`id`,`code_type`").append(sub1).append(") VALUES ('").append(id).append("','").append(table).append("'").append(sub2).append(");");
                System.out.println(sb);
                // 写入文件中
                String resource = TestMySQL.class.getResource("/").getPath();
                File file = FileUtil.newFile(resource + "tz_xZQH.txt");
                FileWriter writer = new FileWriter(file, true);
                writer.write(sb.toString() + "\n");
                writer.close();
                id++;
            }
        }
    }


    public static List<String> getDictTables() {
        return new ArrayList<String>() {{
            add("b01");
//            add("zb01");
        }};
//        return new ArrayList<String>() {{
//            add("ga21412");
//            add("gb3304");
//            add("gb4754");
//            add("gb4761");
//            add("gb4762");
//            add("gb6864");
//            add("gb8561");
//            add("gb22611");
//            add("gb22612");
//            add("gb22613");
//            add("gb48801");
//            add("kz01");
//            add("kz02");
//            add("kz03");
//            add("kz04");
//            add("kz05");
//            add("kz06");
//            add("kz07");
//            add("kz08");
//            add("kz09");
//            add("kz10");
//            add("kz11");
//            add("kz12");
//            add("kz13");
//            add("kz14");
//            add("kz15");
//            add("kz16");
//            add("kz17");
//            add("kz18");
//            add("kz19");
//            add("kz20");
//            add("kz21");
//            add("kz22");
//            add("kz23");
//            add("kz24");
//            add("kz25");
//            add("kz26");
//            add("kz27");
//            add("kz28");
//            add("kz37");
//            add("kz41");
//            add("kz42");
//            add("kz43");
//            add("kz44");
//            add("kz45");
//            add("kz47");
//            add("kz48");
//            add("kz49");
//            add("kz50");
//            add("tb01");
//            add("tb02");
//            add("tb04");
//            add("tb05");
//            add("tb09");
//            add("tb09a");
//            add("tb18");
//            add("tb21");
//            add("tb30");
//            add("tb31");
//            add("tb31_1");
//            add("tb31_2");
//            add("tb32");
//            add("tb34");
//            add("tb35");
//            add("tb36");
//            add("tb38");
//            add("tb39");
//            add("tb40");
//            add("tb41");
//            add("tb43");
//            add("tb44_1");
//            add("tb44_2");
//            add("tb44_3");
//            add("tb44_4");
//            add("tb45");
//            add("tb46");
//            add("tb47");
//            add("tb48");
//            add("tb50_1");
//            add("tb50_2");
//            add("tb51");
//            add("tb52");
//            add("tb63");
//            add("tb63_1");
//            add("tb63_2");
//            add("tb63_3");
//            add("tb63_4");
//            add("tb63_5");
//            add("tb63_6");
//            add("tb63_7");
//            add("tb63_8");
//            add("tb67");
//            add("tb71");
////            add("zb01");
//            add("zb04");
//            add("zb09");
//            add("zb14");
//            add("zb18");
//            add("zb28");
//            add("zb43");
//            add("zb64");
//            add("zb72");
//            add("zb76");
//            add("zb79");
//            add("zb85");
//            add("zb094");
//        }};
    }

}
