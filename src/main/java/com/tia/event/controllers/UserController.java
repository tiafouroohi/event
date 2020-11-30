package com.tia.event.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia.event.models.User;
import com.tia.event.services.UserService;
import com.tia.event.validators.UserValidator;


@Controller
public class UserController {

    
   @Autowired
   private UserService userService;
    
    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
 return "registration.jsp";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	
    	if(result.hasErrors()) {
    		return "registration.jsp";
    	}
    	User newUser = userService.registerUser(user);
    	session.setAttribute("userid", newUser.getId());
    	return "redirect:/";
      
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        if(userService.authenticateUser(email, password)) {
        	User thisUser = userService.findByEmail(email);
        	session.setAttribute("userid", thisUser.getId());
        	return "redirect:/";
        }
        model.addAttribute("error", "Invalid login");
        return "login.jsp";
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        Long userId=(Long) session.getAttribute("userid");
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);
        return "home.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/login";
    }
}