package co3105;

/** Finds the local minimum between the maximum points selected by the initial route 
 * - checks that the same result does not change for 50000000 million times in a row 
 * ensuring the best solution amongst the two points*/

public class SimpleHillClimbing {
	
	Swap swap = new Swap();
	TravelCalculator tc = new TravelCalculator();
	TravelCalculator tmp_tc = new TravelCalculator();
	
	private boolean change = false;
	private int local = 0;
	private boolean goalState;
	private int countTour = 0;
	
public void simpleHillClimbing(int [][] tspArray, int [] swapping) {
	
	int [] tmp_swapping = new int[tspArray.length];
	
	while(goalState != true) {
		
		swapping=swap.swapIntegers(swapping);
		
		++countTour;
		if (countTour > 1) {
			if(tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping)<=tc.totalDistanceTravelled(tspArray, swapping)){
				for(int a=0; a<swapping.length;a++) {
					swapping[a] = tmp_swapping[a];
				}
			}
			else{
				change = true;
				for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
				}
			}
		}else {
			change = true;
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping[a] = swapping[a];
				}
		}
		
		// pass new state to current state
		if(change == false){
			local++;
		}else{
			change = false;
			local = 0;	
		}
		if(local == 50000000) {
			goalState= true;
		}
		//checks that you've reached 50,000,000
		if(tc.totalDistanceTravelled(tspArray,tmp_swapping) == 2085 || (goalState == true)){
			for(int a=0; a<tmp_swapping.length;a++) {
				System.out.print(tmp_swapping[a] + " ");
			}
			System.out.println();
			System.out.println("Cost of tour " + countTour + ": " + tc.totalDistanceTravelled(tspArray, tmp_swapping));
			System.out.println("Goal state achieved");
			break;
		}
	}
}
}
