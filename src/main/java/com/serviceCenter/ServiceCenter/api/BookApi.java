package com.serviceCenter.ServiceCenter.api;


import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.domain.BookDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookApi {

    private final BookDataAdapter bookDataAdapter;

    @Autowired
    public BookApi(BookDataAdapter bookDataAdapter) {
        this.bookDataAdapter = bookDataAdapter;
    }

    public List<Book> getAllPosts(){
        return bookDataAdapter.getAll();
    }

    public Book addPost(Book book){
        book = bookDataAdapter.save(book);
        return book;
    }


}
