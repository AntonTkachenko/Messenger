/**
 * 
 */
package com.messenger.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.messenger.entities.Conversation;
import com.messenger.entities.Message;
import com.messenger.entities.Role;
import com.messenger.entities.User;
import com.messenger.reposiroties.ConversationRepository;
import com.messenger.reposiroties.MessageRepository;
import com.messenger.reposiroties.RoleRepository;
import com.messenger.reposiroties.UserRepository;
import com.messenger.services.interfaces.UserService;

/**
 * @author devil
 *
 */
@Service
public class ServiceForUser implements UserService {

	/**
	 * 
	 */

	private UserRepository userRepository;
	private ConversationRepository conversationRepository;
	private MessageRepository messageRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	@Autowired
	public ServiceForUser(UserRepository userRepository, ConversationRepository conversationRepository,
			MessageRepository messageRepository,BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.conversationRepository = conversationRepository;
		this.messageRepository = messageRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public User findUserByPhoneNumber(String phoneNumber) {

		return userRepository.findByPhone(phoneNumber);
	}

	@Override
	public Set<Conversation> findUserConversations(List<User> users) {
		
		return conversationRepository.findByUsers(users);
	}

	@Override
	public List<Message> findUserMessages(Conversation conversation) {
		
		return messageRepository.findByConversation(conversation);
	}

	@Override
	public UserRepository getUserRepository() {
		
		return userRepository;
	}

	@Override
	public boolean createAndSaveUser(User user) {
		Optional<Role> roleOptional = roleRepository.findById(1L);
		Role role;
		Set<Role> roles = new HashSet<>();
		if(!roleOptional.isPresent()) {
			role = new Role();
			role.setName("USER");
			roles.add(roleRepository.save(role));
		} else {
			role = roleOptional.get();
			roles.add(role);
		}
		user.setRole(roles);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user) != null;
	}

	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
