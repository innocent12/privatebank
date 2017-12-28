package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.UserService;
import entity.User;

@Transactional
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userservices = new UserService();
	/*����ҳ����Դ���ʷ���,һ��ֻ����POST��ʽ���з���*/
	@RequestMapping(value="/pre_register", method=RequestMethod.POST)
	public String pre_register(){
		System.out.print("��ʼע���˺�\n");
		return "/register.html";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(String username, String password, String idnum, String userType, String phonenum){
		int count = userservices.registerUser(username,password,idnum,userType,phonenum);
		if(count ==0){
			System.out.print("ע��ʧ��\n");
			return "/login.html";
		}
		System.out.print("ע���˺ųɹ�\n");
		
		//�ڴ˴�Ӧ�ô���ģʽ����ͼ���ҵ���ModelAndView
		return "/home_page.html";
	}
	
	@RequestMapping(value="/pre_login", method=RequestMethod.POST)
	public String pre_userLogin(){
		System.out.print("�û���¼\n");
		return "/login.html";
	}
	
	@RequestMapping(value="/charge_login")
	public @ResponseBody String chargeLogin(HttpServletRequest request, HttpServletResponse response){
		User user = null;
		String word="";
		try{
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			if(user != null){
				word="success";
			}else{
				word="failed";
			}
		}catch(Exception e){
			word="failed";
		}
		return word;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String userlogin(String username, String password, HttpServletRequest request){//������ʽ����
		/*System.out.print("username="+username+"password="+password+"\n");*/
		User user = null;
		try{
			user = userservices.loginValidate(username,password);
			if(user == null){
				return "/error.html";
			}
			//����½���˺Ŵ���session��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);	
		}catch(Exception e){
			System.out.print("controller error\n");
			return "/error.html";
		}
		//System.out.print("�ɹ�����ϵͳ");
		return "/home_page.html";
	}
}
