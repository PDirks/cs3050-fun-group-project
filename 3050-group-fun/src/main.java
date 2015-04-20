/*
 * cs3050 group project
 * main.java
 * 
 * Cody Cameron
 * Peter Dirks
 */
import java.util.Scanner;		// taking user input
import java.util.ArrayList;		// managing inputed applicants and depts

public class main {

	public static void main(String[] args) {
		String input;
		/* 
		 * let user input which file to work on, for now we'll railroad it to "test.txt", but will need to have
		 * interactivity for the final submission build
		 */
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name of file to read: ");
		input = sc.nextLine();
		*/
		input = "test.txt";
		reader read = new reader( input );

		read.readFile( read.getFile() );
		
		ArrayList<applicant> apps = read.getApps();
		ArrayList<dept> depts = read.getDepts();
		
		for( applicant a : apps ){
			a.print();
		}
		for( dept d : depts ){
			//d.print();
		}
		System.out.println();
		
		//matcher(apps, depts);
		
		multMatcher(apps, depts);
		
		for(dept dd : depts ) {
			dd.printHires();
		}
		
		System.out.println("done");
		
	}// end main

	/*
	 * matcher():
	 * Start of the matching algorithm, definitely still needs more work seems to run infinitely right now
	 * and needs to check all of the applicants favorites, not just the top ones
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

	/*
	 * deptMatcher():
	 * Not sure where I was going with this function. Was looking at running the match by working through dept
	 * preferences. Didn't delete it mostly out of spite.
	 */
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

	/*
	 * multMatcher():
	 * Another attempt at the matching algorithm.
	 */
	static void multMatcher( ArrayList<applicant> apps, ArrayList<dept> depts){
		
		ArrayList<applicant> openApps = new ArrayList<applicant>(apps);	// track unassigned applications
		ArrayList<applicant> filledApps = new ArrayList<applicant>();	// track unassigned applications
		ArrayList<dept> openDepts = new ArrayList<dept>(depts);
		ArrayList<dept> filledDepts = new ArrayList<dept>();
		
		// run loop while there are still depts w/o matches
		while( openDepts.size() != 0 ){
			for( applicant currentApp : apps ){
				int appindex = 0;
				//applicant currentApp = openApps.get(appindex);
				System.out.println("[debug] currentApp: "+currentApp.getName());	// debug
				// don't use currentApp if it is already assigned
				/*if( currentApp.getAssigned() == true ){
					System.out.println("[debug] bumping currentApp");	// debug
					openApps.remove(currentApp);
					currentApp = openApps.get(appindex++);
				}*/
				int currentRank = 0;
				
				// iterate through until all preferences are used
				while(currentApp.getPref().size() > currentRank && currentApp.getAssigned() == false){
					// where tempDept is the highest preference of applicant currentApp
					dept tempDept = currentApp.getPref().get(currentRank);
					
					// if the tempDept has open positions
					if( tempDept.getOpenPositions() != 0 ){
						tempDept.fill(currentApp);
						openApps.remove(currentApp);
						
						System.out.println("[debug] assigning "+ currentApp.getName() + " to "+tempDept.getName());	// debug
						if(tempDept.getOpenPositions() == 0){
							filledDepts.add(tempDept);
							openDepts.remove(tempDept);
						}
					}// end if open
					// check if application has better pref rank than current hires
					else if( tempDept.pref.lastIndexOf(currentApp) < tempDept.getLowestFilledRank() ){
						System.out.println("[debug] checking indexes... ("+currentApp.getName()+" vs "+tempDept.getName()+") "
								+tempDept.pref.lastIndexOf(currentApp) 
								+ " vs "+tempDept.getLowestFilledRank());	// debug
						
						applicant lower = tempDept.getLowestApplicant();
						openApps.add(lower);
						if(tempDept.remove(lower) == false){
							System.out.println("[debug] error: "+lower.getName()+" not in assigned positions");
						}
						tempDept.fill(currentApp);
							
					}
					
					currentRank++;
				}// end while
				//openApps.remove(currentApp);
			}// end foreach of applicants
		}// end while openspots still present
		
	}// end multMatcher()
	
}// end class main
