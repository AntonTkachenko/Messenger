/**
 * 
 */
package com.messenger.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.messenger.entities.Conversation;
import com.messenger.entities.User;
import com.messenger.services.interfaces.ConversationServise;
import com.messenger.services.interfaces.UserService;

/**
 * @author Anton
 *
 */

@Controller
@Scope("request")
public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConversationServise conversationService;

	@RequestMapping(value = "/")
	public String redirectHome() {
		return "redirect:/user/authentication";
	}

	@RequestMapping(value = "/main")
	public ModelAndView home(ModelMap model) {
		User user = userService.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user == null) {
			return new ModelAndView("redirect:/user/authentication", model);
		}
		model.addAttribute("conversations", user.getConversations());
		return new ModelAndView("main", model);
	}

	@RequestMapping(value = "currentConversation", method = { RequestMethod.POST })
	public ModelAndView setCurrentConversation(ModelMap model, Long conversationId) {
		User user = userService.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
		for (Conversation conversation : user.getConversations()) {
			if (conversation.getId() == conversationId) {
				Collections.sort(conversation.getMessages(), (a, b) -> (a.compareTo(b)));
				model.addAttribute("currentConversation", conversation);
				break;
			}
		}
		model.addAttribute("conversations", user.getConversations());
		model.addAttribute("currentConversation", user.getConversations());
		return new ModelAndView("main", model);
	}
	
	@RequestMapping(value = "currentConversation", method = { RequestMethod.POST })
	public ModelAndView createConversation(ModelMap model, Long userId) {
		User userCreator = userService.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
		User user = userService.getUserRepository().getOne(userId);
		Conversation conversation = new Conversation();
		Set<User> users = new HashSet<>();
		users.add(userCreator);
		users.add(user);
		conversation.setUsers(users);
		conversation.setTitle(user.getPhone());
		conversation = conversationService.getConversationRepository().save(conversation);
		model.addAttribute("currentConversation", conversation);
		model.addAttribute("conversations", user.getConversations());
		model.addAttribute("currentConversation", user.getConversations());
		return new ModelAndView("main", model);
	}
}
