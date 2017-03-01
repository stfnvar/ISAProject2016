package com.restaurant.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.model.Bartender;
import com.restaurant.model.Cook;
import com.restaurant.model.Person;
import com.restaurant.model.RestaurantManager;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.service.BartenderServiceImpl;
import com.restaurant.service.PersonServiceImpl;

@RestController
@RequestMapping("/bartender")
public class BartenderController {
	
	@Autowired
	BartenderServiceImpl bartenderServiceImpl;
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	public static Date parseDate(String date) {
		try {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			return f.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getOnDutyDay", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<WorkingSchedule>> getOnDutyDay(@RequestBody String s, HttpServletRequest req) {
		
		Bartender b = (Bartender) req.getSession().getAttribute("guest");
		
		s = s.replace("\"", "");
		Date date = parseDate(s);
		if (req.getSession().getAttribute("guest") instanceof Bartender) {
			return new ResponseEntity<Set<WorkingSchedule>>(bartenderServiceImpl.getOnDutyDay(date, b.getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<WorkingSchedule>>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/checkFirstTime/{id}", method = RequestMethod.GET)
	public Bartender checkFirstTime(@PathVariable long id){
		Bartender b = bartenderServiceImpl.findOneById(id);
		System.out.println(id);
		if(b == null){
			System.out.println("yoy");
			
		}
		if(b.getFirstTimeChangePW() == 0)
			return b;
		else
			return null;
	}
	
	@Transactional
	@RequestMapping(value = "/ftChangePW/{id}/{pw}", method = RequestMethod.POST)
	public Bartender ftChangePW(@PathVariable("id") long id, @PathVariable("pw") String pw, HttpServletRequest req){
		Bartender b = bartenderServiceImpl.findOneById(id);

		bartenderServiceImpl.changePW(b.getId(), pw);
		bartenderServiceImpl.changeFT(b.getId());
		
		//proveri
		Person per = personServiceImpl.findOneByEmailAndPassword(b.getEmail(), pw);
		System.out.println("ODAODSKADOASDOKDAO");
		System.out.println(pw);
		System.out.println(b.getPassword());
		System.out.println(per.getPassword());
		
		req.getSession().removeAttribute("guest");
		//req.getSession().setAttribute("guest", per);
		//
		
		return b;
	}
	
	
	@Transactional
	@RequestMapping(value="/updateBartender", method = RequestMethod.POST)
	public MessageWithObj updateBartender(@RequestBody Person person, HttpServletRequest req){
		
		Person per = personServiceImpl.findOneByEmail(person.getEmail());
		bartenderServiceImpl.updateOneBartender(per.getId(), person.getName(), person.getSurname(),
				person.getEmail(), person.getPassword());
		
		per = personServiceImpl.findOneByEmail(person.getEmail());
		
		req.getSession().removeAttribute("guest");
		req.getSession().setAttribute("guest", per);
		
		System.out.println("APDEJTSANKER");
		return new MessageWithObj("Update completed", true, null);
	}

}
