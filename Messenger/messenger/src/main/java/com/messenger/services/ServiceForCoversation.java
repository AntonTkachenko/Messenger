/**
 * 
 */
package com.messenger.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.entities.Conversation;
import com.messenger.reposiroties.ConversationRepository;
import com.messenger.services.interfaces.ConversationServise;

/**
 * @author ANTON_WORK
 *
 */
@Service
public class ServiceForCoversation implements ConversationServise {

	/**
	 * 
	 */
	@Autowired
	ConversationRepository conversationRepository;

	public ServiceForCoversation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ConversationRepository getConversationRepository() {
		// TODO Auto-generated method stub
		return conversationRepository;
	}

	@Override
	public Conversation findById(Long conversationId) {
		Optional<Conversation> optional = conversationRepository.findById(conversationId);
		return optional.isPresent() ? optional.get() : null;
	}

}
