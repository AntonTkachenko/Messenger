/**
 * 
 */
package com.messenger.reposiroties;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.entities.Conversation;
import com.messenger.entities.User;

/**
 * @author ANTON_WORK
 *
 */
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	public Set<Conversation> findByUsers(List<User> users);

}
