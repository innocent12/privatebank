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
	/*限制页面资源访问方法,一般只能以POST方式进行访问*/
	@RequestMapping(value="/pre_register", method=RequestMethod.POST)
	public String pre_register(){
		System.out.print("开始注册账号\n");
		return "/register.html";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(String username, String password, String idnum, String userType, String phonenum){
		int count = userservices.registerUser(username,password,idnum,userType,phonenum);
		if(count ==0){
			System.out.print("注册失败\n");
			return "/login.html";
		}
		System.out.print("注册账号成功\n");
		
		//在此处应该传入模式和视图并且调用ModelAndView
		return "/home_page.html";
	}
	
	@RequestMapping(value="/pre_login", method=RequestMethod.POST)
	public String pre_userLogin(){
		System.out.print("用户登录\n");
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
	public String userlogin(String username, String password, HttpServletRequest request){//类似隐式传参
		/*System.out.print("username="+username+"password="+password+"\n");*/
		User user = null;
		try{
			user = userservices.loginValidate(username,password);
			if(user == null){
				return "/error.html";
			}
			//将登陆的账号存入session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);	
		}catch(Exception e){
			System.out.print("controller error\n");
			return "/error.html";
		}
		//System.out.print("成功登入系统");
		return "/home_page.html";
	}
}
