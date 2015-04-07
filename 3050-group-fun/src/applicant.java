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

public class applicant {

	private String name;
	private ArrayList<dept> pref;
	
	applicant( String name,ArrayList<dept> preferences){
		this.name = name;
		this.pref = preferences;
	}
	
	void addDept( dept d ){
		pref.add(d);
	}
	
	String getName(){
		return name;
	}
	
	ArrayList<dept> getPref(){
		return pref;
	}
}
