package co3105;

import java.util.ArrayList;
import java.util.Collections;

public class InitialRoute {	
	
	public int[] generateInitialRoute(int[] swapping) {
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i<swapping.length; i++) {
			list.add(i);
		}
	
		Collections.shuffle(list);
	
		list.add(0,0);
	
		System.out.println("Initial Route: " + list);
		for(int i = 0; i<swapping.length; i++) {
			swapping[i] = list.get(i);
		}
		
	return swapping;
	}
}
