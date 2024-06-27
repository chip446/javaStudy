package com.chip.phonesystem.make;

import com.chip.phonesystem.EPhoneGroup;
import com.chip.phonesystem.IPhoneBook;
import com.chip.phonesystem.IPhoneBookRepository;
import com.chip.phonesystem.IPhoneBookService;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookServiceImpl implements IPhoneBookService<IPhoneBook> {
    private List<IPhoneBook> list = new ArrayList<>();
    private final IPhoneBookRepository<IPhoneBook> phoneBookRepository;

    public PhoneBookServiceImpl(String arg1, String fileName) throws Exception {
        if ( "-j".equals(arg1) ) {
            this.phoneBookRepository = new PhoneBookJsonRepository(fileName);
        } else if ( "-t".equals(arg1) ) {
            this.phoneBookRepository = new PhoneBookTextRepository(fileName);
        } else {
            throw new Exception( "Error : You need program arguments (-j/-t) (filename) !");
        }
    }

    @Override
    public int size() {
        return this.list.size();
    }

    /**
     * List<IPhoneBook> list 배열객체에서 id 가 가장 큰 숫자를 찾아서 리턴한다.
     * @return get Maximum id number value
     */
    @Override
    public Long getMaxId() {
        return this.list.stream().mapToLong(IPhoneBook::getId).max().orElse(0L) + 1;
    }

    @Override
    public IPhoneBook findById(Long id) {
        return this.list.stream().filter(obj -> id.equals(obj.getId())).findFirst().orElse(null);
    }

    @Override
    public List<IPhoneBook> getAllList() {
        return this.list;
    }

    @Override
    public boolean insert(String name, EPhoneGroup group, String phoneNumber, String email) throws Exception {
        IPhoneBook phoneBook = PhoneBook.builder()
                .id(this.getMaxId())
                .name(name).group(group)
                .phoneNumber(phoneNumber).email(email).build();
        this.list.add(phoneBook);
        return true;
    }

    @Override
    public boolean insert(IPhoneBook phoneBook) throws Exception {
        this.list.add(phoneBook);
        return true;
    }

    @Override
    public boolean remove(Long id) {
        IPhoneBook find = this.findById(id);
        if ( find != null ) {
            this.list.remove(find);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, IPhoneBook phoneBook) {
        int findIndex = this.findIndexById(id);
        if ( findIndex >= 0 ) {
            phoneBook.setId(id);
            this.list.set(findIndex, phoneBook);
            return true;
        }
        return false;
    }

    private int findIndexById(Long id) {
        return this.list.indexOf(this.findById(id));
    }

    @Override
    public List<IPhoneBook> getListFromName(String findName) {
        return this.list.stream().filter(obj -> obj.getName().contains(findName)).toList();
    }

    @Override
    public List<IPhoneBook> getListFromGroup(EPhoneGroup phoneGroup) {
        return this.list.stream().filter(obj -> phoneGroup.equals(obj.getGroup())).toList();
    }

    @Override
    public List<IPhoneBook> getListFromPhoneNumber(String findPhone) {
        return this.list.stream().filter(obj -> obj.getPhoneNumber().contains(findPhone)).toList();
    }

    @Override
    public List<IPhoneBook> getListFromEmail(String findEmail) {
        return this.list.stream().filter(obj -> obj.getEmail().contains(findEmail)).toList();
    }

    @Override
    public boolean loadData() throws Exception {
        return this.phoneBookRepository.loadData(this.list);
    }

    @Override
    public boolean saveData() throws Exception {
        return this.phoneBookRepository.saveData(this.list);
    }
}
