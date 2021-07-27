package com.serviceCenter.ServiceCenter.adapter;

import com.serviceCenter.ServiceCenter.domain.Book;
import com.serviceCenter.ServiceCenter.domain.Slot;
import com.serviceCenter.ServiceCenter.domain.SlotDataAdapter;
import com.serviceCenter.ServiceCenter.models.SlotModel;
import com.serviceCenter.ServiceCenter.repository.SlotMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SlotDataAdapterMongoImpl implements SlotDataAdapter {

    private final SlotMongoRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SlotDataAdapterMongoImpl(SlotMongoRepository repository, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.repository = repository;
    }

    @Override
    public Slot save(Slot slot) {
        SlotModel slotModel = new SlotModel();
        slotModel.setSlotName(slot.getSlotName());
        slotModel.setStartingTime(slot.getStartingTime());
        slotModel.setEndingTime(slot.getEndingTime());
        slotModel.setMax(slot.getMax());
        slotModel.setAvailable(slot.getMax());
        slotModel.setBookModel(slot.getBook());
        slotModel = repository.save(slotModel);
        slot.setId(slotModel.getId());
        return slot;
    }

    @Override
    public List<Slot> getAll() {
        List<SlotModel> slotModels = repository.findAll();
        List<Slot> slots = new ArrayList<>();
        for (SlotModel slotModel : slotModels) {
            Slot slot = new Slot();
            slot.setId(slotModel.getId());
            slot.setSlotName(slotModel.getSlotName());
            slot.setStartingTime(slotModel.getStartingTime());
            slot.setEndingTime(slotModel.getEndingTime());
            slot.setMax(slotModel.getMax());
            slot.setBook(slotModel.getBookModel());
            slots.add(slot);
        }
        return slots;
    }

    @Override
    public Slot update(Slot slot) {



        Optional<SlotModel> temp = repository.findById(slot.getId());
        int occupied = temp.get().getBookModel().size();
        int max = slot.getMax();
        int available = max-occupied;

        slot.setAvailable(available);
        slot.setBook(temp.get().getBookModel());

        SlotModel slotModel =
                mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(slot.getId())),
                        new Update().set("slotName", slot.getSlotName())
                                .set("startingTime", slot.getStartingTime())
                                .set("endingTime", slot.getEndingTime())
                                .set("Max", slot.getMax())
                                .set("available",slot.getAvailable()),
                        SlotModel.class);
        return slot;
    }

    @Override
    public void remove(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> putBooksToSlot(String id, Book book) {
        SlotModel slotModel = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)),SlotModel.class);

        List<Book> books = slotModel.getBookModel();
        books.add(book);

        //////////
        Integer ava = slotModel.getAvailable();
        //////////

        SlotModel sm = mongoTemplate.findAndModify(Query.query(Criteria.where("id").is(id)),
                new Update().set("bookModel", books).set("available",ava-1),
                SlotModel.class);

        return books;
    }
}
