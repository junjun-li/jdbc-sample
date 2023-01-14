package com.imooc.jdbc.sample;

import java.sql.*;

public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection coon = null;
        try {
            // 1. 加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 创建数据库连接
            coon = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "11776174"
            );
            // 3. 创建 Statement 对象
            Statement stmt = coon.createStatement();
            ResultSet res = stmt.executeQuery("select * from employee");
            // 4. 遍历查询结果
            while (res.next()) {
                Integer eno = res.getInt("eno");
                String ename = res.getString("ename");
                Float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                String hiredate = res.getString("hiredate");
                System.out.println(eno + "-" + ename + "-" + salary + "-" + dname + "-" + hiredate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (coon != null && coon.isClosed() == false) {
                    // 5. 关闭连接,释放资源Exception
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
