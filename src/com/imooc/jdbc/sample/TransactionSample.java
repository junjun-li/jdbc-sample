package com.imooc.jdbc.sample;

import com.imooc.jdbc.hrapp.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionSample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBUtils.getConnection();
            // 关闭JDBC自动提交模式
            conn.setAutoCommit(false);
            String sql = "insert into imooc.employee(eno, ename, salary, dname) values (?,?,?,?)";
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    throw new RuntimeException("插入失败");
                }
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 4000);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
            // 提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtils.closeConnection(conn);
        }
    }
}
