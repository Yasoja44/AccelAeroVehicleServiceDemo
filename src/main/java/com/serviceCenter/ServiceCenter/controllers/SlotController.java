package com.serviceCenter.ServiceCenter.controllers;

import com.serviceCenter.ServiceCenter.api.BookApi;
import com.serviceCenter.ServiceCenter.api.SlotApi;
import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.domain.Slot;
import com.serviceCenter.ServiceCenter.dto.BookDto;
import com.serviceCenter.ServiceCenter.dto.SlotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/service/slots")
public class SlotController {

    private SlotApi slotApi;
    private BookApi bookApi;

    @Autowired
    public SlotController(SlotApi slotApi, BookApi bookApi){
        this.slotApi = slotApi;
        this.bookApi = bookApi;
    }


    @GetMapping
    @PreAuthorize("hasRole('MANAGER') or hasRole('OWNER')")
    public List<Slot> getPosts(){
        return slotApi.getAllPosts();
    }


    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Slot addPost(@RequestBody SlotDto slotDto){
        Slot slot = new Slot();
        slot.setSlotName(slotDto.getSlotName());
        slot.setStartingTime(slotDto.getStartingTime());
        slot.setMax(slotDto.getMax());
        slot.setAvailable(slotDto.getMax());
        slot.setBook(slotDto.getBookModel());
        slot.setEndingTime(slotDto.getEndingTime());
        return slotApi.addPost(slot);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable String id){
        slotApi.removePost(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Slot updatePost(@PathVariable String id,@RequestBody SlotDto slotDto){
        Slot slot = new Slot();
        slot.setId(id);
        slot.setSlotName(slotDto.getSlotName());
        slot.setStartingTime(slotDto.getStartingTime());
        slot.setEndingTime(slotDto.getEndingTime());
        slot.setBook(slotDto.getBookModel());
        slot.setMax(slotDto.getMax());
        return slotApi.updatePost(slot);
    }

    @PostMapping("/put/{id}")
    @PreAuthorize("hasRole('OWNER')")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Book> putBooksToSlot(@PathVariable String id, @RequestBody BookDto bookDto){

        /*
        BookModel bookmodel = new BookModel();
        bookmodel.setName(bookDto.getName());
        bookmodel.setVehicleNo(bookDto.getVehicleNo());

        System.out.println(bookmodel);

        Book book = new Book();
        book.setId(bookmodel.getId());
        book.setName(bookmodel.getName());
        book.setVehicleNo(bookmodel.getVehicleNo());

        return slotApi.addBooksToSlot(id,book);
        */



        ///////////
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setVehicleNo(bookDto.getVehicleNo());
        Book b = bookApi.addPost(book);

        return slotApi.addBooksToSlot(id,b);






    }

}