package com.beaudoin.circleapi.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beaudoin.circleapi.data.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    public User findByUserEmail(String userEmail);

    @Query("SELECT u FROM User u WHERE u.userEmail LIKE %:userEmail%")
    public List<User> findAllByUserEmail(String userEmail);
}
