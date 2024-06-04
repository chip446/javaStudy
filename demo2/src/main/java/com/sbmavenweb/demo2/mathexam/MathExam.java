package com.sbmavenweb.demo2.mathexam;

public class MathExam {
    public int subTest001(int n1, int n2) throws Exception {
        if (n1 < -50000 || n1 > 50000) {
            throw new Exception("num1 값은 -50000보다 작거나, 50000보다 크다");
        }
        if (n2 < -50000 || n2 > 50000) {
            throw new RuntimeException("num2 값은 -50000보다 작거나, 50000보다 크다.");
        }
        return n1 - n2;
    }

    public int mathTest002(int n1, int n2) throws Exception {
        if (n1 < 0 || n1 > 100) {
            throw new Exception("num1 값은 0 ~ 100 사이의 값 이어야 합니다.");
        }
        if (n2 < 0 || n2 > 100) {
            throw new Exception("num2 값은 0 ~ 100 사이의 값 이어야 합니다.");
        }
        return n1 * n2;
    }

    public int mathTest004(int n1, int n2) throws Exception {
        if (n1 < 0 || n1 > 10000) {
            throw new Exception("num1 값은 0 ~ 10000 사이의 값 이어야 합니다.");
        }
        if (n2 < 0 || n2 > 10000) {
            throw new Exception("num2 값은 0 ~ 10000 사이의 값 이어야 합니다.");
        }
        return n1 == n2 ? 1 : -1;
    }

    // 0604

    public int exam120585(int[] array, int height) throws Exception {
        if (array == null || array.length == 0 || array.length > 100) {
            throw new Exception(String.format("array는 null이거나 길이가 1~100 이어야 합니다."));
        }

        if (height <= 1 || height > 200) {
            throw new Exception(String.format("height는 1~200 이어야 합니다."));
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 0 || array[i] > 200) {
                throw new Exception(String.format("array[%d] = [%d]는 1~200 이어야 합니다.", i, array[i]));
            }

            if (array[i] > height) {
                // 키 큰 사람을 누적해야 한다.
                result++;
            }
        }
        return result;
    }

    public int exam120818(int price) throws Exception {
        return 0;
    }
}