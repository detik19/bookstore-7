package net.ruangtedy.java.spring.bookstore.repository;

import net.ruangtedy.java.spring.bookstore.domain.Account;

/**
* Repository for working with {@link Account} domain objects
* 
* @author Marten Deinum
* @author Koen Serneels
*
*/

public interface AccountRepository {
	 Account findByUsername(String username);
	 Account findById(long id);
	 Account save(Account account);

}
