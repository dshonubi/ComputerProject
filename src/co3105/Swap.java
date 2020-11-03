package co3105;

public class Swap {
		
	public int[] swapIntegers(int tspArray[]) {
		int tmp, random1 = 0, random2 = 0;

		while((random1 == random2) || (random1 == 0) || (random2 == 0) ) {
			random1 = 1 + (int) (Math.random() * ((tspArray.length-1) - 1)+1);
			random2 = 1 + (int) (Math.random() * ((tspArray.length-1) - 1)+1);
		}
		
		tmp	= tspArray[random1];
	    tspArray[random1]= tspArray[random2];
		tspArray[random2]=tmp;
			
		return tspArray;
	}
}