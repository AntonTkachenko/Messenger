/**
 * 
 */
package com.messenger.controllers;

import java.util.Collections;
import java.util.HashSet;
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
@Scope("session")
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

		Conversation currentConversation = null;
		for (Conversation conversation : user.getConversations()) {
			currentConversation = conversation;
			break;
		}

		model.addAttribute("currentConversation",
				currentConversation != null ? currentConversation : new Conversation());
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
		model.addAttribute("user", user);
		return new ModelAndView("main", model);
	}

}
