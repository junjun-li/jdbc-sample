package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        System.out.println("2-新增入职员工");
        System.out.println("3-删除员工");
        System.out.println("4-调整薪资");
        System.out.println("5-分页查询员工数据");
        Scanner scan = new Scanner(System.in);
        if (!scan.hasNextInt()) {
            System.out.println("请输入一个数字");
            return;
        }
        Integer cmd = scan.nextInt();
        switch (cmd) {
            case 1:
                new QueryCommand().execute();
                break;
            case 2:
                new InsertCommand().execute();
                break;
            case 3:
                new DeleteCommand().execute();
                break;
            case 4:
                new UpdateCommand().execute();
                break;
            case 5:
                new PaginationCommand().execute();
                break;
        }
    }
}
