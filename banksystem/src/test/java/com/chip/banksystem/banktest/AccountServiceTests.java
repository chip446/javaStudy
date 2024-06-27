package com.chip.banksystem.banktest;

import com.chip.banksystem.bank.Account;
import com.chip.banksystem.bank.AccountService;
import com.chip.banksystem.bank.BankApplication;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountServiceTests {
    @Test
    public void addAccountTest() {
        AccountService accountService = new AccountService();

        accountService.clear();
        accountService.addAccount("111-111", "홍길동", 20000);

        assertThat(accountService.size()).isEqualTo(1);

        Account find = accountService.findAccountByNumber("111-111");
        assertThat(find).isNotNull();
        assertThat(find.getName()).isEqualTo("홍길동");
        assertThat(find.getAmount()).isEqualTo(20000);
    }

    @Test
    public void depositTest() {
        AccountService accountService = new AccountService();
        accountService.addAccount("111-111", "홍길동", 20000);
        accountService.addAccount("222-222", "이순신", 30000);
        assertThat(accountService.size()).isEqualTo(2);

        boolean result = accountService.deposit("222-222", 10000);
        assertThat(result).isEqualTo(true);

        Account find = accountService.findAccountByNumber("222-222");
        assertThat(find).isNotNull();
        assertThat(find.getAmount()).isEqualTo(40000);

        Account find2 = accountService.findAccountByNumber("444-444");
        assertThat(find2).isNull();
    }

    @Test
    public void withdrawTest() {
        AccountService accountService = new AccountService();
        accountService.addAccount("333-333", "홍길동", 40000);
        assertThat(accountService.size()).isEqualTo(1);

        boolean result = accountService.withdraw("333-333", 20000);
        assertThat(result).isEqualTo(true);

        Account find = accountService.findAccountByNumber("333-333");
        assertThat(find).isNotNull();
        assertThat(find.getAmount()).isEqualTo(20000);

        Account find2 = accountService.findAccountByNumber("555-555");
        assertThat(find2).isNull();

        boolean result2 = accountService.withdraw("333-333", 30000);
        assertThat(result2).isEqualTo(false);

        Account find3 = accountService.findAccountByNumber("333-333");
        assertThat(find3).isNotNull();
        assertThat(find3.getAmount()).isEqualTo(20000);
    }

    @Test
    public void minusMoneyTest() {
        AccountService accountService = new AccountService();
        accountService.addAccount("111-111", "홍길동", 10000);
        assertThat(accountService.size()).isEqualTo(1);

        boolean result = accountService.deposit("111-111", -1000);
        assertThat(result).isEqualTo(false);

        boolean result2 = accountService.withdraw("111-111", -2000);
        assertThat(result2).isEqualTo(false);

        accountService.addAccount("222-222", "고길동", -10000);

        Account find = accountService.findAccountByNumber("222-222");
        assertThat(find).isNull();
    }

    @Test
    public void inputStringTest() {
        //문자열이 들어간 경우 BankApplication에서 처리
    }

    @Test
    public void duplicateAccountTest() {
        AccountService accountService = new AccountService();
        accountService.addAccount("111-111", "홍길동", 10000);
        assertThat(accountService.size()).isEqualTo(1);

        boolean result = accountService.addAccount("111-111", "이순신", 20000);
        assertThat(result).isEqualTo(false);
        assertThat(accountService.size()).isEqualTo(1);

        Account find = accountService.findAccountByNumber("111-111");
        assertThat(find).isNotNull();
        assertThat(find.getName()).isEqualTo("홍길동");
        assertThat(find.getAmount()).isEqualTo(10000);
    }

    @Test
    public void remittanceTest() {
        AccountService accountService = new AccountService();
        accountService.addAccount("111-111", "홍길동", 10000);
        accountService.addAccount("222-222", "이순신", 20000);
        assertThat(accountService.size()).isEqualTo(2);

        boolean result = accountService.sendMoney("111-111", "222-222", 5000);
        assertThat(result).isEqualTo(true);

        Account find = accountService.findAccountByNumber("111-111");
        assertThat(find).isNotNull();
        assertThat(find.getAmount()).isEqualTo(5000);

        Account find2 = accountService.findAccountByNumber("222-222");
        assertThat(find2).isNotNull();
        assertThat(find2.getAmount()).isEqualTo(25000);

        boolean result2 = accountService.sendMoney("111-111", "333-333", 5000);
        assertThat(result2).isEqualTo(false);

        Account find3 = accountService.findAccountByNumber("111-111");
        assertThat(find3).isNotNull();
        assertThat(find3.getAmount()).isEqualTo(5000);

        Account find4 = accountService.findAccountByNumber("333-333");
        assertThat(find4).isNull();
    }
}
