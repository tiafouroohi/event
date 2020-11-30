package com.tia.event.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia.event.models.Comment;
import com.tia.event.models.Event;
import com.tia.event.models.User;
import com.tia.event.repositories.CommentRepository;
import com.tia.event.repositories.EventRepository;
import com.tia.event.repositories.EventUserRepository;
import com.tia.event.repositories.UserRepository;


@Service
public class EventService {
	
	@Autowired 
	private UserService userService;
    

	@Autowired
    private UserRepository userRepository;
    
    private EventRepository eventRepository;
    
    private EventUserRepository eventUserRepository;
    
    private CommentRepository commentRepository;
    
    public EventService(UserRepository userRepository, EventRepository eventRepository, EventUserRepository eventUserRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.eventUserRepository = eventUserRepository;
        this.commentRepository =commentRepository;
    }
 
   
    // returns all the books
    public List<Event> allEvents() {
        return (List<Event>) eventRepository.findAll();
    }
    public List<User>allUsers(){
    	return (List<User>) userRepository.findAll();
    }
    public List<Comment>allComments(){
    	return(List<Comment>) commentRepository.findAll();
    }
    public List<Event> findAllOrderByNameDesc(){
    	return eventRepository.findAllOrderByNameDesc();
    }
   
    public Event createEvent(Event event, User host) {
    	event.setHost(host);
        return eventRepository.save(event);
    }
    
    public Event save(Event b) {
    	return eventRepository.save(b);
    }
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public Comment save(Comment b) {
    	return commentRepository.save(b);
    }
    public Event findEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isPresent()) {
            return optionalEvent.get();
        } else {
            return null;
        }
    }
    public Comment findComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            return null;
        }
    }
     
    public void addCommentToEvent(Comment comment, Event event) {
    	comment.setEvent(event);
    	commentRepository.save(comment);
    	
    }
    
   public void addUserToEvent(Long id, User user) {
	   Integer count = 0;
	   count = count +=1;
	   Event event = findEvent(id);
		List<User> users = event.getUsers();
		users.add(user);
		event.setUsers(users);
	   eventRepository.save(event);
   }
    public Event updateEvent(Event event) {
    	return eventRepository.save(event);
    }
    
  

    
  
    

		
	

 
}