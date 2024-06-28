package com.chip.phonesystem;

import com.chip.phonesystem.make.PhoneBook;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private IPhoneBookService<IPhoneBook> phoneBookService;
    public void setPhoneBookService(IPhoneBookService<IPhoneBook> phoneBookService) throws Exception {
        this.phoneBookService = phoneBookService;
        this.phoneBookService.loadData();
    }

    public ConsoleApplication() {
    }

    public void printTitle() {
        System.out.println("============================================================================");
        System.out.println("1.연락처생성|2.목록|3.수정|4.삭제|5.이름검색|6.그룹검색|7.폰검색|8.이메일검색|9.종료");
        System.out.println("============================================================================");
    }

    public int getChoice(Scanner input) throws Exception {
        System.out.print("선택 > ");
        String a = input.nextLine();
        return Integer.parseInt(a);
    }

    public void printList() {
        this.printList(this.phoneBookService.getAllList());
    }

    private EPhoneGroup getGroupFromScanner(Scanner input, String title) {
        boolean doWhile = false;
        EPhoneGroup eGroup = EPhoneGroup.Friends;
        String[] groups = {"Friends", "Families", "Schools", "Jobs", "Hobbies"};
        do {
            System.out.print(title + "연락처 그룹{Friends(1),Families(2),Schools(3),Jobs(4),Hobbies(5)} :");
            String group = input.nextLine();
            if (group.isEmpty() && "수정할 ".equals(title)) {
                return null;
            }
            switch (group) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    doWhile = false;
                    eGroup = EPhoneGroup.valueOf(groups[Integer.parseInt(group)-1]);
                    break;
                default:
                    doWhile = true;
                    System.out.println("Friends(1),Families(2),Schools(3),Jobs(4),Hobbies(5) 1~5사이에 입력");
                    break;
            }
        } while (doWhile);
        return eGroup;
    }

    private String getNameFromScanner(Scanner input, String title) {
        boolean doWhile = false;
        String name;
        do {
            System.out.print(title + "연락처 이름 :");
            name = input.nextLine();
            doWhile = false;
            if (name.isEmpty() && "수정할 ".equals(title)) {
                return name;
            }
            else if (name.isEmpty()) {
                System.out.println("에러: 이름은 필수 입력사항입니다.");
                doWhile = true;
            }
        } while (doWhile);
        return name;
    }

    private String getPhoneFromScanner(Scanner input, String title) {
        boolean doWhile = false;
        String phone;
        do {
            System.out.print(title + "전화번호 :");
            phone = input.nextLine();
            if (phone.isEmpty() && "수정할 ".equals(title)) {
                return phone;
            }
            try {
                doWhile = false;
                String phone2 = phone.replaceAll("-", "");
                Long.parseLong(phone2);
                if (phone2.length() > 18) {
                    System.out.println("에러: 전화번호는 18자리 이내의 숫자만 입력하세요.");
                    doWhile = true;
                }
            } catch (Exception ex) {
                System.out.println("에러: 전화번호는 필수 입력사항이고 숫자와 -기호로만 입력가능합니다.");
                doWhile = true;
            }
        } while (doWhile);
        return phone;
    }

    private String getEmailFromScanner(Scanner input, String title) {
        boolean doWhile = false;
        String email;
        do {
            System.out.print(title + "이메일 :");
            email = input.nextLine();
            doWhile = false;
            if (email.isEmpty() && "수정할 ".equals(title)) {
                return email;
            }
            else if (email.isEmpty()) {
                System.out.println("에러: 이메일은 필수 입력사항입니다.");
                doWhile = true;
            }
        } while (doWhile);
        return email;
    }

    public void insert(Scanner input) throws Exception {
        System.out.println("--------");
        System.out.println("연락처 생성");
        System.out.println("--------");

        String name = this.getNameFromScanner(input, "");
        EPhoneGroup group = this.getGroupFromScanner(input, "");
        String phone = this.getPhoneFromScanner(input, "");
        String email = this.getEmailFromScanner(input, "");

        if (this.phoneBookService.insert(name, group, phone, email)) {
            this.phoneBookService.saveData();
            System.out.println("결과: 데이터 추가 성공되었습니다.");
        }
    }

    public void update(Scanner input) throws Exception {
        System.out.println("--------");
        System.out.println("연락처 수정 (ID입력후, 공백 입력시 기존값 유지)");
        System.out.println("--------");

        IPhoneBook result = getFindIdConsole(input, "수정할 ");
        if (result == null) {
            System.out.println("에러: ID 데이터 가 존재하지 않습니다.");
            return;
        }
        String name = this.getNameFromScanner(input, "수정할 ");
        if (name.isEmpty()) {
            name = result.getName();
        }
        EPhoneGroup group = this.getGroupFromScanner(input, "수정할 ");
        if (group == null) {
            group = result.getGroup();
        }
        String phone = this.getPhoneFromScanner(input, "수정할 ");
        if (phone.isEmpty()) {
            phone = result.getPhoneNumber();
        }
        String email = this.getEmailFromScanner(input, "수정할 ");
        if (email.isEmpty()) {
            email = result.getEmail();
        }

        IPhoneBook update = PhoneBook.builder()
                .id(result.getId()).name(name)
                .group(group)
                .phoneNumber(phone).email(email).build();
        if (this.phoneBookService.update(update.getId(), update)) {
            this.phoneBookService.saveData();
            System.out.println("결과: 데이터 수정 성공되었습니다.");
        }
    }

    public void delete(Scanner input) throws Exception {
        IPhoneBook result = getFindIdConsole(input, "삭제할 ");
        if (result == null) {
            System.out.println("에러: ID 데이터 가 존재하지 않습니다.");
            return;
        }
        if (this.phoneBookService.remove(result.getId())) {
            this.phoneBookService.saveData();
            System.out.println("결과: 데이터 삭제 성공되었습니다.");
        } else {
            System.out.println("결과: 데이터 삭제 실패되었습니다.");
        }
    }

    private IPhoneBook getFindIdConsole(Scanner input, String title) {
        long l = 0L;
        do {
            System.out.print(title + "ID 번호:");
            String id = input.nextLine();
            try {
                l = Long.parseLong(id);
            } catch (Exception ex) {
                System.out.println("ID 번호를 숫자로만 입력하세요.");
            }
        } while ( l <= 0 );
        IPhoneBook iPhoneBook = (IPhoneBook)this.phoneBookService.findById(l);
        return iPhoneBook;
    }

    private void printList(List<IPhoneBook> array) {
        for (IPhoneBook object : array) {
            System.out.println(object.toString());
        }
    }

    public void searchByName(Scanner input) {
        System.out.print("찾을 이름(공백입력시 전체 출력) :");
        String name = input.nextLine();

        List<IPhoneBook> list = this.phoneBookService.getListFromName(name);
        this.printList(list);
    }

    public void searchByGroup(Scanner input) {
        EPhoneGroup group = this.getGroupFromScanner(input, "찾을 ");

        List<IPhoneBook> list = this.phoneBookService.getListFromGroup(group);
        this.printList(list);
    }

    public void searchByPhone(Scanner input) {
        System.out.print("찾을 번호(공백입력시 전체 출력) :");
        String findPhone = input.nextLine();

        List<IPhoneBook> list = this.phoneBookService.getListFromPhoneNumber(findPhone);
        this.printList(list);
    }

    public void searchByEmail(Scanner input) {
        System.out.print("찾을 Email(공백입력시 전체 출력) :");
        String findEmail = input.nextLine();

        List<IPhoneBook> list = this.phoneBookService.getListFromEmail(findEmail);
        this.printList(list);
    }
}