/*
 * cs3050 group project
 * dept.java
 * 
 * Cody Cameron
 * Peter Dirks
 */
import java.util.ArrayList;		// for object logic

public class dept {
	private String name;
	public ArrayList<applicant> pref = new ArrayList<applicant>();

	private ArrayList<applicant> openings = new ArrayList<applicant>();	// people assigned to positions

	private int open;
	private int openpermanent;
	private int lowestFilledRank;
	
	dept( String name, int positions ){
		this.name = name;
		this.open = positions;
		this.openpermanent = positions;
	}

	void addPref( applicant a ){
		pref.add(a);
	}
	
	/*
	 * fill() - fills position with given applicant, returns false if dept already full
	 * input:	applicant to fill
	 */
	boolean fill(applicant a){
		if(open == 0){
			return false;
		}
		
		openings.add(a);	// assigns applicant
		a.setAssigned(true);
		open -= 1;	// decrease opening positions
		
		return true;
		//Commenting this out for now so I can try something...
		/*if( lowestFilledRank < pref.lastIndexOf(a)){	// update lowestFilledRank
			lowestFilledRank = pref.lastIndexOf(a);
		}
		System.out.println("[debug] "+this.name+" lowest rank now "+this.lowestFilledRank);*/
		
	}
	
	applicant getLowestApplicant(){
		//int lowest = 0;
		
		int x = this.getOpenPositions();
		int y = this.getOpenPermanent();
	
		return openings.get(y-x-1);
		
		/* Commenting out to try things...
		for( applicant a : openings ){
			System.out.println("[debug] updating lowest rank ("+this.name+")");	// debug
			if( pref.lastIndexOf(a) > lowest ){
				lowest = pref.lastIndexOf(a); 
			}
			
		}*/
		
		//return pref.get(lowest);
		
	}// end get lowest Applicant
	
	void remove(applicant a){
		//Commented out to try some things....
		/*if( !openings.contains(a) ){
			return false;
		}*/
		
		
		openings.remove(a);
		a.setAssigned(false);
		open +=1;
		
		
		/*
		 * need to recalculate the lowest pref rank in the openings arrayList...
		 */
		/*if(openings.size()==0){
			lowestFilledRank = 100;
		}
		else{
			int tempLowest = -1;
			for( applicant tempApp : openings ){
				if( pref.lastIndexOf(tempApp) > tempLowest ){
					tempLowest = pref.lastIndexOf(tempApp);
				}
			}
			lowestFilledRank = tempLowest;
		}
		return true;*/
	}
	
	void free(int index) {
		openings.remove(index);
		open += 1;
	}

/*
 * Getters and setters
 */
	ArrayList<applicant> getPref(){
		return pref;
	}
	ArrayList<applicant> getOpenings() {
		return openings;
	}
	String getName(){
		return name;
	}
	int getOpenPositions(){
		return open;
	}
	int getLowestFilledRank(){
		return lowestFilledRank;
	}
	int getOpenPermanent(){
		return openpermanent;
	}
	void print(){
		System.out.println("dept: " + name + "\n\topenings: " + open + "\n\tpreferences:");
		for( applicant a : pref ){
			System.out.println("\t\t"+a.getName());
		}
	}
	
	//prints out all new hires
	void printHires() {
		System.out.println("Printing...");
		System.out.println("dept: " + name + "\n\topenings: " + open + "\n\tNew Hires:");
		for( applicant a : openings ){
			System.out.println("\t\t"+a.getName());
		}
	}

}
