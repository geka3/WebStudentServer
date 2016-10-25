package net.ukr.geka3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Group implements Serializable {
	private String name;
	private HashMap<Integer,Student> arrayGroup;
	public Group(String name) {
		super();
		this.name = name;
		arrayGroup = new HashMap<Integer,Student>();
	}
	public Group() {
		super();
		arrayGroup = new HashMap<Integer, Student>();
	}
	
	public HashMap<Integer,Student> getArrayStudents(){
		
		
		return arrayGroup;
	}
	
	public void add(Student st){
		arrayGroup.put(st.hashCode(), st);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
