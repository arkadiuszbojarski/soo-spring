/**
 * 
 */
package org.bojarski.repository;

import org.bojarski.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Arkadiusz Bojarski
 *
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	public User findOneByName(String name);
}
