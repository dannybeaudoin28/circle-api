package com.beaudoin.circleapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beaudoin.circleapi.data.model.SocialConnection;

@Repository
public interface SocialConnectionsRepository extends CrudRepository<SocialConnection, Long> {
    
}
