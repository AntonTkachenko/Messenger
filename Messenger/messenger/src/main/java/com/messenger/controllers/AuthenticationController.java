/**
 * 
 */
package com.messenger.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.messenger.entities.User;
import com.messenger.services.interfaces.SecurityService;
import com.messenger.services.interfaces.UserService;
import com.messenger.validation.UserValidator;

/**
 * @author ANTON_WORK
 *
 */
@Controller
public class AuthenticationController {

	/**
	 * 
	 */
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	public AuthenticationController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "user/authentication", method = { RequestMethod.GET })
	public String registry(Model model) {
		model.addAttribute("userForm", new User());
		return "authentication";
	}

	@RequestMapping(value = "user/authentication", method = { RequestMethod.POST })
	public String signIn(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		if (userForm.getConfirmPassword() == null) {
			userForm.setConfirmPassword(userForm.getPassword());
		}
		if (userService.findUserByPhoneNumber(userForm.getPhone()) == null) {
			userValidator.validate(userForm, bindingResult);
			if (bindingResult.hasErrors()) {
				model.addAttribute("userForm", userForm);
				return "authentication";
			}
			userService.createAndSaveUser(userForm);
		}
		securityService.autoLogin(userForm.getPhone(), userForm.getConfirmPassword());
		return "redirect:/main";
	}

}
