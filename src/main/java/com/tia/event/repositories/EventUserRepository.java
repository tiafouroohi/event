package com.tia.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tia.event.models.EventUser;

public interface EventUserRepository extends CrudRepository<EventUser, Long>{

}
