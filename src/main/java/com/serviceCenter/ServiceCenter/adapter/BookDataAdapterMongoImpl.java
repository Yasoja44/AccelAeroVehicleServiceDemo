package com.serviceCenter.ServiceCenter.adapter;


import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.domain.BookDataAdapter;
import com.serviceCenter.ServiceCenter.models.BookModel;
import com.serviceCenter.ServiceCenter.repository.BookMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDataAdapterMongoImpl implements BookDataAdapter {

    private final BookMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookDataAdapterMongoImpl(BookMongoRepository repository, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        BookModel bookModel = new BookModel();
        bookModel.setName(book.getName());
        bookModel.setVehicleNo(book.getVehicleNo());;
        bookModel = repository.save(bookModel);
        book.setId(bookModel.getId());
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<BookModel> bookModels = repository.findAll();
        List<Book> books = new ArrayList<>();
        for (BookModel bookModel : bookModels) {
            Book book = new Book();
            book.setId(bookModel.getId());
            book.setVehicleNo(bookModel.getVehicleNo());
            book.setName(bookModel.getName());
            books.add(book);
        }
        return books;
    }


}
