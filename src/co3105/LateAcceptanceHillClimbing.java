package co3105;

import java.util.ArrayList;

public class LateAcceptanceHillClimbing {
	
    /** Finds the counter minimum between the maximum points selected by the initial route 
     * - checks that the same result does not change for 1 million times in a row 
     * ensuring the best solution amongst the two points*/
	
	Swap swap = new Swap();
	TravelCalculator tc = new TravelCalculator();
	TravelCalculator tmp_tc = new TravelCalculator();

	private int counter = 0;
	private boolean tempValue = false;
	private int countTour = 0;
	int lahcArrayFull = 0;
	
	public void lateAcceptanceHillClimbing(int [][] tspArray, int [] swapping) {
		int [] tmp_swapping = new int[tspArray.length];
		ArrayList<int[]> lateAcceptanceFitnessArray = new ArrayList<int[]>();
	
		for(int y=0; y<swapping.length;y++) {
			tmp_swapping[y] = swapping[y] ;
		}
	
		while(tempValue != true) {
			swapping=swap.swapIntegers(swapping);
		
			if (countTour == 0) {
				for(int p = 0; p<10;p++) {
					lateAcceptanceFitnessArray.add(0,swapping);
				}
				countTour++;
				
			}else {
				if(tc.totalDistanceTravelled(tspArray,lateAcceptanceFitnessArray.get(9))<= tc.totalDistanceTravelled(tspArray, swapping)) {
					lateAcceptanceFitnessArray.remove(9);
					lateAcceptanceFitnessArray.add(0,swapping);
					counter++;
				}
			}
	
			if(counter == 90000000) {
				tempValue= true;
			}

			if(tempValue == true){
				for(int b=0; b<10;b++) {
					if(tc.totalDistanceTravelled(tspArray,lateAcceptanceFitnessArray.get(b))
						<=tc.totalDistanceTravelled(tspArray, tmp_swapping)){
						for(int m=0; m<swapping.length;m++) {
							tmp_swapping[m] = lateAcceptanceFitnessArray.get(b)[m] ;
						}
					}
				}
			
				for(int a=0; a<swapping.length;a++) {
					System.out.print(tmp_swapping[a] + " ");
				}
				System.out.println();
				System.out.println("Cost of tour " + counter + ": " + tc.totalDistanceTravelled(tspArray, tmp_swapping));
				System.out.println("Goal state achieved");
				break;
			}
		}
	}
}