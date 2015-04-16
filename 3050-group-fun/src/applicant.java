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
	private ArrayList<dept> pref = new ArrayList<dept>();
	private boolean assigned;
	
	private int prefRank = 1;
	
	applicant( String name ){
		this.name = name;
		assigned = false;
	}
	
	void addDept( dept d ){
		pref.add(d);
	}
	
/*
 * getters and setters
 */
	int getPrefRank(){
		return prefRank;
	}
	
	void setPrefRank( int rank ){
		prefRank = rank;
	}
	
	void setAssigned(boolean val){
		this.assigned = val;
	}
	
	boolean getAssigned() {
		return assigned;
	}

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
