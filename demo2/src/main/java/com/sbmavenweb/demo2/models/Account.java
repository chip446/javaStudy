package com.sbmavenweb.demo2.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String name;
    private String bankNumber;
    private int current;

    public Account(String name, String bankNumber, int current) {
        this.name = name;
        this.bankNumber = bankNumber;
        this.current = current;
    }

    @Override
    public String toString() {
        return String.format("Account{name='%s', bankNumber='%s', current=%d}"
                , this.name, this.bankNumber, this.current);
    }
}