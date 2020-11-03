package co3105;

/** @author DayoShonubi */

import java.io.*;

public class SCHCMain {
	public static void main(String[] args) throws Exception {
		
		InitialRoute initialRoute = new InitialRoute();
		SimpleHillClimbing shc = new SimpleHillClimbing();
		StepCountingHillClimbing schc = new StepCountingHillClimbing();
		SCHCAcceptedMoves sca = new SCHCAcceptedMoves();
		SCHCImprovingMoves sci = new SCHCImprovingMoves();
		SimulatedAnnealing sa = new SimulatedAnnealing();
		LateAcceptanceHillClimbing lahc = new LateAcceptanceHillClimbing();
		TravelCalculator tc = new TravelCalculator();
		
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();

		if(args.length < 1) {
		    System.out.println("Error, enter valid text file");
		    System.exit(1);
		}
		   
		  	
		//reads in the file // change later to a command line argument
		BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0])); 
		
		//Reads the first three lines of the text file as they are always the same
		// the name of the file, any comments and the type of problem it is
		bufferedReader.readLine();
		bufferedReader.readLine();
	    bufferedReader.readLine();
	    
	    
	    //get the dimensions for the array
	    //split string, isolate the dimension and convert it to an integer
	    String dimensionLine = bufferedReader.readLine();
	    String [] dimensionLineSplit = dimensionLine.split(" ");
	    int dimension = Integer.parseInt(dimensionLineSplit[1]);
	   
	    int [][] tspArray = new int[dimension][dimension];
	    
	    //reads the next two lines after the dimensions which are the same
		bufferedReader.readLine();
		bufferedReader.readLine();
		bufferedReader.readLine();
		
		String distances;
		int count = 0;
		int i=0;
     	int j=0;
     	
     	//creates a 2D-array of the values received from the buffered reader
     	while((distances = bufferedReader.readLine()) != null) {	
			String [] distancesSplit = distances.split("\\s+");
	     	while(count<distancesSplit.length) {
	     		if (distancesSplit[count].equals("EOF")) {
	     			break;
	     		}else {
	     			int distance = Integer.parseInt(distancesSplit[count]);
	     			if(distance==0) {
	     				tspArray[i][j] = distance;
	     				++j;
	     				i=0;
	     			}else {
	     				tspArray[i][j] = distance;
	     		     	tspArray[j][i] = distance;
	     		     	 ++i;		 
	     		     }
	     			}
	     			count++;
	     		}
	     		count=0;
		}
    	
    //creates an array the length of the the dimension for swapping values  	
	int [] swapping = new int[tspArray.length];
	
	//fills array to the dimension size
	for(int a=0; a<tspArray.length;a++) {
		swapping[a] = a;
	}
	
	//generates the first route to be tried by an algorithm
	swapping = initialRoute.generateInitialRoute(swapping);
	System.out.println("Initial Route Cost: "+ tc.totalDistanceTravelled(tspArray, swapping));
	
	//simple hill climbing algorithm
	startTime = System.currentTimeMillis();
		shc.simpleHillClimbing(tspArray, swapping);
	endTime = System.currentTimeMillis();
	System.out.println("SHC : That took " + (endTime - startTime)/1000 + " seconds");
	
	//counterLimit is set by the user - lst value on the right, it currently updates every 500
	startTime = System.currentTimeMillis();
		schc.stepCountingHillClimbing(tspArray, swapping, 500);
	endTime = System.currentTimeMillis();
	System.out.println("SCHC : That took " + (endTime - startTime)/1000 + " seconds");
	
	startTime = System.currentTimeMillis();
		sca.stepCountingHillClimbingA(tspArray, swapping,150);
	endTime = System.currentTimeMillis();
	System.out.println("SCHC-ACP : That took " + (endTime - startTime)/1000 + " seconds");	
	
	startTime = System.currentTimeMillis();
		sci.stepCountingHillClimbingI(tspArray, swapping,150);
	endTime = System.currentTimeMillis();
	System.out.println("SCHC-IMP : That took " + (endTime - startTime)/1000 + " seconds");	
	
	startTime = System.currentTimeMillis();
		sa.simulatedAnnealing(tspArray, swapping);
	endTime = System.currentTimeMillis();
	System.out.println("SA : That took " + (endTime - startTime)/1000 + " seconds");
	
	startTime = System.currentTimeMillis();
		lahc.lateAcceptanceHillClimbing(tspArray, swapping);
	endTime = System.currentTimeMillis();
	System.out.println("LAHC : That took " + (endTime - startTime)/1000 + " seconds");	
	
	
	}
	
}



