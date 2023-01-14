package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.hrapp.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCommand implements Command {
    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = scan.nextInt();
        System.out.println("请输入员工姓名");
        String eName = scan.next();
        System.out.println("请输入员工薪资");
        float salary = scan.nextFloat();
        System.out.println("请输入员工部门");
        String dName = scan.next();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into imooc.employee(eno, ename, salary, dname) values (?,?,?,?)");
            pstmt.setInt(1, eno);
            pstmt.setString(2, eName);
            pstmt.setFloat(3, salary);
            pstmt.setString(4, dName);
            int count = pstmt.executeUpdate();
            System.out.println("count: " + count);
            System.out.println(eName + "的入职手续已办理");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
    }
}
