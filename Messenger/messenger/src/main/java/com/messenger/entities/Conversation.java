/**
 * 
 */
package com.messenger.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ANTON_WORK
 *
 */
@Entity
@Table(name = "CONVERSATIONS")
public class Conversation extends BasicFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 736999339329279638L;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "FOTO_LINK", nullable = false)
	private String fotoLink;

	@ManyToMany(mappedBy = "conversations")
	private Set<User> users;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conversation",fetch = FetchType.EAGER)
	private List<Message> messages;

	public Conversation() {
		super();
		this.users = new HashSet();
		this.messages = new ArrayList();
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	public final String getTitle() {
		return title;
	}

	public final String getFotoLink() {
		return fotoLink;
	}

	public final Set<User> getUsers() {
		return users;
	}

	public final List<Message> getMessages() {
		return messages;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final void setFotoLink(String fotoLink) {
		this.fotoLink = fotoLink;
	}

	public final void setUsers(Set<User> users) {
		this.users = users;
	}

	public final void setMessenges(List<Message> messenges) {
		this.messages = messenges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fotoLink == null) ? 0 : fotoLink.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversation other = (Conversation) obj;
		if (fotoLink == null) {
			if (other.fotoLink != null)
				return false;
		} else if (!fotoLink.equals(other.fotoLink))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Conversation [title=" + title + ", toString()=" + super.toString() + "]";
	}

}
