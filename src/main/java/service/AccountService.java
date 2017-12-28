package service;

import java.util.List;

import mapping.IAccountMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Account;
import entity.TransactionInfo;

@Service
public class AccountService {
	@Autowired
	IAccountMapping accountMapping;
	//注册账户
	public void registerAccount(){
		try{
			Account account = new Account();
			account.setIdnum("4243132132322");
			account.setName("嘘嘘下");
			account.setPassword("sfs1230");
			account.setTel("131213");
			account.setAccounttype("普通");
			account.setAccount("1456433333");
			int count = accountMapping.newAccount(account);
			System.out.print(count);
			if(count<=0){
				System.out.print("注册失败\n");
			}
		}catch(Exception e){
			System.out.print("service-accountservice error\n");
			System.out.print(e);
		}
	}

	//账户存款
	public String saveMoney(String Accountnum,String endNum, int money, String spname){
		try{
			int count=0;
			count=accountMapping.updateMoneyByAccountnum(Accountnum,money);
			System.out.print("存款返回值count="+count+"\n");
			if(count>0 && Accountnum==endNum){
				String transtype="存款";
				TransactionInfo transInfo = toTransInfo(Accountnum, Accountnum, money, transtype, spname);
				accountMapping.addTransRecord(transInfo);
				//System.out.print("存款记录成功:"+transInfo.toString()+"\n");
			}
			return "success";
		}catch(Exception e){
			System.out.print("accountservice error--存款失败\n"+e);
			return "error";
		}
	}
	
	//账户取款
	public void drawMoney(String Accountnum,String endNum, int money, String spname){
		try{
			int charge = chargeMoney(money,Accountnum);
			//余额足够
			if(charge==1){
				int count = accountMapping.drawMoneyByAccountnum(Accountnum, money);
				//定义自取款类型记录
				if(Accountnum == endNum){
					String transtype="取款";
					TransactionInfo transInfo = toTransInfo(Accountnum, endNum, money, transtype, spname);
					accountMapping.addTransRecord(transInfo);
					//System.out.print("取款记录成功"+transInfo.toString()+"\n");
				}
				System.out.print("取款返回值count="+count+"\n");
			}else if(charge!=1){
				System.out.print("余额不足\n");
			}
		}catch(Exception e){
			System.out.print("accountservice error --取款失败\n");
		}
	}
	
	//账户之间的交易暂时分为存取款操作
	public void transMoney(String earnerAccountnum, String giverAccountnum,int money, String spname){
		try{
			drawMoney(giverAccountnum,earnerAccountnum, money, spname);
			saveMoney(earnerAccountnum,giverAccountnum, money, spname);
			String transtype="账户交易";
			TransactionInfo transInfo= toTransInfo(earnerAccountnum, giverAccountnum, money, transtype, spname);
			accountMapping.addTransRecord(transInfo);
			//System.out.print("取款记录成功"+transInfo.toString()+"\n");
		}catch(Exception e){
			System.out.print(e);
		} 
	}
	
	//账户存款类型服务
	public void typeDeposit(String accountnum, String password, String accountType){
		try{
			String type="";
			double monthRate=0.0;
			double threeMonthRate=0.0;
			switch(accountType){
			case "current":
				type="活期存款";
				monthRate=0.00025;
				break;
			case "fixed":
				type="定期存款";
				threeMonthRate=0.0135;
				break;
			case "call":
				type="通知存款";
				monthRate = 0.011;
				break;
			case "currentfixed":
				type="定活两便存款";
				monthRate = 0.0101;
				break;
			default:
				monthRate=0.01;
				threeMonthRate=0.01;
				break;
			}
			accountMapping.modifyAccountType(accountnum, type);
		}catch(Exception e){
			System.out.println("存款服务-error");
			e.printStackTrace();
		}
	}
	
	public List<Account> getAllAccountByIdnum(String idnum){
		List<Account> accountlist = null;
		try{
			accountlist = accountMapping.getAllAcount(idnum); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return accountlist;
	}
	
	//得到交易记录
	public List<TransactionInfo> getTransactionInfo(String accountNum){
		try{
			List<TransactionInfo> transinfo=accountMapping.getMyTrans(accountNum);
			return transinfo;
			
		}catch(Exception e){
			System.out.print(e);
		}
		return null;
	}
	
	//判断账户余额
	public int chargeMoney(int money, String Accountnum){
		int count=0;
		try{
			int balance = accountMapping.selectAccountByAccountnum(Accountnum).getMoney();
			count =money>balance?-1:1;//1表示余额大,-1表示余额小
			System.out.print(count+"\n");
		}catch(Exception e){
			System.out.print(e);
			System.out.print("accountservice error--chargemoney\n");
		}
		
		return count;
	}
	
	//封装交易记录
	public TransactionInfo toTransInfo(String beginNum, String endNum, int money, String tranType, String spname){
		try{
			TransactionInfo transinfo = new TransactionInfo();
			transinfo.setBeginaccount(beginNum);
			transinfo.setEndaccount(endNum);
			transinfo.setMoney(money);
			transinfo.setTrantype(tranType);
			transinfo.setSponsorname(spname);
			return transinfo;
		}catch(Exception e){
			return null;
		}
	}

}
	





