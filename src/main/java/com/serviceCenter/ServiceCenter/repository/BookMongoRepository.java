package com.serviceCenter.ServiceCenter.repository;


import com.serviceCenter.ServiceCenter.models.BookModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMongoRepository extends MongoRepository<BookModel, String> {
}
