package com.chip.banksystem2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] str2 = str.split(" ");
        int a = Integer.parseInt(str2[0]);
        int b = Integer.parseInt(str2[1]);
        int c = Integer.parseInt(str2[2]);

        System.out.println(a + b + c);
    }
}
