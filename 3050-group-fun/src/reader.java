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
	
	private ArrayList<dept> depts = new ArrayList<dept>();
	private ArrayList<applicant> apps = new ArrayList<applicant>();
	
	reader( String filename ){
		input = new File(filename);
	}
	
/*
 * readFile()
 * 		input: File to process
 * 		output: [populate internal arrays]
 */
	void readFile(File input){
		String[] temp;
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
				
//				System.out.println("Vacancies & dept fetched: " + line);	// debug
				line = scan.nextLine();
				line = scan.nextLine();
				
				while( !line.isEmpty() ){
//					System.out.println("fetched: " + line);	// debug
					temp = line.split("[ ]");
//					System.out.println("\t[" + temp[0] + "] & [" + temp[1] +"]");	//debug
					dept tempDept = new dept( temp[1], Integer.parseInt(temp[0]) );
					depts.add( tempDept );
					line = scan.nextLine();
				}
			}
			else if( line.contains("Job Applicants") ){
				/*
				 * same logic as vacancies and dept processing, take whole line and add to app obj
				 */
//				System.out.println("Job apps fetched: " + line);	// debug
				line = scan.nextLine();
				line = scan.nextLine();
				while( !line.isEmpty() ){
//					System.out.println("\t[" + line + "]");	// debug
					apps.add( new applicant( line ) );
					line = scan.nextLine();
				}
			}
			else if( line.contains("Preferences") ){
				/*
				 * logic split between dept preferences and app preferences
				 * 	i. need to identify which dept is referenced
				 *  ii. then add to preference list 
				 */
				
				//System.out.println("preferences fetched: " + line);	// debug
				temp = line.split("[ ]");
				String match;
				for(int i = 0; i < depts.size(); i++){
					if( depts.get(i).getName().equals(temp[1]) ){
						// we have the parent preference, now process the target...
						line = scan.nextLine();
						line = scan.nextLine();
						
						// process preference list
						while( !line.isEmpty() ){
//							System.out.println("\tadding [" + line + "]");	// debug
							//need to find the applicant in the arraylist
							for(applicant a : apps){
								if( a.getName().equals(line) )
									depts.get(i).addPref( a );
							}// end inner for
							line = scan.nextLine();// bump line reading
						}
					}// end dept check
				}// end dept loop
				for(int i = 0; i < apps.size(); i++){
					if( line.contains(apps.get(i).getName()) ){
						line = scan.nextLine();
						line = scan.nextLine();
						
						// process preference list
						while( !line.isEmpty() ){
							System.out.println("\tadding[" + line + "]");	// debug
							line =  line.replaceAll("\\s+$", "");
							for(dept d : depts){
								if( d.getName().equals(line)){
									apps.get(i).addDept( d );
								}
							}
							line = scan.nextLine();// bump line reading
						}
					}// end apps checl
				}// end apps loop
				
			}// end preferences check
		}
		
	}
	
/*
 * getters and setters
 */
	File getFile(){
		return input;
	}
	ArrayList<applicant> getApps(){
		return apps;
	}
	ArrayList<dept> getDepts(){
		return depts;
	}
	
	
}
