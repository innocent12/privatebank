package entity;

public class TransactionInfo {
	private String beginaccount;
	private String endaccount;
	private int money;
	private int id;
	private String trantype;
	private String sponsorname;
	

	public String getSponsorname() {
		return sponsorname;
	}

	public void setSponsorname(String sponsorname) {
		this.sponsorname = sponsorname;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}

	public TransactionInfo(){}
	
	public TransactionInfo(String beginaccount, String endaccount,int money,String transtype){}
	
	public String getBeginaccount() {
		return beginaccount;
	}
	public void setBeginaccount(String beginaccount) {
		this.beginaccount = beginaccount;
	}
	public String getEndaccount() {
		return endaccount;
	}
	public void setEndaccount(String endaccount) {
		this.endaccount = endaccount;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	@Override
	public String toString(){
		String sentences="TransInfo:beginaccount="+this.beginaccount+"\n"
				+"endaccount="+this.endaccount+"\n"
				+"money="+this.money+"\n"
				+"transtype="+this.trantype+"\n";
		return sentences;
	}

}
