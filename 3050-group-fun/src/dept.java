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
	private ArrayList<applicant> openings = new ArrayList<applicant>();
	private int open;
	
	dept( String name, int positions ){
		this.name = name;
		this.open = positions;
	}

	void addPref( applicant a ){
		pref.add(a);
	}
	
	void fill(applicant a){
		openings.add(a);	// assigns applicant
		a.assigned = 1;
		open -= 1;	// decrease opening positions
	}
	
	void free(int index) {
		openings.remove(index);
		open += 1;
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
	
	//prints out all new hires
	void printHires() {
		System.out.println("Printing...");
		System.out.println("dept: " + name + "\n\topenings: " + open + "\n\tNew Hires:");
		for( applicant a : openings ){
			System.out.println("\t\t"+a.getName());
		}
	}
}
