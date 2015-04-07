/*
 * cs3050 group project
 * dept.java
 * 
 * Cody Cameron
 * Peter Dirks
 */
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

public class dept {
	private String name;
	private ArrayList<applicant> pref;
	
	dept( String name, ArrayList<applicant> preferences ){
		this.name = name;
		pref = preferences;
	}

	void addPref( applicant a ){
		pref.add(a);
	}

	ArrayList<applicant> getPref(){
		return pref;
	}
	String getName(){
		return name;
	}
}
