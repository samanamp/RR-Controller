package com.saman.rrc.app;

public class Child {
	int id;
	int code;
	String name;
	
	public Child(){}
	
	public Child( int id, int code, String name){
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setCode(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

}
