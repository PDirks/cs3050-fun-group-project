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
		
	}

}
