package com.spt.evt.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spt.evt.controller.HotelSearchController;
import com.spt.evt.dao.BookingDao;
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

@Repository
public class JpaBookingDaoImpl implements BookingDao {
	private static final Logger logger = LoggerFactory.getLogger(JpaBookingDaoImpl.class);
	
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Booking> findBookings(String username) {
		if (username != null) {
			return em
					.createQuery(
							"select b from Booking b where b.user.username = :username order by b.checkinDate")
					.setParameter("username", username).getResultList();
		} else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Hotel> findHotels(SearchCriteria criteria) {
		String pattern = getSearchPattern(criteria);
		return em.createQuery(
				"select h from Hotel h where lower(h.name) like " + pattern
						+ " or lower(h.city) like " + pattern
						+ " or lower(h.zip) like " + pattern
						+ " or lower(h.address) like " + pattern)
				.setMaxResults(criteria.getPageSize()).setFirstResult(
						criteria.getPage() * criteria.getPageSize())
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Hotel findHotelById(Long id) {
		return em.find(Hotel.class, id);
	}

	@Transactional
	public void updateHotel(Hotel hotel) {
		em.merge(hotel);
	}

	// helpers

	private String getSearchPattern(SearchCriteria criteria) {
		if (StringUtils.hasText(criteria.getSearchString())) {
			return "'%"
					+ criteria.getSearchString().toLowerCase()
							.replace('*', '%') + "%'";
		} else {
			return "'%'";
		}
	}

	private User findUser(String username) {
		return (User) em.createQuery(
				"select u from User u where u.username = :username")
				.setParameter("username", username).getSingleResult();
	}

}