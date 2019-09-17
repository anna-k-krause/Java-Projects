// Got help from Chloe in office hours
import java.io.IOException;

public class PopulationGrowth {
	
	public static void main(String[] args) throws IOException{
		//throws error message if 3 arguments are not given
		if(args.length != 3) {
			System.err.println("ERROR: expecting 3 arguments");
			System.exit(1);
		}
		Integer popSize = null;
		Integer t = null;
		try {
			popSize = Integer.parseInt(args[0]);
			t = Integer.parseInt(args[2]);
		} catch (NumberFormatException nfe) {
			//throws error if single integer argument is not given
			System.err.println("ERROR: expecting a single integer argument");
			nfe.printStackTrace();
			System.exit(1);
		}
		Double r = null;
		try {
			r = Double.parseDouble(args[1]);
		} catch (NumberFormatException nfe) {
			//throws error if double argument is not given
			System.err.println("ERROR: expecting a double argument");
			nfe.printStackTrace();
			System.exit(1);
		}

		
		double newRate = (r/100);
		//gives new double amount based on what will be used in the equation 

		System.out.println(String.format("%-20s %-10s", "Elapsed Years", "Amount"));
        System.out.println(String.format("%-20s", "----------------------------"));
        System.out.println(String.format("%-20s %-10s", "-", popSize));
        //formatted heading printed
        
        
		for(int i=1; i<=t; i++) {
			//equation that finds the population growth over time given the arguments
            double exponent = (newRate*i);
            double newPop = ((Math.pow(2.71828, exponent)) * popSize); 
        

            System.out.println(String.format("%-20s %-10s", i, (int) newPop));
            //formatted printed new population 
  
		}
            
            
	}
}