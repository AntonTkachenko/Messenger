/**
 * 
 */
package com.messenger.reposiroties;


import org.springframework.data.jpa.repository.JpaRepository;

import com.messenger.entities.User;

/**
 * @author devil
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByPhone(String phoneNumber);
	
	public User findByEmail(String email);

}
