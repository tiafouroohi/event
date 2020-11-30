package com.tia.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tia.event.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

}
