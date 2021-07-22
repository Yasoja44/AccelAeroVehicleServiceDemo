package com.serviceCenter.ServiceCenter.domain;

import java.util.List;


public interface SlotDataAdapter {

    Slot save(Slot slot);
    List<Slot> getAll();
    Slot update(Slot slot);
    void remove(String id);
    List<Book> putBooksToSlot(String id, Book book);

}
