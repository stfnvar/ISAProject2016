package com.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.model.Guest;
import com.restaurant.model.Person;
import com.restaurant.service.GuestServiceImpl;
import com.restaurant.service.PersonServiceImpl;


@RestController
@RequestMapping("/account")
public class LoginRegisterController {

	@Autowired
	PersonServiceImpl personServiceImpl;

	@Autowired
	GuestServiceImpl guestServiceImpl;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getUser(Model model, @RequestBody Person person) {

		return null;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> registerGuest(Model model, @RequestBody Guest person) {
		
		if (personServiceImpl.findOneByUsername(person.getUsername()) != null) {
			return null;
		}
		guestServiceImpl.save(person);
		
		return new ResponseEntity<Guest>(person, HttpStatus.CREATED);
	}
}
