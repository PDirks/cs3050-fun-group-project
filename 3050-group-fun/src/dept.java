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
	public ArrayList<applicant> pref = new ArrayList<applicant>();
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
	
	void print(){
		System.out.println("dept: " + name + "\n\topenings: " + open + "\n\tpreferences:");
		for( applicant a : pref ){
			System.out.println("\t\t"+a.getName());
		}
	}
}
