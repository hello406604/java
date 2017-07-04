package com.kaishengit.entity;

public class User {
	private Integer id;
	private String userName;
	private String address;
	private String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", address=" + address + ", password=" + password
				+ ", deptId=" + deptId + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Integer deptId;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

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
}
