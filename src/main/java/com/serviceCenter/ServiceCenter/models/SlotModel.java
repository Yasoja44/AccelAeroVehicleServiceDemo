package com.serviceCenter.ServiceCenter.models;

import com.serviceCenter.ServiceCenter.domain.Book;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("slots")
public class SlotModel {

    @Id
    private String id;

    private String slotName;

    private LocalDateTime startingTime;

    private LocalDateTime endingTime;

    private Integer Max;

    private List<Book> bookModel;

    Integer available;

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

    public Integer getMax() {
        return Max;
    }

    public void setMax(Integer Max) {
        this.Max = Max;
    }

    public List<Book> getBookModel() {
        return bookModel;
    }

    public void setBookModel(List<Book> bookModel) {
        this.bookModel = bookModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}


