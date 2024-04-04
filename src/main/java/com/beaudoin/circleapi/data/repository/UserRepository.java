package com.beaudoin.circleapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beaudoin.circleapi.data.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
