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
		input = "./tests/" + sc.nextLine();
		*/
		input = "./tests/test0.txt";
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
		
		matcher(apps, depts);
		
		//multMatcher(apps, depts);
		
		for(dept dd : depts ) {
			dd.printHires();
		}
		
		System.out.println("done");
		
	}// end main

	/*
	 * matcher():
	 * Algorithm based on the wiki page with a few additions - i feel like this is close to perfect...I hope
	 */
	static void matcher(ArrayList<applicant> apps, ArrayList<dept> depts) {
		System.out.println("Running the matching algorithm....\n\n");
		
		//For all applicants....
		for( applicant a : apps ){
		
			//while applicant is not assigned....
			while (a.getAssigned() == false) {
				//Counters used to compare preferences
				int c = 0;
				int cc = 0;
				
				// get highest ranked dept for applicant index c
				dept highest = a.getPref().get(c);

				//If highest ranking dept is free for the applicant fill assign applicant to postition
				if(highest.getOpenPositions() != 0)	 {
					highest.fill(a);
					System.out.println("matching (highest) "+ highest.getName()+" with "+ a.getName());	// debug
				} 
				
				//Assuming the highest ranked is already matched then check preferences ...
				else if(cc <= c) {
					
					System.out.println(highest.getName() + " is full...running part 2!");
					System.out.println(highest.getName() + " Preference is " + highest.getPref().get(cc).getName());
						
						//If the highest preference list is not the applicant we are using....
						if(highest.getPref().get(cc) != a) {
							
								applicant lowest = highest.getLowestApplicant();
								System.out.println(highest.getName() + " Is Going to remove " + highest.getLowestApplicant().getName());
								
								//remove applicant and fill with the applicant that is preffered
								highest.remove(lowest);
								System.out.println("matching (favorite) "+ highest.getName() + " with "+ a.getName());
								highest.fill(a);
								
								//this takes the lowest removed applicant and checks to see if any departments prefer him 
								for (dept d : depts) {
									
									ArrayList<applicant> apps2 = d.getOpenings();
									
									for(applicant a2 : apps2) {
										
										if(d.getPref().indexOf(lowest) < d.getPref().indexOf(a2) ) {
											
											System.out.println(d.getName() + " likes " + lowest.getName() + " more than" + a2.getName());
											System.out.println("So on that note do not hire.. " + d.getLowestApplicant().getName());
											
											if(d.getOpenPositions() == 0) {
												
												d.remove(d.getLowestApplicant());
											}
											
												d.fill(lowest);
												break;
										}
									}// end foreach applicants
								}// end foreach depts
								
						}// end if highest preference is not current applicant 
						++cc;
				}// end cc vs c check
			
			++c;	
				if(cc >= c) {
					System.out.println("BREAK!");
					//if preferences are becoming reversed (useless comparison)
					break;
				}
			}// end while
		}// end for
		System.out.println("Done matching things together...");
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
				//int appindex = 0;
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
						System.out.println("Lower is " + lower.getName());
						openApps.add(lower);
						/*if(tempDept.remove(lower) == false){
							System.out.println("[debug] error: "+lower.getName()+" not in assigned positions");
						}*/
						tempDept.fill(currentApp);
						System.out.println("[debug] " + tempDept.getName() +  " Just received " + currentApp.getName());
							
					}
					
					currentRank++;
				}// end while
				//openApps.remove(currentApp);
			}// end foreach of applicants
		}// end while openspots still present
		
	}// end multMatcher()
	
}// end class main
