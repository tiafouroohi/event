package com.tia.event.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tia.event.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	//get all events sort by number of users 
	
	@Query(value="select * from events order by name desc", nativeQuery = true)
	List <Event> findAllOrderByNameDesc();
	
	//create native sql query
	//select, join, functions mysql webfund
	
	

}
