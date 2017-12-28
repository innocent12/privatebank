package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AccountService;
import entity.Account;
import entity.TransactionInfo;
import entity.User;


@Transactional
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountservice = new AccountService();
	
	@RequestMapping(value="/pre_register",method=RequestMethod.POST)
	public String pre_register(){
		System.out.print("��ʼ����\n");
		return "/newAccount.html";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(){
		try{
			accountservice.registerAccount();
			System.out.print("�����ɹ�\n");
		}catch(Exception e){
			System.out.print("controller-accountcon error");
			System.out.print(e);
		}
		return "/home_page.html";
	}
	
	@RequestMapping(value="/savemoney",method=RequestMethod.POST)
	public String savemoney(String accountnum, int money, String name){
		try{
			String result = accountservice.saveMoney(accountnum, accountnum, money, name);
			if(result=="success"){
				return "/save_moneyPage.html";
			}
		}catch(Exception e){
			System.out.print("��� error\n");
			System.out.print(e);
			return e.toString();
		}
		return "/error.html";
	}
	
	@RequestMapping(value="/drawmoney",method=RequestMethod.POST)
	public String drawmoney(String accountnum, int money, String name){
		try{
			accountservice.drawMoney(accountnum,accountnum, money, name);
			System.out.print("ȡ������ɹ�\n");
		}catch(Exception e){
			System.out.print("ȡ�� error\n");
			System.out.print(e);
			return "/error.html";
		}
		return "/save_moneyPage.html";
	}
	
	@RequestMapping(value="/transmoney",method=RequestMethod.POST)
	public String transMoney(String giveraccount, String earneraccount, int money, String name){
		try{
			/*##*/
			accountservice.transMoney(earneraccount, giveraccount, money, name);
			System.out.print("���׳ɹ�");
		}catch(Exception e){
			System.out.print("transaction error\n");
			return e.toString();
		}
		return "/save_moneyPage.html";
	}
	
	//������ͷ���
	@RequestMapping(value="/todeposit")
	public @ResponseBody String toTypeDeposit(String accountnum, String password, String accounttype, HttpServletRequest request, HttpServletResponse response){
		User user = null;
		try{
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			if(user != null){
				String idnum = user.getIdnum();
				System.out.println(idnum);
				//List<Account> accountlist = accountservice.getAllAccountByIdnum(idnum);
				//session.setAttribute("accountlist", accountlist);
				accountservice.typeDeposit(accountnum, password, accounttype);	
			}else{
				return "����û�е�¼��ȥ��¼";
			}
		}catch(Exception e){
			System.out.println("controller-�˻���������-error"+e.toString());
			//e.printStackTrace();
			return e.toString();
		}
		return "�޸ĳɹ�";
	}
	
	@RequestMapping(value="/gettransaction", method=RequestMethod.POST)
	public String getTransInfo(){
		try{
			String accountNum="1456433333";
			
			List<TransactionInfo> list = accountservice.getTransactionInfo(accountNum);
			for(int i=0; i<list.size();i++){
				System.out.print(list.get(i).toString()+"\n");
			}
		}catch(Exception e){
			System.out.print("transinfo error\n");
			return e.toString();
		}
		return "transinfo success";
	}
}
