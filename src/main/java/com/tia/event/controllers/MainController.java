package com.tia.event.controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia.event.models.Comment;
import com.tia.event.models.Event;
import com.tia.event.models.User;
import com.tia.event.services.EventService;
import com.tia.event.services.UserService;



@Controller
public class MainController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(@ModelAttribute("event")Event event, HttpSession session, Model model) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";	
		} 
		Long userId=(Long) session.getAttribute("userid");
	        User u = userService.findUserById(userId);
	        model.addAttribute("user", u);
	        model.addAttribute("allEvents", eventService.findAllOrderByNameDesc());
	        
		return "index.jsp";
	}
	
	@PostMapping("/")
	public String createEvent(@Valid @ModelAttribute ("event")Event event, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "index.jsp";
		}
		eventService.createEvent(event, (userService.findUserById((Long)session.getAttribute("userid"))));
		return "redirect:/";
	}
	
	@RequestMapping("/event/{id}")
	public String event(@Valid @ModelAttribute("comment")Comment comment, BindingResult result,@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";	
		}
		if(result.hasErrors()) {
			return "event.jsp";
		}
		model.addAttribute("event", eventService.findEvent(id));
		
		
		eventService.createComment(comment);
		comment.setEvent(eventService.findEvent(id));
		eventService.save(comment);
		return "event.jsp";
	}
	
	@PostMapping("event/{id}")
	public String addCommentToEvent(@RequestParam("comment")String comment, @PathVariable("id")Long id, Model model) {
		Comment comment1 = new Comment();
		comment1.setName(comment);
		 
		Comment cmnt = eventService.createComment(comment1);
		
		eventService.addCommentToEvent(cmnt, eventService.findEvent(id));
		model.addAttribute("allUsers",eventService.allUsers());
		
		
		
		model.addAttribute("allComments", eventService.allComments());
		return "redirect:/event/" + id;
	}
	
	@RequestMapping("/edit/event/{id}")
	public String editEvent(@Valid @ModelAttribute("event")Event event, BindingResult result, @PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";	
		}
		model.addAttribute("event", eventService.findEvent(id)); //grab the event from DB
		return "edit.jsp";
	}
	
	@PostMapping("/edit/event/{id}")
	public String edit(@Valid @ModelAttribute("event")Event event, BindingResult result, @PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";	
		}
		
		eventService.updateEvent(event);
		return "redirect:/event/"+ id;
	}
	
	@RequestMapping("/join/{id}")
	public String join(@PathVariable("id")Long id, Model model, HttpSession session) {
	if(session.getAttribute("userid")==null) {
		return "redirect:/login";	
	}
	User user = userService.findUserById((Long)session.getAttribute("userid"));
	Event event = eventService.findEvent(id);
	user.getEvents().add(event);
	userService.save(user);
	
	
	//List<Event> events = user.getEvents();
	//Event event1 = eventService.findEvent(id);
	//events.add(event);
	//userService.save(user);
	
	
	return "redirect:/";
	}
	
}
