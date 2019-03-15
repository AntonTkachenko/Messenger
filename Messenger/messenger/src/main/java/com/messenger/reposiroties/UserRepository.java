/**
 * 
 */
package com.messenger.reposiroties;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.entities.User;

/**
 * @author devil
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByPhone(String phoneNumber);

	public User findByEmail(String email);

	public Page<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName, Pageable pageable);

	public Page<User> findByFirstNameLike(String firstName, Pageable pageable);

	public Page<User> findByFirstNameLikeOrLastNameLikeOrMidleNameLike(String firstName, String lastName,
			String midleName, Pageable pageable);

}
