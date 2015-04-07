/*
 * cs3050 group project
 * reader.java
 * 
 * reads file contents
 * 
 * Cody Cameron
 * Peter Dirks
 */

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

public class reader {

	private File input;
	private Scanner scan;
	
	
	
	reader( String filename ){
		input = new File(filename);
	}
	
/*
 * readFile()
 * 		input: File to process
 * 		output: [populate internal arrays]
 */
	void readFile(File input){
		
		try{
			scan = new Scanner( input );
		}
		catch (FileNotFoundException e){
			
			System.out.println("file not found...");
			System.exit(0);
			
		}
		System.out.println("printing file...");
		while( scan.hasNextLine() ){
			
			System.out.println( scan.nextLine() );
			
		}
		
	}
	
	File getFile(){
		
		return input;
		
	}
	
	
	
}
