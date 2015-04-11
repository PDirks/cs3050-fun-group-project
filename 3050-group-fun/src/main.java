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
			d.print();
		}
		
		System.out.println();
		
		matcher(apps, depts);
		
		for(dept dd : depts ) {
			dd.printHires();
		}
		
	}

	/*
	 * Start of the matching algorithm, definitely still needs more work seems to run infinitely right now
	 * and needs to check all of the applicants favorites, not just the top ones
	 * 
	 */
	static void matcher(ArrayList<applicant> apps, ArrayList<dept> depts) {
		System.out.println("Running the matching algorithm....\n\n");
		
		for( dept d : depts ){
			while (d.getOpenPositions() != 0) {
				int c = 0;
				applicant highest = d.getPref().get(c);

			if(highest.getAssigned() == 0)	 {
				d.fill(highest);
			} else {
				if(highest.getPref().get(0) != d) {
					dept favorite = highest.getPref().get(0);
					if(favorite.getOpenPositions() == 0)
						favorite.fill(highest);
				} else {
					break;
				}
			}
			
			++c;	
			}
		}
	}
}
