package net.ukr.geka3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Init {
	private File fMyGroup = new File("myGroup.gr");
	private Group gr;
	
	public void init(){
		boolean newGroup = false;
		if(!fMyGroup.exists()){
			try {
				System.out.println("new group db created");
				fMyGroup.createNewFile();
				newGroup = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(fMyGroup.isDirectory()) {
			System.out.println("ERROR my Group is directory");
			
		}
		
		if(newGroup){
			createGroup();
			saveGroup();
		}else{
			gr = loadGroup();
		}
		if(gr.getArrayStudents().size()< 1){
			Student temStudent = new Student("Vasya", "Sushkov", "20");
			gr.add(temStudent);
		}
		
		
		
		
	}
	
	
	
	private void createGroup(){
		gr = new Group("myGroupName");
		
		return;
		
	}
	
	public Group loadGroup(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fMyGroup))){
			gr = (Group)ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gr;
	}
	
	public void saveGroup(){
		try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fMyGroup))){
			ous.writeObject(gr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public Group getGr() {
		return gr;
	}



	public void setGr(Group gr) {
		this.gr = gr;
	}
	
}
