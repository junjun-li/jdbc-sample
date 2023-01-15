package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.hrapp.common.DBUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PaginationCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入页号:");
        Scanner scan = new Scanner(System.in);
        int page = scan.nextInt();
        Connection conn = null;
        List<Employee> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from imooc.employee limit ?,10");
            // 注意分页要看一下
            pstmt.setInt(1, (page - 1) * 10);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Employee item = new Employee();
                int eno = res.getInt("eno");
                String ename = res.getString("ename");
                float salary = res.getFloat("salary");
                String dname = res.getString("dname");
                Date hiredate = res.getDate("hiredate");
                item.setEno(eno);
                item.setEname(ename);
                item.setSalary(salary);
                item.setDname(dname);
                item.setHiredate(hiredate);
                list.add(item);
            }
            System.out.println(list);
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }
    }
}
