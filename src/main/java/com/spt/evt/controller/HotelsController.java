package com.spt.evt.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spt.evt.Utils.AjaxUtils;
import com.spt.evt.entity.Hotel;
import com.spt.evt.service.BookingService;

@Controller
public class HotelsController {
	private static final Logger logger = LoggerFactory.getLogger(HotelsController.class);
	
	private BookingService bookingService;

	@Inject
	public HotelsController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@ModelAttribute
	public Hotel addHotel(@PathVariable Long id) {
		return (id != null) ? bookingService.findHotelById(id) : null;
	}

	@RequestMapping(value = "/hotels/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id) {
		return "hotels/show";
	}

	@RequestMapping(value = "/hotels/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id) {
		return "hotels/edit";
	}

	@RequestMapping(value = "/hotels/{id}", method = RequestMethod.POST)
	public String edit(Hotel hotel, BindingResult result,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		if (result.hasErrors()) {
			return "hotels/edit";
		}
		bookingService.updateHotel(hotel);
		return (AjaxUtils.isAjaxRequest(requestedWith)) ? "hotels/show" : "redirect:/hotels/" + hotel.getId();
	}

}