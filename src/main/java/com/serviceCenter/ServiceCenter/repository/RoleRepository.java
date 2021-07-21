package com.serviceCenter.ServiceCenter.repository;

import com.serviceCenter.ServiceCenter.models.ERole;
import com.serviceCenter.ServiceCenter.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
