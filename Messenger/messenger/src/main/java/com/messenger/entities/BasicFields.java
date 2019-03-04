/**
 * 
 */
package com.messenger.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ANTON_WORK
 *
 */
@MappedSuperclass
public class BasicFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6633416079524680440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;

	@Column(name = "CREATED_DATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE_TIME",nullable = false)
	protected Date updatedDateTime;

	public BasicFields() {
		this.createdDateTime = new Date();
		this.updatedDateTime = new Date();
	}

	public final Long getId() {
		return id;
	}

	public final Date getCreatedDateTime() {
		return createdDateTime;
	}

	public final Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public final void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((updatedDateTime == null) ? 0 : updatedDateTime.hashCode());
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
		BasicFields other = (BasicFields) obj;
		if (createdDateTime == null) {
			if (other.createdDateTime != null)
				return false;
		} else if (!createdDateTime.equals(other.createdDateTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updatedDateTime == null) {
			if (other.updatedDateTime != null)
				return false;
		} else if (!updatedDateTime.equals(other.updatedDateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntityId = " + id;
	}
}
