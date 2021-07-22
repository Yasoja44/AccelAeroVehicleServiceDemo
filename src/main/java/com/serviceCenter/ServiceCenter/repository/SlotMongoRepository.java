package com.serviceCenter.ServiceCenter.repository;


import com.serviceCenter.ServiceCenter.models.SlotModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotMongoRepository extends MongoRepository<SlotModel, String> {


}
