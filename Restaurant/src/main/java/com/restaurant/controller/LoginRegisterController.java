package com.restaurant.controller;

import java.util.List;

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
import com.restaurant.model.Offerer;
import com.restaurant.model.Person;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.Worker;
import com.restaurant.service.AdminServiceImpl;
import com.restaurant.service.Friendship;
import com.restaurant.service.GuestServiceImpl;
import com.restaurant.service.OffererServiceImpl;
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
	@Autowired
	OffererServiceImpl offererServiceImpl;
	
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
				
			Offerer offerer = offererServiceImpl.findOne(person.getId());
			
			if(offerer != null){
				req.getSession().setAttribute("guest", person);
				if(offerer.isFirstTime()){
					return new MessageWithObj("offerer", true, offerer);
				}else{
					return new MessageWithObj("offerer", false, offerer);
				}
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
	
	//proverava ko je trenutno u sesiji
	@RequestMapping(value = "/loggedin")
	public MessageWithObj whoIsLogged(HttpServletRequest req) {
		Person p=(Person)req.getSession().getAttribute("guest");
		if(p!=null){
			Person person = personServiceImpl.findOneByEmailAndPassword(p.getEmail().trim(), p.getPassword().trim());
			
			if(person != null){
				
				Worker worker = workerServiceImpl.findOneById(person.getId());
				
				if(worker != null){
					
					req.getSession().setAttribute("guest", person);
					return new MessageWithObj("radnik", true, worker);
				}
				
				Offerer of = offererServiceImpl.findOne(person.getId());
				
				if(of != null){
					if(of.isFirstTime()){
						return new MessageWithObj("offerer", true, of);
					}else{
						return new MessageWithObj("offerer", false, of);
					}
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
	}
		 return new MessageWithObj("Sesija prazna", false, null);
		
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
	public String activateAccount(@PathVariable("email") String email) {
		
		
		Person person = personServiceImpl.findOneByEmail(email);
		
		guestServiceImpl.activateAccount(person.getId());
		
		return "Activation completed!";
	}
	
	//update naloga gosta
	@Transactional
	@RequestMapping(value = "/updateGuest")
	public MessageWithObj activateAccount(@RequestBody Person person, HttpServletRequest req) {
		
		Person per = personServiceImpl.findOneByEmail(person.getEmail());

		guestServiceImpl.updateOneGuest(per.getId(), person.getName(), person.getSurname(),
				person.getEmail(), person.getPassword());
		
		per = personServiceImpl.findOneByEmail(person.getEmail());
		
		req.getSession().removeAttribute("guest");
		req.getSession().setAttribute("guest", per);
		
		
		return new MessageWithObj("Update completed", true, null);
	}
	
	@RequestMapping(value = "/test")
	public MessageWithObj testing( HttpServletRequest req) {
		
		long id = ((Person)req.getSession().getAttribute("guest")).getId();
		List<Friendship> friends;
		
		try {
		friends = guestServiceImpl.getFriends(id);
		}catch(NullPointerException ex){
			return new MessageWithObj("Nema prijatelja", false, null);
		}
	
		return new MessageWithObj("prijatelji ulogovanog", true, friends );
	
		
		
			
	}
	
	@Transactional
	@RequestMapping(value = "/rmfriend/{id}")
	public void removeFriend(@PathVariable("id") String friendId, HttpServletRequest req) {
		
		long id1 = ((Person)req.getSession().getAttribute("guest")).getId();
		long id2 = Long.parseLong(friendId);
		
		 guestServiceImpl.removeOneFriendship(id1, id2);
		 guestServiceImpl.removeOneFriendship(id2, id1);

	}
	//SS
	@RequestMapping(value = "/notfriends")
	public MessageWithObj notFriends( HttpServletRequest req) {
		
		long id = ((Person)req.getSession().getAttribute("guest")).getId();
		return new MessageWithObj("neprijatelji ulogovanog", true, guestServiceImpl.getNotFriends(id) );
	}
	
	@RequestMapping(value = "/search/{op}/{name}/{surname}")
	public MessageWithObj searchResults(@PathVariable("op") String op,@PathVariable("name") String name,
	@PathVariable("surname") String surname, HttpServletRequest req) {
		long current = ((Person)req.getSession().getAttribute("guest")).getId();
		if(op.equals("or")){
			if(name.equals("!x!")){
				return new MessageWithObj("Po imenu OR" ,true ,guestServiceImpl.findGuestsBySurname(surname, current));
			}else
				return new MessageWithObj("Po prezimenu OR" ,true ,guestServiceImpl.findGuestsByName(name,current));
		}else{
			return new MessageWithObj("And pretraga", true, guestServiceImpl.findGuestsByNameAndSurname(name, surname,current));
		}

	}
	
	@RequestMapping(value = "/searchFriends/{op}/{name}/{surname}")
	public MessageWithObj searchFriends(@PathVariable("op") String op,@PathVariable("name") String name,
	@PathVariable("surname") String surname, HttpServletRequest req) {
		long current = ((Person)req.getSession().getAttribute("guest")).getId();
		if(op.equals("or")){
			if(name.equals("!x!")){
				return new MessageWithObj("Po imenu OR" ,true ,guestServiceImpl.findFriendsBySurname(surname, current));
			}else
				return new MessageWithObj("Po prezimenu OR" ,true ,guestServiceImpl.findFriendsByName(name,current));
		}else{
			return new MessageWithObj("And pretraga", true, guestServiceImpl.findFriendsByNameAndSurname(name, surname,current));
		}

	}
	
	
	@Transactional
	@RequestMapping(value = "/addfriend/{id}", method = RequestMethod.POST)
	public void addFriend(@PathVariable("id") String id, HttpServletRequest req){
	
		long id1 = ((Person)req.getSession().getAttribute("guest")).getId();
		long id2 = Long.parseLong(id);
		
		guestServiceImpl.becomeFriends(id1, id2);
		guestServiceImpl.becomeFriends(id2, id1);
	}
}
