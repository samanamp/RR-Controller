package com.saman.rrc.app;

public class SysSetting {
	private String telnum;
	private String pass;
	
	public SysSetting(){}
	
	public SysSetting(String telnum, String pass){
		this.telnum = telnum;
		this.pass = pass;
	}
	
	public void setTel(String telnum){
		this.telnum = telnum;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public String getTel(){
		return this.telnum;
	}
	
	public String getPass(){
		return this.pass;
	}

}
