package entity;

import java.util.Date;


public class User {
	/*@Autowired 若要自动装配属性，则不需要写get方法*/
	private String username;
	private String password;
	private String idnum;
	private String usertype;
	private String status;
	private String createtime;
	private String phonenum;
	int id;
	
	public User(){}
	
	public User(String username,String password,String idnum,String usertype,String status,Date createtime){}
	
	
	public String getPhonenum(){
		return phonenum;
	}
	
	public void setPhonenum(String phonenum){
		this.phonenum = phonenum;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return "username="+this.username+"\n"+"password="+this.password+"\n"+"idnum="+this.idnum+"\n"+
				"usertype="+this.usertype+"\n"+"status="+this.usertype+"\n"+"status="+this.status+"\n"+
				"createtime="+this.createtime+"\n"+"phonenum="+this.phonenum+"\n";
	}
	
}
