package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.EventEntity;
import tn.esprit.spring.entity.Rating;
import tn.esprit.spring.interfaces.IRatingEventService;
import tn.esprit.spring.repository.EventRepository;
@RestController
@CrossOrigin("*")
@RequestMapping("/rating")
public class RatingEventController {
	@Autowired
	IRatingEventService ratEventServ;
	@Autowired
	EventRepository eventRepository;
	
	
	/*@PostMapping("/AddRating/{idEvent}/{valueRating}")
	public String addRatingEvent(@PathVariable("idEvent")int idEvent ,@PathVariable("valueRating") int ratingValue) 
	{
		System.out.println("---------------------"+idEvent+"--------"+ratingValue);
		return ratEventServ.addRatingEvent(idEvent, ratingValue);
	}*/
	@PostMapping("/AddRating/{idEvent}/{valueRating}")
	public String addRatingEvent(@PathVariable("idEvent")Long idEvent ,@PathVariable("valueRating") int ratingValue) 
	{float x=ratingValue;
		EventEntity event = eventRepository.findById(idEvent).get();
		for(Rating y : event.getRating()){
			x=x+y.getRatingValue();
		}
		event.setRate(x/(event.getRating().size()+1));
		event = eventRepository.save(event);
		System.out.println("---------------------"+idEvent+"--------"+ratingValue);
		return ratEventServ.addRatingEvent(idEvent, ratingValue);
	}
	@GetMapping("/rating/{idEvent}")
	public float getValueRatingByEventAndUser(@PathVariable("idEvent")Long idEvent) {
		return ratEventServ.getValueRatingByEventAndUser(idEvent);
	}
	@PutMapping("/modifyrating")
	@ResponseBody
	public String updateRatingEvent(@RequestBody Rating ratEvent) {
		return ratEventServ.updateRatingEvent(ratEvent);
	}
}
