package com.serviceCenter.ServiceCenter.api;

import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.domain.Slot;
import com.serviceCenter.ServiceCenter.domain.SlotDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotApi {

    private final SlotDataAdapter slotDataAdapter;

    @Autowired
    public SlotApi(SlotDataAdapter slotDataAdapter) {
        this.slotDataAdapter = slotDataAdapter;
    }


    public List<Slot> getAllPosts(){
        return slotDataAdapter.getAll();
    }

    public Slot addPost(Slot slot){
        slot = slotDataAdapter.save(slot);
        return slot;
    }

    public void removePost(String id){
        slotDataAdapter.remove(id);
    }


    public Slot updatePost(Slot slot) {

        return slotDataAdapter.update(slot);
    }

    public List<Book> addBooksToSlot(String id, Book book) {

        return slotDataAdapter.putBooksToSlot(id, book);
    }
}
