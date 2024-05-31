package com.entity;

public class User {
	
	private int id;
	private String uname;
	private String mailid;
	private int age;
	private String city;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User(String uname, String mailid, int age, String city) {
		super();
		this.uname = uname;
		this.mailid = mailid;
		this.age = age;
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public User(int id, String uname, String mailid, int age, String city) {
		super();
		this.id = id;
		this.uname = uname;
		this.mailid = mailid;
		this.age = age;
		this.city = city;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public void add(User user) {
		// TODO Auto-generated method stub
		
	}

}
