package com.restaurant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.LogParams;
import com.restaurant.miscel.MessageWithObj;
import com.restaurant.miscel.SendMail;
import com.restaurant.model.Administrator;
import com.restaurant.model.Guest;
import com.restaurant.model.Person;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.Worker;
import com.restaurant.service.AdminServiceImpl;
import com.restaurant.service.GuestServiceImpl;
import com.restaurant.service.PersonServiceImpl;
import com.restaurant.service.RestaurantManagerServiceImpl;
import com.restaurant.service.WorkerServiceImpl;


@RestController
@RequestMapping("/account")
public class LoginRegisterController {

	@Autowired
	PersonServiceImpl personServiceImpl;
	@Autowired
	WorkerServiceImpl workerServiceImpl;
	@Autowired
	AdminServiceImpl adminServiceImpl;
	@Autowired
	RestaurantManagerServiceImpl restManagerServiceImpl;
	

	@Autowired
	GuestServiceImpl guestServiceImpl;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj getUser(Model model,
			@RequestBody LogParams params, HttpServletRequest req) {

		Person person = personServiceImpl.findOneByEmailAndPassword(params.getEmail().trim(), params.getPassword().trim());
		
		
		if(person != null){
			
			Worker worker = workerServiceImpl.findOneById(person.getId());
			
			if(worker != null){
				System.out.println("NASAO");
				req.getSession().setAttribute("guest", person);
				return new MessageWithObj("radnik", true, worker);
			}
				
		
			
			Guest guest = guestServiceImpl.findOneById(person.getId());
			
			//dodatna provera za goste
			if(guest != null){
				if(guest.getActive() == 1){
					req.getSession().setAttribute("guest", person);
					return new MessageWithObj("gost", true, person);
				}else
					return new MessageWithObj("gost", false, person);
				
			//znaci da nije gost, mogucnosti jos admin i restmanager	
			}else if(guest == null) {
				
				Administrator admin = adminServiceImpl.findOneById(person.getId());
				
				if(admin!=null){
					
					req.getSession().setAttribute("guest", person);
					return new MessageWithObj("admin", true, person);
				}else{
					RestaurantManager rm = restManagerServiceImpl.findOneById(person.getId());
					req.getSession().setAttribute("guest", person);
					return new MessageWithObj("rmanager", true, person);
				}
				
				
			}
		

		}
	return new MessageWithObj("error",false , null);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj registerGuest(Model model, @RequestBody Guest person) {
		
		if (personServiceImpl.findOneByEmail(person.getEmail()) != null) {
			return new MessageWithObj("Postoji", false, person);
		}
		
		guestServiceImpl.save(person);
		
		SendMail sm = new SendMail("stefandvlp@gmail.com", "http://localhost:8080/account/activate/"+person.getEmail()+"/");
		
		return new MessageWithObj("Uspesno kreiran", true, person);
	}
	//proverava ko je trenutno u sesiji
	@RequestMapping(value = "/loggedin")
	public MessageWithObj whoIsLogged(HttpServletRequest req) {
		
		if(req.getSession().getAttribute("guest")!=null){
			return new MessageWithObj("U sesiji", true, (Person)req.getSession().getAttribute("guest"));
			
		}else return new MessageWithObj("Sesija prazna", false, null);
	}
	
	@RequestMapping(value = "/logout")
	public MessageWithObj logout(HttpServletRequest req) {
		if(req.getSession().getAttribute("guest")!=null){
			req.getSession().invalidate();
			return new MessageWithObj("Izbacen iz sesije", true,null);
			
		}else
			return new MessageWithObj("Doslo je do greske", false, null);
	}
	//aktiviranje naloga
	@Transactional
	@RequestMapping(value = "/activate/{email}")
	public String activateAccount(@PathVariable String email) {
		
		
		Person person = personServiceImpl.findOneByEmail(email);
		
		guestServiceImpl.activateAccount(person.getId());
		
		return "Activation completed!";
	}
	
}
