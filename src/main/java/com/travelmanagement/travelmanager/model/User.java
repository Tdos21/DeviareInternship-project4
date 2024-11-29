package com.travelmanagement.travelmanager.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
	Integer id;
	
	
	String name;
	String lastName;
	String phone;
	String email;
	String password;
	String address;
	String zipCode;
	
	public User() {
		
	}
	
	public User(Integer id,String name, String lastName, String phone, String email, String password, String address, String zipCode) {
		this.id = id;
	    this.name = name;
	    this.lastName = lastName;
	    this.phone = phone;
	    this.address = address;
	    this.zipCode = zipCode;
	    this.email = email;
	    this.password = password;
	    
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", zipCode=" + zipCode + ", email=" + email+ "]";
	}


	

}
