package com.sbmavenweb.demo2.mathexam;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String name;
    private int score;

    @Override
    public String toString() {
        return String.format("이름 : %s, 점수 : %d, 주소 : %s", name, score, super.toString());
    }
}
