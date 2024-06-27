package com.chip.banksystem.bank;

import java.util.Scanner;

public class BankApplication {
    private AccountService accountService = new AccountService();

    public void printHeader() {
        System.out.println("======================================");
        System.out.println("1.계좌생성|2.계좌목록|3.예금|4.출금|5.종료|6.송금");
        System.out.println("======================================");
    }

    public int getChoice(Scanner input) throws Exception {
        while (true) {
            System.out.print("선택 > ");
            String a = input.nextLine();
            try {
                return Integer.parseInt(a);
            } catch (NumberFormatException e) {
                System.out.println("!!! 잘못된 입력입니다. !!!");
                printHeader();
            }
        }
    }

    public void addAccount(Scanner input) throws Exception {
        System.out.println("--------");
        System.out.println("계좌생성");
        System.out.println("--------");

        System.out.print("계좌번호:");
        String bankNumber = input.nextLine();
        System.out.print("계좌주:");
        String name = input.nextLine();
        System.out.print("초기입금액:");
        String current = input.nextLine();
        int money = Integer.parseInt(current);

        if (this.accountService.addAccount(new Account(bankNumber, name, money))) {
            System.out.println("결과: 계좌가 생성되었습니다.");
        } else {
            System.out.println("결과: 계좌생성이 실패하였습니다.\n계좌번호가 중복되었거나, 초기입금액이 음수입니다.");
        }
    }

    public void printAccounts() {
        for ( Account account : this.accountService.getAllAccount() ) {
            System.out.println(account.toString());
        }
    }

    public void income(Scanner input) throws Exception {
        Account result = getInputConsole(input, "예금");
        if ( result == null ) {
            System.out.println("에러: 계좌가 존재하지 않습니다.");
            return;
        }
        if ( result.getAmount() == -1 && ("에러").equals(result.getName()) ) {
            System.out.println("결과: 예금이 실패하였습니다.");
            return;
        }

        if ( this.accountService.deposit(result.getAccountNumber(), result.getAmount()) ) {
            System.out.println("결과: 예금이 성공되었습니다.");
        }
        else {
            System.out.println("결과: 예금이 실패하였습니다.");
        }
    }

    public void outcome(Scanner input) throws Exception {
        Account result = getInputConsole(input, "출금");
        if ( result == null ) {
            System.out.println("에러: 계좌가 존재하지 않습니다.");
            return;
        }
        if ( result.getAmount() == -1 && ("에러").equals(result.getName()) ) {
            System.out.println("결과: 예금이 실패하였습니다.");
            return;
        }

        if ( this.accountService.withdraw(result.getAccountNumber(), result.getAmount()) ) {
            System.out.println("결과: 출금이 성공되었습니다.");
        } else {
            System.out.println("에러: 출금이 실패하였습니다.");
        }
    }

    public void remittance(Scanner input) {
        System.out.println("--------");
        System.out.println("송금");
        System.out.println("--------");

        System.out.print("보내는 계좌:");
        String sendBankNumber = input.nextLine();
        Account from = this.accountService.findAccountByNumber(sendBankNumber);
        if ( from == null ) {
            System.out.println("에러: 계좌가 존재하지 않습니다.");
            return;
        }

        System.out.print("받는 계좌:");
        String getBankNumber = input.nextLine();
        Account to = this.accountService.findAccountByNumber(getBankNumber);
        if ( to == null ) {
            System.out.println("에러: 계좌가 존재하지 않습니다.");
            return;
        }

        System.out.print("송금액:");
        int money = Integer.parseInt(input.nextLine());

        if ( this.accountService.sendMoney(from.getAccountNumber(), to.getAccountNumber(), money) ) {
            System.out.println("결과: 송금이 성공되었습니다.");
        } else {
            System.out.println("에러: 송금이 실패하였습니다.");
        }
    }

    private Account getInputConsole(Scanner input, String title) {
        System.out.println("--------");
        System.out.println(title);
        System.out.println("--------");

        System.out.print("계좌번호:");
        String bankNumber = input.nextLine();
        Account account = this.accountService.findAccountByNumber(bankNumber);
        if ( account == null ) {
            return null;
        }
        System.out.print(title + "액:");
        String current = input.nextLine();
        int money = 0;
        try {
            money = Integer.parseInt(current);
        } catch (NumberFormatException e) {
            return new Account(bankNumber, "에러", -1);
        }

        return new Account(bankNumber, "임시명", money);
    }

    public static void main(String[] args) {
        try {
            BankApplication bapp = new BankApplication();
            Scanner input = new Scanner(System.in);
            boolean run = true;
            while(run) {
                bapp.printHeader();
                int choice = bapp.getChoice(input);
                switch (choice) {
                    case 1:
                        bapp.addAccount(input);
                        break;
                    case 2:
                        bapp.printAccounts();
                        break;
                    case 3:
                        bapp.income(input);
                        break;
                    case 4:
                        bapp.outcome(input);
                        break;
                    case 5:
                        run = false;
                        break;
                    case 6:
                        bapp.remittance(input);
                        break;
                    default:
                        System.out.println("!!! 잘못된 입력입니다. !!!");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
