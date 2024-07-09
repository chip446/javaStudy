package com.chip.phonebookjpa.phonebook;

import com.chip.phonebookjpa.category.ICategory;

import java.util.List;

public interface IPhoneBookService<T> {
    T findById(Long id);
    List<T> getAllList();
    T insert(T dto) throws Exception;
    boolean remove(Long id) throws Exception;
    T update(Long id, T dto) throws Exception;
    List<T> getListFromName(String findName);
    List<T> getListFromCategory(ICategory category);
    List<T> getListFromPhoneNumber(String findPhone);
    List<T> getListFromEmail(String findEmail);
}
