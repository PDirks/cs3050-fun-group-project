/*
 * cs3050 group project
 * applicant.java
 * 
 * Cody Cameron
 * Peter Dirks
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
//I was here....hahehe

public class applicant {

	private String name;
	private ArrayList<dept> pref = new ArrayList<dept>();
	
	applicant( String name ){
		this.name = name;
	}
	
	void addDept( dept d ){
		pref.add(d);
	}
	
/*
 * getters and setters
 */
	String getName(){
		return name;
	}
	ArrayList<dept> getPref(){
		return pref;
	}
	void print(){
		System.out.println("applicant: " + name + "\n\tpreferences:");
		for( dept d : pref ){
			System.out.println("\t\t"+d.getName());
		}
	}
	
}
