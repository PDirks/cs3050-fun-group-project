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
import java.lang.Integer;

import java.io.File;
import java.io.FileNotFoundException;

public class reader {

	private File input;
	private Scanner scan;
	
	private ArrayList<dept> depts;
	
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
			
			String line = scan.nextLine();
			
			//System.out.println( scan.nextLine() );
			
			if( line.contains("Vacancies and Departments") ){
				/*
				 * line contains dept and vacancies, skip next line
				 * then start to read in data & create objects
				 */
				
				System.out.println("fetched: " + line);
				line = scan.nextLine();
				line = scan.nextLine();
				
				while( !line.isEmpty() ){
					System.out.println("fetched: " + line);
					String[] temp = line.split("[ ]");
					System.out.println("\t" + temp[0] + " & " + temp[1]);
					depts.add( new dept( temp[1], new Integer(temp[0]) ) );
					line = scan.nextLine();
				}
				
//				String[] temp = line.split("[ ]");
//				depts.add( new dept( temp[1], new Integer(temp[0]) ) );
//				System.out.println("adding " + temp[1] + " & " + temp[0]);
			}
			else if( line.contains("Job Applicants") ){
				
			}
			else if( line.contains("Preferences") ){
				
			}
		}
		
	}
	
/*
 * getters and setters
 */
	File getFile(){
		return input;
	}
	
	
	
}
