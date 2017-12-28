package mapping;

import java.util.List;

import entity.Account;
import entity.TransactionInfo;

public interface IAccountMapping {
	public int newAccount(Account account);
	
	public List<Account> getAllAcount(String idnum);
	
	public int updateMoneyByAccountnum(String accountnum, int money);
	
	public int drawMoneyByAccountnum(String accountnum, int money);
	
	public Account selectAccountByAccountnum(String accountnum);
	
	public List<TransactionInfo> getMyTrans(String accountnum);
	
	public int addTransRecord(TransactionInfo transInfo);
	
	public int modifyAccountType(String accountnum, String accountType);
}
