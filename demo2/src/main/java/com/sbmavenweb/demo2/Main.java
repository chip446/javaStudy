package com.sbmavenweb.demo2;

import java.util.Scanner;

public class Main {
    static int[][] memo;

    public static int pascal(int r, int c) {
        if (r == 0 || c == 0) {
            return 1;
        }
        if (memo[r][c] != 0) {
            return memo[r][c] % 100000000;
        }
        return memo[r][c] = (pascal(r, c-1) + pascal(r-1, c)) % 100000000;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        memo = new int[r][c];
        System.out.println(pascal(r-1, c-1));
    }
}
