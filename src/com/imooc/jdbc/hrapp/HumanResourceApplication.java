package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        Scanner scan = new Scanner(System.in);
        if (!scan.hasNextInt()) {
            System.out.println("请输入一个数字");
            return;
        }
        Integer cmd = scan.nextInt();
        switch (cmd) {
            case 1:
                Command command = new QueryCommand();
                command.execute();
            case 2:
                // System.out.println("办理员工入职");
        }
    }
}
