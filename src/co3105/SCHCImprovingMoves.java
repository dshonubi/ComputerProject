package co3105;

public class SCHCImprovingMoves {

	TravelCalculator tc = new TravelCalculator();
	Swap swap = new Swap();
	TravelCalculator tmp_tc = new TravelCalculator();
	
	private int counter = 0;
	private int overallCount = 0;
	private int countTour = 0;
	private int [] costBound;
	
	public void stepCountingHillClimbingI(int [][] tspArray, int [] swapping,int costLimit) {
		costBound = new int[tspArray.length];
		int [] tmp_swapping = new int[tspArray.length];
	 
		while(overallCount  < 90000001) {
			swapping=swap.swapIntegers(swapping);
		
			if(countTour==0) {
				for(int a=0; a<swapping.length;a++) {
					tmp_swapping[a] = swapping[a];
				}
				for(int b=0; b<swapping.length;b++) {
					costBound[b] = tmp_swapping[b];
				}
				++countTour;
				continue;
				
			}else {
				++countTour;
				if(tc.totalDistanceTravelled(tspArray, swapping) < tc.totalDistanceTravelled(tspArray, tmp_swapping)){
					counter++;
				}
					
				if((tc.totalDistanceTravelled(tspArray, swapping) <= tc.totalDistanceTravelled(tspArray, tmp_swapping)) 
					|| (tc.totalDistanceTravelled(tspArray, swapping)<tc.totalDistanceTravelled(tspArray,costBound))) {
					for(int a=0; a<swapping.length;a++) {
						tmp_swapping[a] = swapping[a];
					}
				}
		
				if(counter >= costLimit) {
					for(int b=0; b<swapping.length;b++) {
						costBound[b] = tmp_swapping[b];
					}
					counter = 0;
				}
			}
		
			overallCount++;
			if(overallCount  == 90000000){
				for(int a=0; a<costBound.length;a++) {
					System.out.print(costBound[a] + " ");
				}
			System.out.println();
			System.out.println("Cost of tour " + overallCount + ": " + tc.totalDistanceTravelled(tspArray,costBound));
			System.out.println("Goal state achieved");
			break;
			}
		}
	}
}