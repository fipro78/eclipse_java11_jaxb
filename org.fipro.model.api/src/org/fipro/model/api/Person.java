package org.fipro.model.api;

import java.util.Date;

public class Person {

	public enum Gender {
		MALE, FEMALE
	}

	private int id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private boolean married;
	private Date birthday;

	Person() {
		// empty default constructor for JAXB
	}
	
	public Person(int id) {
		this.id = id;
	}

	public Person(int id, String firstName, String lastName, Gender gender, boolean married, Date birthday) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.married = married;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}