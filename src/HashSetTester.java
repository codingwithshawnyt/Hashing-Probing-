import java.util.*;
import java.io.*;

/* YOU ALSO NEED THE nums.dat FILE */

public class HashSetTester
{
	//Change the type based off which lab
		//you are testing.
    Hash<Integer> table = new ChainingHash<>();

    public static void main(String[] args) {
    	HashSetTester tester = new HashSetTester();
    	tester.input();
    	tester.output();
    }
    
    public void input() {
        try
        {
        	int number;
            Scanner reader = new Scanner(new File("nums.dat"));
            System.out.println("Testing the \"add\" method:\n");
            while (reader.hasNextLine())
            {
            	number = reader.nextInt();
            	System.out.println("Adding: "+number+
            		((table.add(number))? " -> Success": " -> FAILED"));
            }
            pause();
        }
        catch (IOException e) {
            System.out.println("Error reading nums.dat>");
            System.exit(0);
        }
    }
    
    public void output() {
    	System.out.println("Attempting to add duplicates:\n");
    	System.out.print("Re-adding 5: ");
    	boolean added = table.add(5);
    	System.out.print(added+
    		((!added)? " -> Passed\n": " -> FAILED\n"));
    	pause();
    	
    	//Should have    0   1    2   3   4   5   6    7    8   9
    	//            | 39 | 0 | 22 | 3 | 0 | 5 | 6 | 17 | 28 | 9 |
    	/*
    	 *				toString: [39, 22, 3, 5, 6, 17, 28, 9]
    	 *		or
    	 *
    	 *			[0] -> 
    	 *			[1] ->
    	 *			[2] -> 22
    	 *			[3] -> 3
    	 *			[4] -> 
    	 *			[5] -> 5
    	 *			[6] -> 6
    	 *			[7] -> 17
    	 *			[8] -> 28
    	 *			[9] -> 39 -> 9
    	 *
    	 *				toString: 22, 3, 5, 6, 17, 28, 39, 9
    	 */
    	System.out.println("Testing the \"toString\" method:\n");
        System.out.println(table);
        pause();
        
        System.out.println("Testing the \"contains\" method:\n");
        System.out.println();
        System.out.println("Contains 40? "+table.contains(40)+
        	((!table.contains(40))? " -> Passed": " -> FAILED"));
        System.out.println("Contains 39? "+table.contains(39)+
        	((table.contains(39))? " -> Passed": " -> FAILED"));
        pause();
     	
     	System.out.println("Testing the \"remove\" method:");
    	System.out.println( "22 has been removed: " + table.remove(22)+
    			((!table.contains(22))? " -> Passed": " -> FAILED"));
    	System.out.println( "17 has been removed: " + table.remove(17)+
    			((!table.contains(17)&&table.contains(39))? " -> Passed": " -> FAILED"));
    	
        
    }
    
    public static void pause() {
    	System.out.println("\n--------------------------");
    	System.out.println("Press ENTER to continue...");
    	System.out.println("--------------------------");
    	new Scanner(System.in).nextLine();
    }
}