/**
 * 
 */
package com.messenger.reposiroties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.entities.Conversation;
import com.messenger.entities.Message;

/**
 * @author ANTON_WORK
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	public List<Message> findByConversation(Conversation conversation);

}
