package com.back.miru.model.dto;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String tel;

	public User() {
		super();
	}

	public User(String id, String password, String name, String email, String tel) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", tel=" + tel
				+ "]";
	}
	
}