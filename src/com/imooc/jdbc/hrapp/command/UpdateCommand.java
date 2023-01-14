package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.hrapp.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = scanner.nextInt();
        System.out.println("请输入调整薪资");
        float salary = scanner.nextFloat();
        Connection coon = null;
        try {
            coon = DBUtils.getConnection();
            PreparedStatement pstmt = coon.prepareStatement("update imooc.employee set salary = ? where eno = ?");
            pstmt.setFloat(1, salary);
            pstmt.setInt(2, eno);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                System.out.println("薪资更新成功");
            } else {
                System.out.println("未找到" + eno + "编号员工数据");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(coon);
        }
    }
}
