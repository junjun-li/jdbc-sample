package com.imooc.jdbc;

import java.util.Scanner;

public class ScannerText {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // if (!scan.hasNextLine()) {
        //     return;
        // }
        String text = scan.nextLine();
        System.out.println(text);
    }
}
