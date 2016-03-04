package com.example.contacts;

import java.io.File;

public class Contact{
	private String ContactName;
	//private int imageId;
	

//	public int getImageId() {
//		return imageId;
//	}
//
//
//	public void setImageId(int imageId) {
//		this.imageId = imageId;
//	}


	public String getContactNumber() {
		return ContactNumber;
	}


	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}


	private String ContactNumber;
	//private int imageId;

	public Contact(String name, String nmbr /*int imageId */ ) {
		this.ContactName = name;
		this.ContactNumber = nmbr;
		//this.imageId = imageId;
	}

	
	public String getContactName() {
		return ContactName;
	}


	public void setContactName(String contactName) {
		ContactName = contactName;
	}


}

