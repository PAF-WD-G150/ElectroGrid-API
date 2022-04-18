package org.customermanagement.crud.CustomerManagement.model;

public class customermanagementmodel {
	
	private String accountid;
	private String accountname;
	private String nic;
	private String name;
	private String address;
	private String email;
	private String province;
	

	public customermanagementmodel() {
		
	}
	
	public customermanagementmodel(String accountid, String accountname, String nic, String name, String address,
			String email, String province) {
		super();
		this.accountid = accountid;
		this.accountname = accountname;
		this.nic = nic;
		this.name = name;
		this.address = address;
		this.email = email;
		this.province = province;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	





}
