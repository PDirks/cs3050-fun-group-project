/*
 * cs3050 group project
 * main.java
 * 
 * Cody Cameron
 * Peter Dirks
 */
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) {
		
		System.out.println("program!");
		reader read = new reader( "test.txt" );

		read.readFile( read.getFile() );
		
		ArrayList<applicant> apps = read.getApps();
		ArrayList<dept> depts = read.getDepts();
		
		for( applicant a : apps ){
			a.print();
		}
		for( dept d : depts ){
			//d.print();
		}
	}

}
