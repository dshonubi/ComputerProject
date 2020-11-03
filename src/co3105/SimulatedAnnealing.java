package co3105;

import java.util.Random;

public class SimulatedAnnealing {

	TravelCalculator tc = new TravelCalculator();
	Swap swap = new Swap();
	TravelCalculator tmp_tc = new TravelCalculator();
	
	private int overallCount = 0;
	private int countTour = 0;
	private int temperature = 90000000;
	Random rand = new Random();
	int finalResult = 0;


	public void simulatedAnnealing(int [][] tspArray, int [] swapping) {
		int [] tmp_swapping = new int[tspArray.length];
		int [] tmp_swapping2 = new int[tspArray.length];
		
		for(int m = 0; m < swapping.length; m++) {
			tmp_swapping2[m]= swapping[m];
		}
		
		while(overallCount<90000001) {
		
		for(int i = 0; i < swapping.length; i++) {
			tmp_swapping[i]= swapping[i];
		}
		
		tmp_swapping = swap.swapIntegers(tmp_swapping);
		int change = tc.totalDistanceTravelled(tspArray, swapping) - tmp_tc.totalDistanceTravelled(tspArray, tmp_swapping);	
		++countTour;
		int numb = (change*-1);
		double value = (double) numb/temperature;
		
		if(change>0) {
			for(int a=0; a<swapping.length;a++) {
				 swapping[a] = tmp_swapping[a];
			}
		}
		
		if(value<rand.nextDouble() && change<=0) {
			for(int a=0; a<swapping.length;a++) {
				 swapping[a] = tmp_swapping[a];
			}
		}
		
		if(tc.totalDistanceTravelled(tspArray, tmp_swapping)<=tc.totalDistanceTravelled(tspArray, tmp_swapping2)) {
			for(int a=0; a<swapping.length;a++) {
				tmp_swapping2[a] = tmp_swapping[a];
			}
		}
		
	    temperature--;
		overallCount++;
		
		if(overallCount  == 90000000){
			for(int a=0; a<tmp_swapping2.length;a++) {
				System.out.print(tmp_swapping2[a] + " ");
			}
			System.out.println();
			System.out.println("Cost of tour " + countTour + ": " + tc.totalDistanceTravelled(tspArray, tmp_swapping2));
			System.out.println("Goal state achieved");
			finalResult = tc.totalDistanceTravelled(tspArray,tmp_swapping2);
			break;
		}
	}
}
}