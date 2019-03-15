/**
 * 
 */
package com.messenger.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.messenger.entities.User;
import com.messenger.services.interfaces.UserService;
import com.messenger.utils.classes.MessengerRegex;

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

	}


	@PostMapping(value = "find")
	public ModelAndView setCurrentConversation(ModelMap model, String text) {
		List<User> users = new ArrayList<>();
		if (text.matches(MessengerRegex.PHONE_REGEX)) {
			users.add(userService.findUserByPhoneNumber(text));
		} else if (text.matches(MessengerRegex.PHONE_REGEX)) {
			users.add(userService.findByEmail(text));
		} else {
			users = userService.findByName(text, 0);
		}
		if (users.isEmpty()) {
			model.addAttribute("error", "User dont find!");
		} else {
			model.addAttribute("error", null);
		}

		model.addAttribute("users", users);

		return new ModelAndView("users", model);
	}

}
