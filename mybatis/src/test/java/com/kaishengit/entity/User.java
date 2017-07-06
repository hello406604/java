package com.kaishengit.entity;

import java.io.Serializable;

public class User implements Serializable{

//   添加序列化ID   用于二级缓存时 需要设置为可序列化的
	private static final long serialVersionUID = 4468084317486252401L;
	private Integer id;
	private String userName;
	private String address;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Integer comId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", address='" + address + '\'' +
				", password='" + password + '\'' +
				", comId=" + comId +
				'}';
	}
}
