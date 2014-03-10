package net.ruangtedy.java.spring.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ruangtedy.java.spring.bookstore.domain.Account;
import net.ruangtedy.java.spring.bookstore.repository.AccountRepository;
/*
 * @Service serves as a specialization of @Component, 
 * allowing for implementation classes to be autodetected through classpath scanning. 
 * What this means is that you could annotate your service-layer classes with @Component, 
 * but by annotating them with @Service instead, your classes are more properly suited for processing by tools or associating with aspects, 
 * since @Service makes an ideal target for pointcuts. 
 * 
 * Of course, it is possible that @Service may carry additional semantics in the future; 
 * thus, if you are making a decision between using @Component and @Service, @Service is clearly the better choice for your service-layer.
 */
@Service
@Transactional(readOnly=true)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(readOnly = false)
	public Account save(Account account) {
		
		return this.accountRepository.save(account);
	}

	@Override
	public Account login(String username, String password)
			throws AuthenticationException {
		Account account=this.accountRepository.findByUsername(username);
		if(account!=null){
			String pwd=org.apache.commons.codec.digest.DigestUtils.sha256Hex(password+"{"+username+"}");
			if(!account.getPassword().equalsIgnoreCase(pwd)){
				throw new AuthenticationException("Wrong username/password combination.", "invalid.password");

			}
			
		}else{
			throw new AuthenticationException("Wrong username/password combination.", "invalid.username");

		}
		return account;
	}

	@Override
	public Account getAccount(String username) {
		return this.accountRepository.findByUsername(username);
	}

}
