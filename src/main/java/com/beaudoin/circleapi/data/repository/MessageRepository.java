package com.beaudoin.circleapi.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beaudoin.circleapi.data.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    // @Query("SELECT m FROM Message m " +
    //         "WHERE (m.messageSender = :userId1 AND m.messageReceiver = :userId2) " +
    //         "   OR (m.messageSender = :userId2 AND m.messageReceiver = :userId1)")
    // List<Message> findMessagesBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

}
