/**
 * 
 */
package com.messenger.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author devil
 *
 */

@Entity
@Table(name = "USERS")
public class User extends BasicFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4934080399783056182L;
	
	@NotNull
	@Column(name = "USER_PHONE", unique = true, nullable = false)
	private String phone;

	@Column(name = "USER_EMAIL")
	private String email;
	
	@NotNull
	@Column(name = "USER_PASSWORD", nullable = false, unique = true)
	private String password;
	
	@Transient
	private String confirmPassword;

	@Column(name = "USER_FIRST_NAME", length = 55)
	private String firstName;

	@Column(name = "USER_LAST_NAME", length = 55)
	private String lastName;

	@Column(name = "USER_MIDLE_NAME", length = 55)
	private String midleName;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@Column(name = "IS_BLOCKED")
	private Boolean isBlocked;
	
	@ManyToMany
	@JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_CONVERSATIONS", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CONVERSATION_ID") })
	private Set<Conversation> conversations;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Message> messages;

	public User() {
		super();
		this.conversations = new HashSet();
	}
	
	

	public User(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
		this.conversations = new HashSet();
	}



	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	public final String getPhone() {
		return phone;
	}

	public final String getEmail() {
		return email;
	}

	public final String getPassword() {
		return password;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final String getMidleName() {
		return midleName;
	}

	public final Boolean getIsActive() {
		return isActive;
	}

	public final Boolean getIsBlocked() {
		return isBlocked;
	}

	public final Set<Conversation> getConversations() {
		return conversations;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final void setMidleName(String midleName) {
		this.midleName = midleName;
	}

	public final void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public final void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public final void setConversations(Set<Conversation> conversations) {
		this.conversations = conversations;
	}

	public final List<Message> getMessages() {
		return messages;
	}

	public final void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public final String getConfirmPassword() {
		return confirmPassword;
	}

	public final void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public final Set<Role> getRole() {
		return role;
	}

	public final void setRole(Set<Role> role) {
		this.role = role;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isBlocked == null) ? 0 : isBlocked.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((midleName == null) ? 0 : midleName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		User other = (User) obj;
		if (conversations == null) {
			if (other.conversations != null)
				return false;
		}
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isBlocked == null) {
			if (other.isBlocked != null)
				return false;
		} else if (!isBlocked.equals(other.isBlocked))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (midleName == null) {
			if (other.midleName != null)
				return false;
		} else if (!midleName.equals(other.midleName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "User [firstName=" + firstName + ", lastName=" + lastName + ", midleName=" + midleName
				+ "]";
	}
}
