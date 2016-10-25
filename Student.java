package net.ukr.geka3;

import java.io.Serializable;
import java.util.UUID;

public class Student implements Serializable {
	String name;
	String surName;
	String age;
	
	public Student(String name, String surName, String age) {
		super();
		this.name = name;
		this.surName = surName;
		this.age = age;
		
	}
	public Student() {
		super();
	}
	public String getName() {
		return name;
	}
	public String getSurName() {
		return surName;
	}
	public String getAge() {
		return age;
	}
	
	
}
