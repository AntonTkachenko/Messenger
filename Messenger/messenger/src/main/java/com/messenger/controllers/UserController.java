/**
 * 
 */
package com.messenger.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.messenger.entities.Conversation;
import com.messenger.entities.Message;
import com.messenger.entities.User;
import com.messenger.services.interfaces.ConversationServise;
import com.messenger.services.interfaces.MessageService;
import com.messenger.services.interfaces.SecurityService;
import com.messenger.services.interfaces.UserService;
import com.messenger.utils.types.MessageType;
import com.messenger.validation.UserValidator;

/**
 * @author ANTON_WORK
 *
 */
@Controller
public class UserController {

	/**
	 * 
	 */
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ConversationServise conversationServise;

	@Autowired
	private MessageService messageService;

	public UserController() {
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

	@RequestMapping(value = "user/logout", method = { RequestMethod.GET })
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		model.addAttribute("userForm", new User());
		return "authentication";
	}

	@PostMapping(value = { "user/createConversation" })
	public ModelAndView createConversation(HttpServletRequest request, ModelMap model, Long userId) {
		User mainUser = userService
				.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
		User user = userService.findUserById(userId);
		Conversation currentConversation = new Conversation();
		currentConversation.setTitle(mainUser.getFirstName() + "/" + user.getFirstName());
		currentConversation.getUsers().add(mainUser);
		currentConversation.getUsers().add(user);
		currentConversation = conversationServise.getConversationRepository().save(currentConversation);
		mainUser.getConversations().add(currentConversation);
		user.getConversations().add(currentConversation);
		userService.getUserRepository().saveAndFlush(mainUser);
		userService.getUserRepository().saveAndFlush(user);
		model.addAttribute("currentConversation", currentConversation);
		return new ModelAndView("main", model);
	}

	@PostMapping(value = { "user/sendMessage" })
	public ModelAndView sendMessage(ModelMap model, Long currentConversationId, String messageText) {
		User mainUser = userService
				.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
		Conversation currentConversation = conversationServise.findById(currentConversationId);
		Message message = new Message();
		message.setText(messageText);
		message.setType(MessageType.TEXT);
		message.setConversation(currentConversation);
		message.setUser(mainUser);
		message = messageService.getMessageRepository().save(message);
		model.addAttribute("currentConversation", currentConversation);
		return new ModelAndView("main", model);
	}

}
