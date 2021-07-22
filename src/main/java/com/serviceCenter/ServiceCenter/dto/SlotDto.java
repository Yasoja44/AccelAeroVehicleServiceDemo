package com.serviceCenter.ServiceCenter.dto;


import com.serviceCenter.ServiceCenter.domain.Book;

import java.time.LocalDateTime;
import java.util.List;

public class SlotDto {

    String slotName;

    LocalDateTime startingTime;

    LocalDateTime endingTime;

    Integer Max;

    Integer available;

    List<Book> bookModel;

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public Integer getMax() {
        return Max;
    }

    public void setMax(Integer max) {
        Max = max;
    }

    public List<Book> getBookModel() {
        return bookModel;
    }

    public void setBookModel(List<Book> bookModel) {
        this.bookModel = bookModel;
    }
}
