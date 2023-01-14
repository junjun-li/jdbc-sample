package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.hrapp.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = scanner.nextInt();
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("delete from imooc.employee where eno = ?");
            pstmt.setInt(1, eno);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                System.out.println("员工已删除");
            } else {
                System.out.println("未找到" + eno + "编号的员工");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
