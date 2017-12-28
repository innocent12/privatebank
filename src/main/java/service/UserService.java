package service;

import mapping.IUserMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;

@Service
public class UserService {
	@Autowired
	private IUserMapping usermapping;
	
	//注册用户
	public int registerUser(String username,String password,String idnum,String userType, String phonenum){
		int count=0;
		try{
			User user = new User();
			user.setIdnum(idnum);
			user.setPassword(password);
			user.setStatus("启用");
			user.setUsername(username);
			user.setUsertype(userType);
			user.setPhonenum(phonenum);
			count = usermapping.save(user);
			return count;
		
		}catch(Exception e){
			return count; 
		}
	}
	
	
	//登录验证
	public User loginValidate(String username,String password){
		//测试用
	/*	System.out.print("进入Service层\n");
		System.out.print("username="+username+"password="+password+"\n");*/
		User user = new User();
		try{
			user = usermapping.selectByLogin(username, password);
			if(user == null){
				System.out.print("没有找到记录");
				return null;
			}
		}catch(Exception e){
			System.out.print("service-userservice error\n");
			System.out.print(e);
			return null;
		}
		return user;

	}
}
