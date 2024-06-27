package com.sbmavenweb.demo2.mathexam;

import javax.swing.*;
import java.awt.*;

public class Task extends JFrame implements Runnable{
    public Task() {
        setTitle("실행 시작"); //타이틀
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //정상종료
        Container c = getContentPane(); //컨텐트팬 알아냄

        c.setBackground(Color.yellow); //배경색

        Thread th= new Thread(this); //스레드 생성
        th.start(); //스레드 시작

        setSize(300, 200); //프레임 크기
        setVisible(true); //창표시
        c.setFocusable(true); //컨텐트팬이 포거스 받음
        c.requestFocus(); //컨탠트팬 포커스 설정, 키 입력
    }


    @Override
    public void run() {
        try {
            Thread.sleep(10000); //10초 대기
            setTitle("실행 종료"); //타이틀
            getContentPane().setBackground(Color.blue); //배경색 변경

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Task();


    }
}