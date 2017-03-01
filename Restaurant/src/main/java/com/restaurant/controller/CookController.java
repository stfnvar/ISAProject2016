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
import com.restaurant.model.Cook;
import com.restaurant.model.Person;
import com.restaurant.model.Waiter;
import com.restaurant.model.WorkingSchedule;
import com.restaurant.service.CookServiceImpl;
import com.restaurant.service.PersonServiceImpl;

@RestController
@RequestMapping("/cook")
public class CookController {
	
	@Autowired
	CookServiceImpl cookServiceImpl;
	
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
		
		Cook w = (Cook) req.getSession().getAttribute("guest");
		
		s = s.replace("\"", "");
		Date date = parseDate(s);
		if (req.getSession().getAttribute("guest") instanceof Cook) {
			return new ResponseEntity<Set<WorkingSchedule>>(cookServiceImpl.getOnDutyDay(date, w.getId()),
					HttpStatus.OK);
		}
		return new ResponseEntity<Set<WorkingSchedule>>(HttpStatus.UNAUTHORIZED);
	}
	
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
