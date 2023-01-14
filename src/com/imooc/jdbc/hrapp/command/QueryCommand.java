package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

// 1. 加载并注册JDBC驱动
// 2. 创建数据库连接
// 3. 创建 Statement 对象
// 4. 遍历查询结果
// 5. 关闭资源
public class QueryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入部门名称：");
        Scanner scan = new Scanner(System.in);
        // if (!scan.hasNextLine()) {
        //     return;
        // }
        String dName = scan.nextLine();
        Connection coon = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            coon = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "11776174"
            );
            Statement stmt = coon.createStatement();
            ResultSet res = stmt.executeQuery("select * from employee where dname='" + dName + "'");
            while (res.next()) {
                Integer eno = res.getInt("eno");
                String ename = res.getString("ename");
                String salary = res.getString("salary");
                String dname = res.getString("dname");
                Date hiredate = res.getDate("hiredate");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary + "-" + hiredate);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (coon != null && !coon.isClosed()) {
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
