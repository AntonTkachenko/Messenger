/**
 * 
 */
package com.messenger.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.messenger.utils.types.MessageType;

/**
 * @author ANTON_WORK
 *
 */
@Entity
@Table(name = "MESSENGES")
public class Message extends BasicFields implements Serializable, Comparable<Message> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656621182054956789L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SENDER_ID", nullable = false)
	private User user;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CONVERSATION_ID", nullable = false)
	private Conversation conversation;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "MEESAGE_TYPE")
	private MessageType type;

	@Column(name = "text", nullable = false)
	private String text;

	public Message() {
		super();
		type = MessageType.TEXT;
		text = "";
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	public final User getUser() {
		return user;
	}

	public final Conversation getConversation() {
		return conversation;
	}

	public final MessageType getType() {
		return type;
	}

	public final String getText() {
		return text;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public final void setType(MessageType type) {
		this.type = type;
	}

	public final void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(Message o) {

		return this.createdDateTime.compareTo(o.createdDateTime);
	}

}
