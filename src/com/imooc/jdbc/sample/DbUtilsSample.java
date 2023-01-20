package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DbUtilsSample {
    private static void query() {
        Properties properties = new Properties();
        String propertiesFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertiesFile = new URLDecoder().decode(propertiesFile, "UTF-8");
            properties.load(new FileInputStream(propertiesFile));
            // 获取数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 创建数据库连接
            QueryRunner qr = new QueryRunner(dataSource);
            List<Employee> list = qr.query(
                "select * from imooc.employee where ename=? or dname=?",
                new BeanListHandler<>(Employee.class),
                "张三", "市场部"
                // new Object[]{1,2}
                // 2
            );
            for (Employee item : list) {
                System.out.println(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void update() {
        Properties properties = new Properties();
        String propertiesFile = DbUtilsSample.class.getResource("/druid-config.properties").getPath();
        Connection conn = null;
        try {
            propertiesFile = new URLDecoder().decode(propertiesFile, "UTF-8");
            properties.load(new FileInputStream(propertiesFile));
            // 创建数据库连接
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            QueryRunner qr = new QueryRunner(dataSource);
            qr.update("update imooc.employee set salary=salary+1000 where eno=?", 3308);
            qr.update("update imooc.employee set salary=salary-500 where eno=?", 3420);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        // DbUtilsSample.query();
        update();
    }
}
