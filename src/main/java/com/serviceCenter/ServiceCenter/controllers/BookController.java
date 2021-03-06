package com.serviceCenter.ServiceCenter.controllers;

import com.serviceCenter.ServiceCenter.api.BookApi;
import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/service/books")
public class BookController {

    private BookApi bookApi;

    @Autowired
    public BookController(BookApi bookApi){
        this.bookApi = bookApi;
    }

    @GetMapping
    @PreAuthorize("hasRole('OWNER')")
    public List<Book> getPosts(){
        return bookApi.getAllPosts();
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addPost(@RequestBody BookDto bookDto){
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setVehicleNo(bookDto.getVehicleNo());
        return bookApi.addPost(book);
    }
}
