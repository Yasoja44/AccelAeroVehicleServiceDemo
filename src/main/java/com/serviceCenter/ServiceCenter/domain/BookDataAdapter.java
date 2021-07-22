package com.serviceCenter.ServiceCenter.domain;

import java.util.List;

public interface BookDataAdapter {

    Book save(Book book);
    List<Book> getAll();
}
