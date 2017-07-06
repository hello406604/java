package com.kaishengit.entity;

public class User {

	private Integer id;
	private String userName;
	private String address;
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
		return "User [id=" + id + ", userName=" + userName + ", address=" + address + ", comId=" + comId + "]";
	}
}
