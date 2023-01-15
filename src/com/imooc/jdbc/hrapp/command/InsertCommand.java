package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.hrapp.common.DBUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        System.out.println("请输入入职日期");
        // String => java.sql.Date步骤
        // 1. String 转为 java.util.Date
        // 2. 将 java.util.Date 转换为时间戳
        // 3. java.sql.Date 支持传入时间戳
        String strDate = scan.next();
        java.util.Date udDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            udDate = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date sqDate = new Date(udDate.getTime());
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into imooc.employee(eno, ename, salary, dname, hiredate) values (?,?,?,?,?)");
            pstmt.setInt(1, eno);
            pstmt.setString(2, eName);
            pstmt.setFloat(3, salary);
            pstmt.setString(4, dName);
            pstmt.setDate(5, sqDate);
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
