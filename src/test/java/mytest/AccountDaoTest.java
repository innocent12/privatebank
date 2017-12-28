package mytest;


import java.util.List;

import mapping.IAccountMapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Account;
import entity.TransactionInfo;

/*
 * ��ΪAccountServiceDao�Ĳ����࣬����Ŀ��Ҳ����IAccountMapping.java�Ĳ�����
 * */
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�����ļ�
@ContextConfiguration({ "classpath:applicationContext.xml", "classpath:springMVC-servlet.xml" })
public class AccountDaoTest{
	
	/*�Զ�װ��accountmapping*/
	@Autowired
	private IAccountMapping accountmapping;
	
	@Test
	public void testNewAccount(){
			Account account = new Account();
			account.setIdnum("4243132132311");
			account.setName("������");
			account.setPassword("sfs1230");
			account.setTel("131213");
			account.setAccounttype("��ͨ");
			account.setAccount("1456433311");
			System.out.print(account+"\n");
			int count = accountmapping.newAccount(account);
			System.out.print("������"+count+"\n");
	}
	
	@Test
	public void testGetAllAccount(){
		String idnum = "111";
		List<Account> accountList =  accountmapping.getAllAcount(idnum);
		System.out.print("���е�account��ӡ���£�");
		for(Account account:accountList){
			System.out.print(account.toString()+"\n");
		}
	}
	
	@Test
	public void testAddTransInfo(){
		TransactionInfo transInfo = new TransactionInfo();
		int count = accountmapping.addTransRecord(transInfo);
		System.out.print(count+"\n");
	}
	
	@Test
	public void testGetMyTransaction(){
		String accountNum = "1456433333";
		List<TransactionInfo> transInfos = accountmapping.getMyTrans(accountNum);
		for(TransactionInfo transInfo: transInfos){
			System.out.print(transInfo+"\n");
		}
	}
	
	@Test
	public void testGetAccountByAccnum(){
		String accountNum="1456433333";
		Account account = accountmapping.selectAccountByAccountnum(accountNum);
		System.out.print(account+"\n");
	}
	
	@Test
	public void test(){
		String accountNum="1456433333";
		int money=3000;
		int count = accountmapping.updateMoneyByAccountnum(accountNum, money);
		System.out.print(count+"\n");
	}
	
}
