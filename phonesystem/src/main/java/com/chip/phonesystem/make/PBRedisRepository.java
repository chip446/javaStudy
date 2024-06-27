package com.chip.phonesystem.make;

import com.chip.phonesystem.IPhoneBook;
import com.chip.phonesystem.IPhoneBookRepository;

import java.util.List;

public class PBRedisRepository implements IPhoneBookRepository<IPhoneBook> {
    @Override
    public boolean saveData(List<IPhoneBook> listData) throws Exception {
        return false;
    }

    @Override
    public boolean loadData(List<IPhoneBook> listData) throws Exception {
        return false;
    }
}
