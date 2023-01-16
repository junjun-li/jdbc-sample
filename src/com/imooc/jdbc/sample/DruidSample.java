package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.common.DBUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池配置与使用
 */
public class DruidSample {
    public static void main(String[] args) {
        // 1. 加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
        System.out.println(propertyFile);
        try {
            // 如果有中文或者空格等特殊字符,getPath会把他转义
            // 使用decode装回来,兼容处理
            propertyFile = new URLDecoder().decode(propertyFile, "UTF-8");
            // 加载流
            properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            // 2. 获取 DataSource 数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            // 3. 创建数据库连接
            conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from imooc.employee");
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Integer empId = res.getInt("eno");
                String ename = res.getString("ename");
                String dname = res.getString("dname");
                Float salary = res.getFloat("salary");
                System.out.println(dname + "-" + empId + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(conn);
        }

    }
}
