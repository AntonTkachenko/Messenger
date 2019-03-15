/**
 * 
 */
package com.messenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.messenger.entities.User;
import com.messenger.services.interfaces.UserService;

/**
 * @author ANTON_WORK
 *
 */
@ControllerAdvice
public class UserControllerAdvice {

	/**
	 * 
	 */

	@Autowired
	private UserService userService;

	public UserControllerAdvice() {
	}

	@ModelAttribute
	public void addMainUserModel(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		if (authentication != null) {
			user = userService.findUserByPhoneNumber(authentication.getName());
		}
		model.addAttribute("user", user != null ? user : new User());
	}

}
