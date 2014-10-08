package com.spt.evt.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spt.evt.dao.BookingDao;
import com.spt.evt.dao.impl.JpaBookingDaoImpl;
import com.spt.evt.dto.SearchCriteria;
import com.spt.evt.entity.Booking;
import com.spt.evt.entity.Hotel;
import com.spt.evt.entity.User;
import com.spt.evt.service.BookingService;

/**
 * A JPA-based implementation of the Booking Service. Delegates to a JPA entity
 * manager to issue data access calls against the backing repository. The
 * EntityManager reference is provided by the managing container (Spring)
 * automatically.
 */
@Service("bookingService")
public class JpaBookingService implements BookingService {
	private static final Logger logger = LoggerFactory.getLogger(JpaBookingService.class);
	
	@Autowired
	private BookingDao bookingDao;

	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}

	public BookingDao getBookingDao() {
		return bookingDao;
	}

	public List<Booking> findBookings(String username) {
		if (username != null) {
			return getBookingDao().findBookings(username);
		} else {
			return null;
		}
	}

	public List<Hotel> findHotels(SearchCriteria criteria) {
		return getBookingDao().findHotels(criteria);
	}

	public Hotel findHotelById(Long id) {
		return getBookingDao().findHotelById(id);
	}

	@Override
	public void updateHotel(Hotel hotel) {
		getBookingDao().updateHotel(hotel);		
	}

}