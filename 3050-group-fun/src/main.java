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
			//a.print();
		}
		for( dept d : depts ){
			//d.print();
		}
		
		System.out.println();
		
		//matcher(apps, depts);
		
		multMatcher(apps, depts);
		
		for(dept dd : depts ) {
			//dd.printHires();
		}
		
	}// end main

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

				if(highest.getAssigned() == true)	 {
					d.fill(highest);
					System.out.println("matching (highest) "+ d.getName()+" with "+ highest.getName());	// debug
				} 
				else {
					/*
					 * infinate loop happening somewhere in here...
					 */
					if(highest.getPref().get(0) != d) {
						dept favorite = highest.getPref().get(0);
						
						System.out.println("matching (favorite) "+ favorite.getName() + " with "+ highest.getName());
						
						if(favorite.getOpenPositions() == 0){
							favorite.fill(highest);
						}
					} 
					else {
						break;
					}// end else
				}
			
			++c;	
			}// end while
		}// end for
	}

	static void multMatcher( ArrayList<applicant> apps, ArrayList<dept> depts){
		
		ArrayList<applicant> openApps = new ArrayList<applicant>(apps);	// track unassigned applications
		
		for( applicant a : apps ){
			if( !openApps.contains(a) ){
				continue;
			}
			System.out.println("now working on "+a.getName());	// debug
			dept d = a.getPref().get(a.getPrefRank());
			// first we check if the top prefered has open spots
			if( a.getPref().get(a.getPrefRank()).getOpenPositions() != 0 ){
				System.out.println("\t"+a.getName()+"would like to get matched with "+d.getName()+" which has open spots");
				// now we check if 'a' is even ranked by the dept
				if( d.getPref().contains(a) ){
					// a is ranked, and dept has spots open, so we assign
					d.fill(a);
					// remove 'a' from unassigned list
					openApps.remove(a);
					System.out.println("\t"+a.getName()+" is matched with "+d.getName());	// debug
					continue;
				}
				else{
					// 'a' is not prefered by the company, so we lower a's preference
					a.setPrefRank( a.getPrefRank() - 1);
					System.out.println(a.getName()+"is not prefered by"+d.getName()+" lowering prefNum to "+ a.getPrefRank());
				}	
			}// end check if prefered dept has open spots
			else{
				System.out.println("\t"+d.getName()+" has no open spots. Checking if "+a.getName()+" outranks anybody...");
				System.out.println("\tchecking dept pref of "+d.getLowestFilledRank()+" vs applicant pref of "+d.getPref().lastIndexOf(a));
				if( d.getLowestFilledRank() < d.getPref().lastIndexOf(a) ){
					
					
					
				}
			}// dept has no open spots
			
			
			
		}// end foreach
		
		
	}
	
}// end class main
