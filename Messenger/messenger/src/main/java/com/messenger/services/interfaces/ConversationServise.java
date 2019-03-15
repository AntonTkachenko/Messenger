/**
 * 
 */
package com.messenger.services.interfaces;

import com.messenger.entities.Conversation;
import com.messenger.reposiroties.ConversationRepository;

/**
 * @author ANTON_WORK
 *
 */
public interface ConversationServise {
	
	public ConversationRepository getConversationRepository();
	
	public Conversation findById(Long conversationId);

}
