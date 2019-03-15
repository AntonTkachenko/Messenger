package com.messenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messenger.reposiroties.MessageRepository;
import com.messenger.services.interfaces.MessageService;

@Service
public class ServiceForMessage implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public ServiceForMessage() {
	}

	@Override
	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

}
