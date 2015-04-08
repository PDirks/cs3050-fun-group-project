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
	public ArrayList<applicant> pref;
	private int open;
	
	dept( String name, int positions ){
		this.name = name;
		this.open = positions;
	}

	void addPref( applicant a ){
		pref.add(a);
	}

/*
 * Getters and setters
 */
	ArrayList<applicant> getPref(){
		return pref;
	}
	String getName(){
		return name;
	}
	int getOpenPositions(){
		return open;
	}
}
