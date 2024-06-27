package com.chip.banksystem.bank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String accountNumber;
    private String name;
    private int amount;

    /**
     * 계좌 (Account) 를 생성하는 사용자 정의 생성자
     * @param accountNumber : 계좌번호
     * @param name : 계좌 대표명
     * @param amount : 현재 금액
     */
    public Account(String accountNumber, String name, int amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
    }

    /**
     * 계좌(Account) 정보(계좌대표명, 계좌번호, 현재금액)를 String 으로 리턴한다.
     * @return
     */
    @Override
    public String toString() {
        return String.format("계좌번호 : %s, 이름 : %s, 금액 : %d"
            , this.accountNumber, this.name, this.amount);
    }
}
