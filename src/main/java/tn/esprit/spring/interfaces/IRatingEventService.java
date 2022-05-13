package tn.esprit.spring.interfaces;

import tn.esprit.spring.entity.Rating;

public interface IRatingEventService {
	public String addRatingEvent(Long idEvent,int ratingValue);
	public String updateRatingEvent(Rating ratEvent);
	public float getAvgRat();
	public float getValueRatingByEventAndUser(Long idEvent);
}
