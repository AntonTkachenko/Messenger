/**
 * 
 */
package com.messenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.messenger.services.interfaces.UserService;

/**
 * @author ANTON_WORK
 *
 */
@Controller
public class FindController {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;

	public FindController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(value = "find")
	public ModelAndView setCurrentConversation(ModelMap model, String phoneNumber) {
		model.addAttribute("user", userService.findUserByPhoneNumber(phoneNumber));
		return new ModelAndView("users", model);
	}

}
