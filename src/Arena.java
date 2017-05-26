import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/** This program is a simple game to be used as a vehicle for teaching programming concepts. And for fun.
 * 
 * @author Eric Parkerson
 * @version 1.0
 */
public class Arena {

	/**
	 * The first function called when the program starts.
	 * 
	 * @param args - an Array of Strings containing the arguments passed in from the command line.
	 */
	public static void main(String[] args) {
		/* Welcome to the arena game! Here, you select how large the battlefield is, how many combatants there will be, and then watch them fight
		 *  to the death! */
		
		// Environment Variables
		int battleGridSize = 0;
		int numCombatants = 0;
		String cellHorizontalBarrier = "------";
		
		// Gladiator Variables
		String[] fighterName;
		int[] fighterHP;
        int[] fighterAttack;
        int[] fighterDefense;
        int[] fighterSpeed;
        int[] fighterEnergy;            
        int[] fighterXLoc;
        int[] fighterYLoc;
        String[][] fighterInventory;
        int fightersRemaining; // tracks the number of fighters still active in combat

        // A list of names to be randomly pulled from when generating combatants.
        String[] FIGHTER_NAME_POOL = { "Sparticus", "Thor", "Wonder Woman", "Hercules", "Goku", "Hercule", "Vegeta", "Black Widow", "James Raynor",
        								"Uther", "Android 18", "Kerrigan", "Katniss", "Chuck Norris", "George Lopez", "Howard Stern", "Your Mother",
        								"Fuck, I", "Kirby", "Link" };
        Random randomGenerator = new Random();

        // Item Stat Variables
        // for the sake of the first version of the game, all items will provide a +20 to their particular stat.
        String[] itemName = { "Armor", "Sword", "Shield", "Chocobo Feather", "Red Bull" }; // Buffs HP, Attack, Defense, Speed, and Energy, respectively.
        boolean[] itemAvailable = { true, true, true, true, true }; // the array that tracks if the item is already picked up or not.
        int[] itemXLoc = new int[5];
        int[] itemYLoc = new int[5];
        
        // Input Variables
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // "wrapping" the stdin data feed in a more useful object
        String inputBuffer = "";
        boolean customizeCombatants = false;
        
        System.out.println("Welcome to the arena! Here, gladiators will fight for their lives as you watch for your amusement. That's what having"
        					+ " money and power is all about, right?");
        
        // Ask for the board size (from 4x4 to 10x10 allowable)
        while (battleGridSize < 4 || battleGridSize > 10) {
            System.out.print("How big should the battlegrid be? Pick anywhere from 4 to 10: ");
            try {
            	inputBuffer = input.readLine();
            	battleGridSize = Integer.parseInt(inputBuffer);
            } catch(IOException ex) {
            	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
            	System.exit(1);
            } catch(NumberFormatException ex) {
            	battleGridSize = 0; // They typed in something that wasn't an integer, so we just take it as a 0.
            }
            
            if (battleGridSize < 4 || battleGridSize > 10) {            	
                System.out.println("Come again? That wasn't an option you could pick.");
            }
        }
        
        // Ask for the number of combatants (2-8 allowable)
        while (numCombatants < 2 || numCombatants > 8) {
            System.out.print("How many gladiators should fight? Pick from 2 to 8: ");
            try {
            	inputBuffer = input.readLine();
            	numCombatants = Integer.parseInt(inputBuffer);
            } catch (IOException ex) {
            	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
            	System.exit(1);
            } catch (NumberFormatException ex) {
            	numCombatants = 0;
            }

            if (numCombatants < 2 || numCombatants > 8) {
                System.out.println("Give me an actual number between 2 and 8. I know numbers are hard, but get it together.");
            }
        }
        
        // initialize the gladiator stat arrays.
        fighterName = new String[numCombatants];
        fighterHP = new int[numCombatants];
        fighterAttack = new int[numCombatants];
        fighterDefense = new int[numCombatants];
        fighterSpeed = new int[numCombatants];
        fighterEnergy = new int[numCombatants];
        fighterXLoc = new int[numCombatants];
        fighterYLoc = new int[numCombatants];
        fighterInventory = new String[numCombatants] []; // each fighter could potentially pick up multiple items.
        for (int i=0; i < numCombatants; i++) {
        	fighterInventory[i] = new String[5]; // a fighter could conceivably hold all five items, so give them room to do so.
        }

        System.out.println("Yes sir! " + numCombatants + " gladiators will be doing battle in a " + battleGridSize + " by " + battleGridSize + " arena!");
        
        // Would you like to customize your combatants? 
        inputBuffer = "";
        while(inputBuffer.compareToIgnoreCase("Y") != 0 && inputBuffer.compareToIgnoreCase("N") != 0) {
            System.out.print("Would you like to customize your gladiators? (Y/N) ");
            try {
            	inputBuffer = input.readLine();
            } catch (IOException ex) {
            	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
            	System.exit(1);
            }

            if (inputBuffer.compareToIgnoreCase("Y") != 0 && inputBuffer.compareToIgnoreCase("N") != 0) {
               System.out.println("It's a yes or no question and you still managed to get it wrong. How did you get come into your money and power!?");
            } else if (inputBuffer.compareToIgnoreCase("Y") == 0) {
                customizeCombatants = true;
            }
        }
        
        // For each combatant requested...
        for (int i = 0; i < numCombatants; i++) {
            // If you'd like to customize combatants, collect stats - Name, HP, Speed, Attack, Defense, Energy. 
            if (customizeCombatants) {
                System.out.println("Today, " + numCombatants + " gladiators will be doing battle in a " + battleGridSize + " by " + battleGridSize
                					+ " arena!\n");

                // List the gladiators that have already been created
                for (int j = 0; j < i; j++) {
                    System.out.println("Name: " + fighterName[j] + " HP: " + fighterHP[j] + " Attack: " + fighterAttack[j] + " Defense: "
                    					+ fighterDefense[j] + " Speed: " + fighterSpeed[j] + " Energy: " + fighterEnergy[j]);
                }

                // Name the next gladiator
                System.out.print("Name gladiator " + (i + 1) + ": ");
                try {
                	inputBuffer = input.readLine();
                } catch (IOException ex) {
                	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                	System.exit(1);
                }
                fighterName[i] = inputBuffer;

                // initialize all stats to 0 to start with.
                fighterHP[i] = fighterAttack[i] = fighterDefense[i] = fighterSpeed[i] = fighterEnergy[i] = 0;

                // Health Points
                while (fighterHP[i] < 1 || fighterHP[i] > 200) {
                    System.out.print("Enter " + fighterName[i] + "'s Hit Points (1-200): ");
                    try {
                    	inputBuffer = input.readLine();
                    	fighterHP[i] = Integer.parseInt(inputBuffer);
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    } catch (NumberFormatException ex) {
                    	fighterHP[i] = 0;
                    }

                    if (fighterHP[i] < 1 || fighterHP[i] > 200) {
                        System.out.println("I need a number within the range, genius.");
                    }
                }

                // Attack
                while (fighterAttack[i] < 1 || fighterAttack[i] > 50) {
                	System.out.print("Enter " + fighterName[i] + "'s Attack Power (1-50): ");
                	try {
                    	inputBuffer = input.readLine();
                    	fighterAttack[i] = Integer.parseInt(inputBuffer);
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    } catch (NumberFormatException ex) {
                    	fighterAttack[i] = 0;
                    }

                    if (fighterAttack[i] < 1 || fighterAttack[i] > 50) {
                        System.out.println("I need a number within the range, genius.");
                    }
                }

                // Defense
                while (fighterDefense[i] < 1 || fighterDefense[i] > 50) {
                    System.out.print("Enter " + fighterName[i] + "'s Defense Ability (1-50): ");
                    try {
                    	inputBuffer = input.readLine();
                    	fighterDefense[i] = Integer.parseInt(inputBuffer);
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    } catch (NumberFormatException ex) {
                    	fighterDefense[i] = 0;
                    }

                    if (fighterDefense[i] < 1 || fighterDefense[i] > 50) {
                        System.out.println("I need a number within the range, genius.");
                    }
                }

                // Speed
                while (fighterSpeed[i] < 1 || fighterSpeed[i] > 50) {
                    System.out.print("Enter " + fighterName[i] + "'s Speed Score (1-50): ");
                    try {
                    	inputBuffer = input.readLine();
                    	fighterSpeed[i] = Integer.parseInt(inputBuffer);
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    } catch (NumberFormatException ex) {
                    	fighterSpeed[i] = 0;
                    }

                    if (fighterSpeed[i] < 1 || fighterSpeed[i] > 50) {
                        System.out.println("I need a number within the range, genius.");
                    }
                }

                // Energy
                while (fighterEnergy[i] < 1 || fighterEnergy[i] > 100) {
                    System.out.print("Enter " + fighterName[i] + "'s Endurance Score (1-100): ");
                    try {
                    	inputBuffer = input.readLine();
                    	fighterEnergy[i] = Integer.parseInt(inputBuffer);
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    } catch (NumberFormatException ex) {
                    	fighterEnergy[i] = 0;
                    }

                    if (fighterEnergy[i] < 1 || fighterEnergy[i] > 100) {
                        System.out.println("I need a number within the range, genius.");
                    }
                }

                inputBuffer = "";
                while (inputBuffer.compareToIgnoreCase("Y") != 0 && inputBuffer.compareToIgnoreCase("N") != 0) {
                    System.out.print("\nThis gladiator is ready! Customize the next gladiator? (Y/N) ");
                    try {
                    	inputBuffer = input.readLine();
                    } catch (IOException ex) {
                    	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
                    	System.exit(1);
                    }

                    if (inputBuffer.compareToIgnoreCase("Y") != 0 && inputBuffer.compareToIgnoreCase("N") != 0) {
                        System.out.println("It's a yes or no question and you still managed to get it wrong. How did you get come into your"
                        					+ " money and power!?");
                    } else if (inputBuffer.compareToIgnoreCase("N") == 0) {
                        customizeCombatants = false; // change flag to false so the random stat generator kicks in.
                    }
                }
            } else { // If not customizing, randomly generate stats
                fighterName[i] = FIGHTER_NAME_POOL[randomGenerator.nextInt(20)];

                // Search to see if this name has already been used for another gladiator. If it has, reassign a random name and start checking
                // 	it against the list again.
                for (int j = 0; j < i; j++) {
                    if (fighterName[i] == fighterName[j]) {
                        fighterName[i] = FIGHTER_NAME_POOL[randomGenerator.nextInt(20)];
                        j = -1; // We want the for loop counter, j, to start at 0, but to account for the j++ that will execute before the next
                        		//  loop starts, we need to set it to -1.
                    }
                }
                fighterHP[i] = randomGenerator.nextInt(200) + 1; // generates number from 0-199. Want a number from 1-200, so we add 1.
                fighterAttack[i] = randomGenerator.nextInt(50) + 1; // generates a number from 0-49. Want a number from 1-50, so we add 1.
                fighterDefense[i] = randomGenerator.nextInt(50) + 1; // similar as comment above. Applies to the rest of fighter stat assignments.
                fighterSpeed[i] = randomGenerator.nextInt(50) + 1;
                fighterEnergy[i] = randomGenerator.nextInt(100) + 1;
            }
        } // end combatant creation for-loop.
        
        // Generate world 
        // Place combatants (allow no overlap of positions)
        for (int i = 0; i < numCombatants; i++) {
            fighterXLoc[i] = randomGenerator.nextInt(battleGridSize);
            fighterYLoc[i] = randomGenerator.nextInt(battleGridSize);

            for (int j = 0; j < i; j++) {
                // if current fighter ([i]) is in the same position any other fighter ([j]), generate a new starting position for current fighter
            	//   and check it against all others.
                if (fighterXLoc[i] == fighterXLoc[j] && fighterYLoc[i] == fighterYLoc[j]) {
                    fighterXLoc[i] = randomGenerator.nextInt(battleGridSize);
                    fighterYLoc[i] = randomGenerator.nextInt(battleGridSize);
                    j = -1; // We want the for loop counter, j, to start at 0, but to account for the j++ that will execute before the next loop 
                    		//  starts, we need to set it to -1.
                }
            }
        }
        
        /* Place items on battlefield. Allow no overlap with other items/combatants */
        for (int i=0; i < 5; i++) {
            boolean validPosition; // flag to be used in determining if there is an item location conflict with another item or combatant.
            do {
                validPosition = true; // reset the flag on each run through.

                itemXLoc[i] = randomGenerator.nextInt(battleGridSize);
                itemYLoc[i] = randomGenerator.nextInt(battleGridSize);

                for (int j = 0; j < i && validPosition; j++) {
                    // if current item ([i]) is in the same position any other item ([j]), generate a new starting position for current item and check it against all others.
                    if (itemXLoc[i] == itemXLoc[j] && itemYLoc[i] == itemYLoc[j]) {
                        validPosition = false;
                    }
                }

                for (int j = 0; j < numCombatants && validPosition; j++) {
                    if (itemXLoc[i] == fighterXLoc[j] && itemYLoc[i] == fighterYLoc[j]) {
                        validPosition = false;
                    }
                }
            } while (!validPosition);
        }
        
        // **** Present to the user the battlefield ****
        System.out.println("\n"); // put a little white space between the configuration and playing board.
        // Generate the horizontal barrier that will be used between each layer of the board.
        String horizontalBarrier = "-"; // need to start with the top corner piece.
        for (int i = 0; i < battleGridSize; i++) {
            horizontalBarrier += cellHorizontalBarrier; // then each battlegrid adds on dashes to make room for itself.
        }

        int linesOut = 0; // a counter to keep track of the number of complete lines of output written.
        for (int y = 0; y < battleGridSize; y++) { // Y-Axis for-loop
            System.out.print(horizontalBarrier);

            // Print out Gladiator status to the right of the arena board. This prints out the into at the top barrier of the coming row.
            if (linesOut == 0) {
                System.out.println("\t\tName\t\tHP\tAttack\tDefense\tSpeed\tEnergy");
            } else if (linesOut <= numCombatants) {
                String name = fighterName[linesOut-1];
                if (name.length() < 8) {
                    name += "\t";
                }

                System.out.println("\t" + linesOut + "\t" + name + "\t" + fighterHP[linesOut - 1] + "\t" + fighterAttack[linesOut - 1] + "\t"
                					+ fighterDefense[linesOut - 1] + "\t" + fighterSpeed[linesOut - 1] + "\t" + fighterEnergy[linesOut - 1]);
            } else {
                System.out.println("");
            }
            linesOut++; // A complete line has been output. Increment the counter.

            System.out.print("|"); // start each row with a vertical line, then each cell will be responsible for rendering and closing itself.
            for (int x = 0; x < battleGridSize; x++) { // X-Axis for loop
                String cellContents = "";
                String prePad = ""; // variables to set spacing inside each cell.
                String postPad = "";
                
                // Find combatants at this location
                for (int i = 0; i < numCombatants; i++) { // combatants for loop
                    if (fighterXLoc[i] == x && fighterYLoc[i] == y) {
                        cellContents += (i + 1); // place the number representing the combatant in this cell
                    }
                }

                // Find items at this location and label them accordingly.
                for (int i = 0; i < 5; i++) {
                    int itemLetterBase = 65; // ASCII code for "A"                   
                    char itemLetter =  (char) (itemLetterBase + i);
                    if (itemXLoc[i] == x && itemYLoc[i] == y) {
                        cellContents += String.valueOf(itemLetter);
                    }
                }

                switch (cellContents.length()) {
                    case 0: prePad = "   "; postPad = "  "; break;
                    case 1: prePad = "  "; postPad = "  "; break;
                    case 2: prePad = "  "; postPad = " "; break;
                    case 3: prePad = " "; postPad = " "; break;
                    case 4: prePad = " "; break;
                }

                System.out.print(prePad + cellContents + postPad + "|");

            } // End of X-Axis For Loop

            // Print out Gladiator status to the right of the arena board. This prints out the info to the right of the actual board row.
            if (linesOut <= numCombatants) {
                String name = fighterName[linesOut-1];
                if (name.length() < 8) {
                    name += "\t";
                }

                System.out.println("\t" + linesOut + "\t" + name + "\t" + fighterHP[linesOut - 1] + "\t" + fighterAttack[linesOut - 1] + "\t"
                					+ fighterDefense[linesOut - 1] + "\t" + fighterSpeed[linesOut - 1] + "\t" + fighterEnergy[linesOut - 1]);
            } else {
                System.out.println("");
            }
            linesOut++; // A complete line has been output. Increment the counter.
        } // End of Y-Axis For Loop

        System.out.println(horizontalBarrier); // End of rendering the board and the gladiator names.
        System.out.println("Items: ");

        for (int i=0; i < 5; i++) {
            if (i != 0) {
                System.out.print(" | ");
            }

            int itemLetterBase = 65; // 65 is the ASCII code for capitol 'A'. Adding to that number will get the next letters in the alphabet.
            char itemLetter = (char) (itemLetterBase + i);

            System.out.print(String.valueOf(itemLetter) + " - " + itemName[i]);
        }
        System.out.println("");

        // begin the game! One execution of this do-while loop is one turn. 
        do {
        	System.out.println("\nPress any key to continue...");
        	System.out.println("\n");
        	
            try {
            	inputBuffer = input.readLine();
            } catch (IOException ex) {
            	System.err.println("Something went wrong reading data from the user!! " + ex.getMessage());
            	System.exit(1);
            }
            
        	StringBuilder turnSummary = new StringBuilder();
          	// Move combatants to new positions
        	for (int i=0; i < numCombatants; i++) {
        		if (fighterHP[i] > 0) { // only conscious fighters move.
	        		fighterXLoc[i] += randomGenerator.nextInt(3) - 1; // Cause the fighter to move left, right, or not at all on the X-axis.
	        		if (fighterXLoc[i] >= battleGridSize) {
	        			fighterXLoc[i] = battleGridSize - 1; // keep them within the right barrier of the arena
	        		} else if (fighterXLoc[i] < 0) {
	        			fighterXLoc[i] = 0; // keep them within the left barrier of the arena
	        		}
	        		
	        		fighterYLoc[i] += randomGenerator.nextInt(3) - 1; // Cause the fighter to move up, down, or not at all on the Y-axis;
	        		if (fighterYLoc[i] >= battleGridSize) {
	        			fighterYLoc[i] = battleGridSize - 1; // keep them within the bottom barrier of the arena.
	        		} else if (fighterYLoc[i] < 0) {
	        			fighterYLoc[i] = 0; // keep them within the top barrier of the arena.
	        		}
	        		
	        		// if the fighter is carrying any items, move the item(s) with them. This is the only way items move.
	        		for (int inventoryItem = 0; inventoryItem < fighterInventory[i].length; inventoryItem++) {
	        			if (!(fighterInventory[i][inventoryItem] == null)) {
							for (int item = 0; item < itemName.length; item++) {
								if (fighterInventory[i][inventoryItem].equals(itemName[item])) {
									itemXLoc[item] = fighterXLoc[i];
									itemYLoc[item] = fighterYLoc[i];
									break;
								}
							}
	        			} else {
	        				break;
	        			}
	        		}
        		}
        	}
        	
        	// Handle when combatants encounter an item
        	for (int item=0; item < 5; item++) { // for each item on the battle field...
        		if (itemAvailable[item]) { // only look for who can grab the item if it isn't already picked up.
	        		int fightersHere = 0;
	        		int[] fightersHereIdList = new int[numCombatants]; // Could be up to all the fighters here
	        		
	        		for (int i=0; i < numCombatants; i++) { // see if a conscious fighter is in the same spot
	        			if (fighterXLoc[i] == itemXLoc[item] && fighterYLoc[i] == itemYLoc[item] && fighterHP[i] > 0) {
	        				fightersHereIdList[fightersHere] = i; // found a fighter! Remember their array index number
	        				fightersHere++; // update the counter of how many fighters are on this item
	        			}
	        		}
	        		
	        		// determine who successfully got the item
	        		if (fightersHere > 0) {
		        		int itemWinnerIndex = -1; // initialize the variable. Will be set before used.
		        		if (fightersHere > 1) { // if there are more than one fighter here, the fastest fighter gets it.
		        			int bestSpeedRoll = 0;
		        			for(int i=0; i < fightersHere; i++) {
		        				int currentFighter = fightersHereIdList[i];
		        				int speedRoll = randomGenerator.nextInt(50) + 1 + fighterSpeed[currentFighter];
		        			
		        				turnSummary.append(fighterName[currentFighter] + " dashed toward the " + itemName[item] + " with a roll of " + speedRoll + ".\n");
		        				
		        				if (speedRoll > bestSpeedRoll) {
		        					bestSpeedRoll = speedRoll;
		        					itemWinnerIndex = currentFighter;
		        				}
		        			}
		        		} else { // the only fighter here automatically gets the item
		        			itemWinnerIndex = fightersHereIdList[0];
		        		} // end more than one fighter here if statement
		        		
		    			// find the next empty space in the fighters inventory and add the item there.
		        		for (int i=0; i < 5; i++) {
		    				if (fighterInventory[itemWinnerIndex][i] == null) {
		    					fighterInventory[itemWinnerIndex][i] = itemName[item];
		    					break;
		    				}
		    			}
		        		
		        		// add a bonus to the appropriate stat of the fighter that picked up the item.
		        		if (itemName[item].equals("Armor")) {
		        			fighterHP[itemWinnerIndex] += 20;
		        		} else if (itemName[item].equals("Sword")) {
		        			fighterAttack[itemWinnerIndex] += 20;
		        		} else if (itemName[item].equals("Shield")) {
		        			fighterDefense[itemWinnerIndex] += 20;
		        		} else if (itemName[item].equals("Chocobo Feather")) {
		        			fighterSpeed[itemWinnerIndex] += 20;
		        		} else if (itemName[item].equals("Red Bull")) {
		        			fighterEnergy[itemWinnerIndex] += 20;
		        		} 
		        		
		        		turnSummary.append(fighterName[itemWinnerIndex] + " snatched up the " + itemName[item] + "!\n");
		        		itemAvailable[item] = false; // flag the item as picked up.
	        		} // end at least one fighter here if statement
        		} // end item is not picked up if statement
        	} // end item checking for loop
        	
        	// Combat Phase
        	// Initialize a flag array to keep track of who has taken their turn/.
        	boolean[] turnTaken = new boolean[numCombatants];
        	for (int i=0; i < numCombatants; i++) {
        		turnTaken[i] = false;
        	}
        	
        	// Now go through the list of fighters, looking for conscious fighters that share a space.
        	for (int combatant = 0; combatant < numCombatants; combatant++) {
        		if (fighterHP[combatant] > 0 && !turnTaken[combatant]) {
        			int fightersHere = 1;
	        		int[] fightersHereIdList = new int[numCombatants]; // Could be up to all the fighters here
	        		fightersHereIdList[0] = combatant; // add the combatant who's turn we're processing into the list of fighters in this space.
	        		
	        		// find the other conscious combatants in this space.
	        		for (int otherCombatant=combatant+1; otherCombatant < numCombatants; otherCombatant++) { // see if a conscious fighter is in the same spot
	        			if (fighterHP[otherCombatant] > 0 && fighterXLoc[combatant] == fighterXLoc[otherCombatant]
	        					&& fighterYLoc[combatant] == fighterYLoc[otherCombatant]) {
	        				fightersHereIdList[fightersHere] = otherCombatant; // found a fighter! Remember their array index number
	        				fightersHere++; // update the counter of how many fighters are on this item
	        			}
	        		}
        			
	        		// if there are multiple fighters here, calculate speed rolls to see in what order events happen.
	        		if (fightersHere > 1) {
	        			int[] fighterSpeedRoll = new int[fightersHere];
	        			for (int currentFighter = 0; currentFighter < fightersHere; currentFighter++) {
	        				fighterSpeedRoll[currentFighter] = randomGenerator.nextInt(50) + 1 + fighterSpeed[fightersHereIdList[currentFighter]];
	        			}
	        			
	        			// now have the gladiators fight, from fastest to slowest.
	        			for (int i = 0; i < fightersHere; i++) {
	        				int fastestRoll = 0;
	        				int attacker = -1; // initialize this variable. Will be set before it is used.
	        				for (int j = 0; j < fightersHere; j++) { // this loop determines who is fastest who hasn't yet taken their turn.
	        					if (fighterSpeedRoll[j] > fastestRoll && !turnTaken[fightersHereIdList[j]]) {
	        						fastestRoll = fighterSpeedRoll[j];
	        						attacker = fightersHereIdList[j];
	        					}
	        				}
	        				
	        				// attacker goes after the others in their square.
	        				for (int j = 0; j < fightersHere; j++) {
	        					// don't let fighters attack themselves or attack if they're unconscious.
	        					if (attacker != fightersHereIdList[j] && fighterHP[attacker] > 0) {
	        						int defender = fightersHereIdList[j];
	        						if (fighterHP[defender] > 0) { // attackers won't pummel an unconscious body.	        						
		        						int attackRoll = randomGenerator.nextInt(50) + 1 + fighterAttack[attacker];
		        						int defenseRoll = randomGenerator.nextInt(50) + 1 + fighterDefense[defender];

		        						int attackDamage = attackRoll - defenseRoll;
		        						if (attackDamage < 0) attackDamage = 0;
		        						
		        						turnSummary.append(fighterName[attacker] + ", with a speed roll of " + fastestRoll + ", dealt " + attackDamage
		        											 + " to " + fighterName[defender] + ". (Attack: " + attackRoll + ", Defense: " + defenseRoll
		        											 + ")\n");
		        						
		        						fighterHP[defender] -= attackDamage;
		        						
		        						// if this fighter has been rendered unconscious, they drop their items.
		        						if (fighterHP[defender] <= 0) {
		        							turnSummary.append(fighterName[defender] + " falls unconscious!\n");
		        							
		        							for (int itemInventory = 0; itemInventory < fighterInventory[defender].length; itemInventory++) {
		        								// if we've come to a null item in the fighters inventory, they're not carrying anything more.
		        	        					if (fighterInventory[defender][itemInventory] == null)
		        									break;
		        	        					// Match the item in the fighters inventory with the name of the item and make that item available again.
		        								for (int item = 0; i < itemName.length; item++) {
		        									if (fighterInventory[defender][itemInventory].equals(itemName[item])) {
		        										itemAvailable[item] = true;
		        										break;
		        									}
		        								}
		        							}
		        						}
	        						} // end defender is conscious if-statement
	        					} // end ensure attacker doesn't attack themself and attacker is conscious if-statement
	        				} // end of attack other fighters for-loop
	        				turnTaken[attacker] = true;
	        			} // end turn of "current fastest" fighter for-loop
	        		} // end more that one fighter in square if-statement
        		} // end if current fighter is conscious and hasn't taken their turn if-statement
        	} // end fighter iterating loop
        	
        	// Refresh the count of how many combatants remain
        	fightersRemaining = 0;
        	for (int i = 0; i < numCombatants; i++) {
        		if (fighterHP[i] > 0) { // if the fighter is still in the ring...
        			if (--fighterEnergy[i] <= 0) { // Deplete each fighter's energy and KO them if they have none left.
        				fighterHP[i] = 0;
        				
        				turnSummary.append(fighterName[i] + " falls unconscious from exhaustion!\n");
	        			
        				// have the fighter drop their inventory.
        				for (int itemInventory = 0; itemInventory < fighterInventory[i].length; itemInventory++) {
        					// if we've come to a null item in the fighters inventory, they're not carrying anything more.
        					if (fighterInventory[i][itemInventory] == null) {
								break;
							}
        					// Match the item in the fighters inventory with the name of the item and make that item available again.
        					for (int item = 0; item < itemName.length; item++) {
								if (fighterInventory[i][itemInventory].equals(itemName[item])) {
									itemAvailable[item] = true;
									break;
								}
							}
						}
        			} else { 
        				fightersRemaining++;
        			}
        		}
        	}
        	
        	// **** Present to the user the updated battlefield ****
            System.out.println("\n"); // put a little white space between the configuration and playing board.

            linesOut = 0; // a counter to keep track of the number of complete lines of output written.
            for (int y = 0; y < battleGridSize; y++) { // Y-Axis for-loop
                System.out.print(horizontalBarrier);

                // Print out Gladiator status to the right of the arena board. This prints out the into at the top barrier of the coming row.
                if (linesOut == 0) {
                    System.out.println("\t\tName\t\tHP\tAttack\tDefense\tSpeed\tEnergy");
                } else if (linesOut <= numCombatants) {
                    String name = fighterName[linesOut-1];
                    if (name.length() < 8) {
                        name += "\t";
                    }

                    System.out.println("\t" + linesOut + "\t" + name + "\t" + fighterHP[linesOut - 1] + "\t" + fighterAttack[linesOut - 1] + "\t"
                    					+ fighterDefense[linesOut - 1] + "\t" + fighterSpeed[linesOut - 1] + "\t" + fighterEnergy[linesOut - 1]);
                } else {
                    System.out.println("");
                }
                linesOut++; // A complete line has been output. Increment the counter.

                System.out.print("|"); // start each row with a vertical line, then each cell will be responsible for rendering and closing itself.
                for (int x = 0; x < battleGridSize; x++) { // X-Axis for loop
                    String cellContents = "";
                    String prePad = ""; // variables to set spacing inside each cell.
                    String postPad = "";
                    
                    // Find active combatants at this location
                    for (int i = 0; i < numCombatants; i++) { // combatants for loop
                        if (fighterXLoc[i] == x && fighterYLoc[i] == y && fighterHP[i] > 0) {
                            cellContents += (i + 1); // place the number representing the combatant in this cell
                        }
                    }

                    // Find items at this location and label them accordingly.
                    for (int i = 0; i < 5; i++) {
                        int itemLetterBase = 65; // ASCII code for "A"                   
                        char itemLetter =  (char) (itemLetterBase + i);
                        if (itemXLoc[i] == x && itemYLoc[i] == y) {
                            cellContents += String.valueOf(itemLetter);
                        }
                    }

                    switch (cellContents.length()) {
                        case 0: prePad = "   "; postPad = "  "; break;
                        case 1: prePad = "  "; postPad = "  "; break;
                        case 2: prePad = "  "; postPad = " "; break;
                        case 3: prePad = " "; postPad = " "; break;
                        case 4: prePad = " "; break;
                    }

                    System.out.print(prePad + cellContents + postPad + "|");

                } // End of X-Axis For Loop

                // Print out Gladiator status to the right of the arena board. This prints out the info to the right of the actual board row.
                if (linesOut <= numCombatants) {
                    String name = fighterName[linesOut-1];
                    if (name.length() < 8) {
                        name += "\t";
                    }

                    System.out.println("\t" + linesOut + "\t" + name + "\t" + fighterHP[linesOut - 1] + "\t" + fighterAttack[linesOut - 1] + "\t"
                    					+ fighterDefense[linesOut - 1] + "\t" + fighterSpeed[linesOut - 1] + "\t" + fighterEnergy[linesOut - 1]);
                } else {
                    System.out.println("");
                }
                linesOut++; // A complete line has been output. Increment the counter.
            } // End of Y-Axis For Loop

            System.out.println(horizontalBarrier); // End of rendering the board and the gladiator names.

            /***** DEBUG PURPOSES *****/
            // List the gladiators that have already been created -- Don't need this displayed at the moment.
            System.out.println("Items: ");

            for (int i=0; i < 5; i++) {
                if (i != 0) {
                    System.out.print(" | ");
                }

                int itemLetterBase = 65; // 65 is the ASCII code for capitol 'A'. Adding to that number will get the next letters in the alphabet.
                char itemLetter = (char) (itemLetterBase + i);

                System.out.print(String.valueOf(itemLetter) + " - " + itemName[i]);
            }
            System.out.println("");
            System.out.println(turnSummary + "\n");
        } while (fightersRemaining > 1);

        // Display the victor and his/her stats: That's it!
        System.out.println("The battle is decided!");
        // find which fighter is still alive, if any.
        int victor = -1;
        for (int i=0; i < numCombatants; i++) {
        	if (fighterHP[i] > 0 && fighterEnergy[i] > 0) {
        		victor = i;
        		break;
        	}
        }
        
        if (victor >= 0) {
        	System.out.println(fighterName[victor] + " won the day! How nice for that plebeian!");
        } else {
        	System.out.println("No one survived the arena! Looks like the only victor today was entertainment!");
        }
        
        System.out.println("Sleep well tonight in the knowledge that you have bodyguards to stop arena survivors or the families of the dead from killing you for your sadistic hedonism.");
        System.exit(0);
	}
}
