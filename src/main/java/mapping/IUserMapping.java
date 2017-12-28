package mapping;

import entity.User;
import entity.Account;


public interface IUserMapping{
	public int save(User user);
	
	public User selectByLogin(String username, String password);
	
	public void newAccount(Account account);
	
	public int updateUser(User user);
}
