package com.saman.rrc.app;

public class Variable {
	int id;
	int commandID;
	int code;
	String name;
	
	public Variable(){}
	
	public Variable( int id, int code, int commandID, String name){
		this.id = id;
		this.commandID=commandID;
		this.code = code;
		this.name = name;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setCommandID(int commandID){
		this.commandID = commandID;
	}
	
	public int getCommandID(){
		return this.commandID;
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
