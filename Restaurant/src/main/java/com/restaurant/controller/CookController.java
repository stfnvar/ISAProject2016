package com.restaurant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.model.Cook;
import com.restaurant.model.Person;
import com.restaurant.model.Waiter;
import com.restaurant.service.CookServiceImpl;
import com.restaurant.service.PersonServiceImpl;

@RestController
@RequestMapping("/cook")
public class CookController {
	
	@Autowired
	CookServiceImpl cookServiceImpl;
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@RequestMapping(value = "/checkFirstTime/{id}", method = RequestMethod.GET)
	public Cook checkFirstTime(@PathVariable long id){
		Cook c = cookServiceImpl.findOneById(id);
		if(c.getFirstTimeChangePW() == 0)
			return c;
		else
			return null;
	}
	
	@Transactional
	@RequestMapping(value = "/ftChangePW/{id}/{pw}", method = RequestMethod.POST)
	public Cook ftChangePW(@PathVariable("id") long id, @PathVariable("pw") String pw, HttpServletRequest req){
		System.out.println("USPEO?");
		Cook c = cookServiceImpl.findOneById(id);
		
		cookServiceImpl.changePW(c.getId(), pw);
		cookServiceImpl.changeFT(c.getId());
		
		Person per = personServiceImpl.findOneByEmailAndPassword(c.getEmail(), pw);

		req.getSession().removeAttribute("guest");
		//req.getSession().setAttribute("guest", per);
		
		return c;
	}
	
	
	@Transactional
	@RequestMapping(value="/updateCook", method = RequestMethod.POST)
	public MessageWithObj updateCook(@RequestBody Person person, HttpServletRequest req){
		
		Person per = personServiceImpl.findOneByEmail(person.getEmail());

		cookServiceImpl.updateOneCook(per.getId(), person.getName(), person.getSurname(),
				person.getEmail(), person.getPassword());
		
		per = personServiceImpl.findOneByEmail(person.getEmail());
		
		req.getSession().removeAttribute("guest");
		req.getSession().setAttribute("guest", per);
		
		System.out.println("APDEJTKUVAR");
		return new MessageWithObj("Update completed", true, null);
	}
	

}
