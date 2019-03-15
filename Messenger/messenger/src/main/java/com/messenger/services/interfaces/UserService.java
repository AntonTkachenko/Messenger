/**
 * 
 */
package com.messenger.services.interfaces;

import java.util.List;
import java.util.Set;

import com.messenger.entities.Conversation;
import com.messenger.entities.Message;
import com.messenger.entities.User;
import com.messenger.reposiroties.UserRepository;

/**
 * @author devil
 *
 */
public interface UserService {

	public User findUserByPhoneNumber(String phoneNumber);
	
	public User findUserById(Long id);

	public Set<Conversation> findUserConversations(List<User> users);

	public List<Message> findUserMessages(Conversation conversation);
	
	public UserRepository getUserRepository();

	public User findByEmail(String email);
	
	public List<User> findByName(String name, int pageNumber);

	boolean createAndSaveUser(User user);
}
