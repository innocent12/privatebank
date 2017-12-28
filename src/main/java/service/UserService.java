package service;

import mapping.IUserMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;

@Service
public class UserService {
	@Autowired
	private IUserMapping usermapping;
	
	//ע���û�
	public int registerUser(String username,String password,String idnum,String userType, String phonenum){
		int count=0;
		try{
			User user = new User();
			user.setIdnum(idnum);
			user.setPassword(password);
			user.setStatus("����");
			user.setUsername(username);
			user.setUsertype(userType);
			user.setPhonenum(phonenum);
			count = usermapping.save(user);
			return count;
		
		}catch(Exception e){
			return count; 
		}
	}
	
	
	//��¼��֤
	public User loginValidate(String username,String password){
		//������
	/*	System.out.print("����Service��\n");
		System.out.print("username="+username+"password="+password+"\n");*/
		User user = new User();
		try{
			user = usermapping.selectByLogin(username, password);
			if(user == null){
				System.out.print("û���ҵ���¼");
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
