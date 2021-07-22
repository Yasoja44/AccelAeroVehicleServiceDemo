package com.serviceCenter.ServiceCenter.controllers;

import com.serviceCenter.ServiceCenter.api.BookApi;
import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/books")
public class BookEndpoint {

    private BookApi bookApi;

    @Autowired
    public BookEndpoint(BookApi bookApi){
        this.bookApi = bookApi;
    }

    @GetMapping
    public List<Book> getPosts(){
        return bookApi.getAllPosts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addPost(@RequestBody BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setVehicleNo(bookDto.getVehicleNo());
        return bookApi.addPost(book);
    }
}
