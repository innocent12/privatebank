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
	//ע���˻�
	public void registerAccount(){
		try{
			Account account = new Account();
			account.setIdnum("4243132132322");
			account.setName("������");
			account.setPassword("sfs1230");
			account.setTel("131213");
			account.setAccounttype("��ͨ");
			account.setAccount("1456433333");
			int count = accountMapping.newAccount(account);
			System.out.print(count);
			if(count<=0){
				System.out.print("ע��ʧ��\n");
			}
		}catch(Exception e){
			System.out.print("service-accountservice error\n");
			System.out.print(e);
		}
	}

	//�˻����
	public String saveMoney(String Accountnum,String endNum, int money, String spname){
		try{
			int count=0;
			count=accountMapping.updateMoneyByAccountnum(Accountnum,money);
			System.out.print("����ֵcount="+count+"\n");
			if(count>0 && Accountnum==endNum){
				String transtype="���";
				TransactionInfo transInfo = toTransInfo(Accountnum, Accountnum, money, transtype, spname);
				accountMapping.addTransRecord(transInfo);
				//System.out.print("����¼�ɹ�:"+transInfo.toString()+"\n");
			}
			return "success";
		}catch(Exception e){
			System.out.print("accountservice error--���ʧ��\n"+e);
			return "error";
		}
	}
	
	//�˻�ȡ��
	public void drawMoney(String Accountnum,String endNum, int money, String spname){
		try{
			int charge = chargeMoney(money,Accountnum);
			//����㹻
			if(charge==1){
				int count = accountMapping.drawMoneyByAccountnum(Accountnum, money);
				//������ȡ�����ͼ�¼
				if(Accountnum == endNum){
					String transtype="ȡ��";
					TransactionInfo transInfo = toTransInfo(Accountnum, endNum, money, transtype, spname);
					accountMapping.addTransRecord(transInfo);
					//System.out.print("ȡ���¼�ɹ�"+transInfo.toString()+"\n");
				}
				System.out.print("ȡ���ֵcount="+count+"\n");
			}else if(charge!=1){
				System.out.print("����\n");
			}
		}catch(Exception e){
			System.out.print("accountservice error --ȡ��ʧ��\n");
		}
	}
	
	//�˻�֮��Ľ�����ʱ��Ϊ��ȡ�����
	public void transMoney(String earnerAccountnum, String giverAccountnum,int money, String spname){
		try{
			drawMoney(giverAccountnum,earnerAccountnum, money, spname);
			saveMoney(earnerAccountnum,giverAccountnum, money, spname);
			String transtype="�˻�����";
			TransactionInfo transInfo= toTransInfo(earnerAccountnum, giverAccountnum, money, transtype, spname);
			accountMapping.addTransRecord(transInfo);
			//System.out.print("ȡ���¼�ɹ�"+transInfo.toString()+"\n");
		}catch(Exception e){
			System.out.print(e);
		} 
	}
	
	//�˻�������ͷ���
	public void typeDeposit(String accountnum, String password, String accountType){
		try{
			String type="";
			double monthRate=0.0;
			double threeMonthRate=0.0;
			switch(accountType){
			case "current":
				type="���ڴ��";
				monthRate=0.00025;
				break;
			case "fixed":
				type="���ڴ��";
				threeMonthRate=0.0135;
				break;
			case "call":
				type="֪ͨ���";
				monthRate = 0.011;
				break;
			case "currentfixed":
				type="����������";
				monthRate = 0.0101;
				break;
			default:
				monthRate=0.01;
				threeMonthRate=0.01;
				break;
			}
			accountMapping.modifyAccountType(accountnum, type);
		}catch(Exception e){
			System.out.println("������-error");
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
	
	//�õ����׼�¼
	public List<TransactionInfo> getTransactionInfo(String accountNum){
		try{
			List<TransactionInfo> transinfo=accountMapping.getMyTrans(accountNum);
			return transinfo;
			
		}catch(Exception e){
			System.out.print(e);
		}
		return null;
	}
	
	//�ж��˻����
	public int chargeMoney(int money, String Accountnum){
		int count=0;
		try{
			int balance = accountMapping.selectAccountByAccountnum(Accountnum).getMoney();
			count =money>balance?-1:1;//1��ʾ����,-1��ʾ���С
			System.out.print(count+"\n");
		}catch(Exception e){
			System.out.print(e);
			System.out.print("accountservice error--chargemoney\n");
		}
		
		return count;
	}
	
	//��װ���׼�¼
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
	





