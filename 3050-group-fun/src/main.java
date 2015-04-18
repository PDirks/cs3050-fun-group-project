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

	static void deptMatcher( ArrayList<applicant> apps, ArrayList<dept> depts){
		
		ArrayList<applicant> openApps = new ArrayList<applicant>(apps);	// track unassigned applications
		ArrayList<applicant> filledApps = new ArrayList<applicant>();	// track unassigned applications
		ArrayList<dept> openDepts = new ArrayList<dept>(depts);
		ArrayList<dept> filledDepts = new ArrayList<dept>();
		
		while( openDepts.size() != 0 ){
			int index = 0;
			dept activeDept = openDepts.get(0);
			while( index < activeDept.getPref().size()){
				
				// check if candidate is unassigned
				if( activeDept.getPref().get( index ).getAssigned() == false ){
					// assign candidate to position
					applicant added = activeDept.getPref().get( index );
					activeDept.fill( added );
					filledApps.add( added );
					openApps.remove(added);
					if( activeDept.getOpenPositions() == 0 ){	// if no more positions, remove from openDept list
						filledDepts.add(activeDept);
						openDepts.remove(activeDept);
					}
				}// end if pref is unassigned
			
			}// end iterate through pref
			
		}// end iterate through open depts
		
		/*
		 *  not sure where I'm going with this......
		 */
		
		
	}// end deptMatcher

	static void multMatcher( ArrayList<applicant> apps, ArrayList<dept> depts){
		
		ArrayList<applicant> openApps = new ArrayList<applicant>(apps);	// track unassigned applications
		ArrayList<applicant> filledApps = new ArrayList<applicant>();	// track unassigned applications
		ArrayList<dept> openDepts = new ArrayList<dept>(depts);
		ArrayList<dept> filledDepts = new ArrayList<dept>();
		
		while( openDepts.size() != 0 ){
			int appindex = 0;
			applicant currentApp = openApps.get(appindex);
			int currentRank = 0;
			
			// iterate through until all preferences are used
			while(currentApp.getPref().size() > currentRank){
				dept tempDept = currentApp.getPref().get(currentRank);
				if( currentApp.getPref().get(currentRank).getOpenPositions() != 0 ){
					tempDept.fill(currentApp);
					if(tempDept.getOpenPositions() == 0){
						filledDepts.add(tempDept);
						openDepts.remove(tempDept);
					}
				}// end if open
				else if( tempDept.pref.lastIndexOf(currentApp) < tempDept.getLowestFilledRank() ){
					// check if application has better pref rank than current hires
					applicant lower = tempDept.getLowestApplicant();
					
					
				}
				
				currentRank++;
			}// end while
			
		}// end while openspots still present
		
}
	
}// end class main
