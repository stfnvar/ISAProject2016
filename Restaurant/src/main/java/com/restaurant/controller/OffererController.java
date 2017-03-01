package com.restaurant.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.miscel.MessageWithObj;
import com.restaurant.model.Announcement;
import com.restaurant.model.Offer;
import com.restaurant.model.Offerer;
import com.restaurant.model.Person;
import com.restaurant.model.RestaurantManager;
import com.restaurant.service.AnnouncementServiceImpl;
import com.restaurant.service.OfferServiceImpl;
import com.restaurant.service.OffererServiceImpl;
import com.restaurant.service.PersonServiceImpl;

@RestController
@Controller
@RequestMapping("/offerer")
public class OffererController {

	@Autowired
	OffererServiceImpl offererServiceImpl;

	@Autowired
	OfferServiceImpl offerServiceImpl;

	@Autowired
	PersonServiceImpl personServiceImpl;

	@Autowired
	AnnouncementServiceImpl announcementServiceImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/getOffersForOfferer")
	public ResponseEntity<List<Offer>> getOffersForOfferer(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof Offerer) {
			Offerer rm = (Offerer) req.getSession().getAttribute("guest");
			return new ResponseEntity<List<Offer>>(offerServiceImpl.getAllOffersForOfferer(rm.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<List<Offer>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/applyProfileChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<Person> applyProfileChanges(@RequestBody Person restaurantManager, HttpServletRequest req) {
		Person person = offererServiceImpl.update(restaurantManager.getName(), restaurantManager.getSurname(),
				restaurantManager.getId());
		req.getSession().setAttribute("guest", person);
		return new ResponseEntity<Person>(personServiceImpl.findOne(restaurantManager.getId()), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<String> changePassword(@RequestBody ArrayList<String> params, HttpServletRequest req) {
		Person restaurantManager = (Person) req.getSession().getAttribute("guest");
		if (params.get(1) == null || params.get(2) == null) {
			return new ResponseEntity<String>("{\"status\": \"minLengthError\"}", HttpStatus.OK);
		}
		if (!params.get(1).equals(params.get(2))) {
			return new ResponseEntity<String>("{\"status\": \"notMatch\"}", HttpStatus.OK);
		}
		if (!restaurantManager.getPassword().equals(params.get(0))) {
			return new ResponseEntity<String>("{\"status\" :\"noAccess\"}", HttpStatus.OK);
		}
		offererServiceImpl.changePassword(restaurantManager.getId(), params.get(1));
		return new ResponseEntity<String>("{\"status\" : \"changed\"}", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAnnoun")
	public ResponseEntity<List<Announcement>> getOfferers(HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof Offerer) {
			return new ResponseEntity<List<Announcement>>(announcementServiceImpl.getAllAnnouncements(), HttpStatus.OK);
		}
		return new ResponseEntity<List<Announcement>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changeFirstPass", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<String> changeFirstPass(@RequestBody ArrayList<String> params, HttpServletRequest req) {
		Person restaurantManager = (Person) req.getSession().getAttribute("guest");
		if (!params.get(1).equals(params.get(2))) {
			return new ResponseEntity<String>("{\"status\": \"notMatch\"}", HttpStatus.OK);
		}
		offererServiceImpl.changePassword(restaurantManager.getId(), params.get(1));
		offererServiceImpl.changeFirstTime(false, restaurantManager.getId());
		return new ResponseEntity<String>("{\"status\" : \"changed\"}", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addOffer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public MessageWithObj registerOfferer(@RequestBody Offer of, HttpServletRequest req) {
		if (req.getSession().getAttribute("guest") instanceof Offerer) {
			/*
			Announcement announ = announcementServiceImpl.findOne(of.getAnnouncement().getId());
			if (announ.isAccepted()) {
				return new MessageWithObj("BAD2", false, null);
			}

			Offer k = offerServiceImpl.save(of);
			return new MessageWithObj("OK", true, k);
			*/
		}
		return new MessageWithObj("BAD3", false, null);
	}
}