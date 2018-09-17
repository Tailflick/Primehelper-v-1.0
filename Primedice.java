import java.util.Scanner;

public class Primedice {
	
	static Scanner uIn = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true) {
			
			System.out.println("Enter command: (\'help\' for commands)");
			
			String input = uIn.nextLine();
			
			if (input.equalsIgnoreCase("help")) {
				System.out.println("\nAvailable commands: \n");
				System.out.println("exit - exits the program");
				System.out.println("help - displays this menu");
				System.out.println("odds - calculate the odds of going on a loosing streak");
				System.out.println("martin - martingale helper tool");
				
				continue;
				
			} else if (input.equalsIgnoreCase("exit")) {
				System.out.println("Exiting...");
				System.exit(0);
			} else if (input.equalsIgnoreCase("martin")) {
				
				System.out.println("\nMartingale helper. (Demonstrates how much you should increase your bet on loss)\n");
				System.out.println("Only reccomended for payout <= 2, (the equation is asymptotic for payout > 2, and may increase faster than you like\n");
				
				while (true) {
					
					System.out.println("Enter desired payout: (\'return\' to go to main menu)");
					input = uIn.nextLine();
					double payout;
					
					if(input.equalsIgnoreCase("return")){
						break;
					} 
					
					try {
						payout = Double.parseDouble(input);
					} catch (NumberFormatException e) {
						System.out.println("\'" + input + "\' is not a valid number, remember to use periods and not commas" );
						continue;
					} 
					
					if (payout <= 1) {
						System.out.println("Payout must be larger than 1");
						continue;
					} else if (payout > 9899) {
						System.out.println("Payout must be smaller than 9990");
						continue;
					}
					getStrat(payout);
					
					
					continue;
					
				}
				
				continue;
				
			} else if (input.equalsIgnoreCase("odds")) {
				
				System.out.println("\nLoss streak helper \n");
				
				while (true) {
					
					System.out.println("Enter odds of winning: (e.g. '49.50'. 'return' to go to main menu");
					
					input = uIn.nextLine();
					
					if(input.equalsIgnoreCase("return")) {
						break;
					}
					double odds;
					try {
						odds = Double.parseDouble(input);
					} catch (NumberFormatException e) {
						System.out.println("\'" + input + "\' is not a valid number, remember to use periods and not commas" );
						continue;
					} 
					
					if (odds <= 0 || odds >= 100) {
						System.out.println("Last time I checked, " + odds + " was not a valid odds percentage.");
						continue;
					}
					
					while(true) {
						
						System.out.println("Enter how many losses you wish to calculate: (max 1000)");
			
						input = uIn.nextLine();
						
						if(input.equalsIgnoreCase("return")) {
							break;
						} 
						
						int calcNums;
						try {
							calcNums = Integer.parseInt(input);
						} catch (NumberFormatException e) {
							System.out.println("\'" + input + "\' is not a valid number, remember to use periods and not commas" );
							continue;
						} 
						
						if (calcNums > 1000) {
							System.out.println("'" + input + "' is NOT smaller than 1000, nice try");
							continue;
						} else if (calcNums <= 0) {
							System.out.println("Okay then, you don't want to calculate anything");
							continue;
						}
						
						System.out.println(odds);
						odds /= 100;
						System.out.println(odds);
						
						if (odds <= 0.5) {
							odds = 1 - odds;
						}
						
						double lossChance = odds;
						
						for (int i = 0; i < calcNums; i++) {
							System.out.println("Odds of going on a " + (i + 1) + " loosing streak = " + (lossChance * 100) + "%");
							lossChance *= odds;
						}
						System.out.println();
						break;
						
					}
					
					
				}
				continue;
				
			} else {
				System.out.println("\'" + input + "\' is not recognized as a command");
				continue;
			}
			
			break;
		}
		
	}

	private static void getStrat(double payout) {
		
		double betIncrease = ((payout / (payout - 1)) - 1) * 100;
		System.out.println("\nYou should increase your bet: " + betIncrease + "% on loss\n");
		
	}

}
