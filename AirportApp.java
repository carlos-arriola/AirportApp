// Name: Arriola, Carlos
// Project: #5
// Due: 12/8/23
// Course: CS-2400-02-F23
//
// Description: Airport application that demonstrates the use of the 
//				Graph ADT, alongside various other data structures 
//				learned throughout the course.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Reads a variety of airport codes and distances, and evaluates minimum distances through
  	the use of the Graph ADT */
public class AirportApp {
	/** Main method 
	@param args Command line arguments 
	@throws FileNotFoundException If the file cannot be read*/
	public static void main(String args[]) throws FileNotFoundException
	{
		System.out.println("Airports v0.1 by C. Arriola\n");
		Scanner keyboard = new Scanner(System.in);
		
		File file = new File("airports.csv");
		Scanner sc = new Scanner(file);
				
		DictionaryInterface<String, String> airports = new HashedDictionary<String, String>(70);
		while (sc.hasNextLine())
		{
			String[] strArr = sc.nextLine().split(",");
			for (int i = 0; i < strArr.length - 1; i++)
			{
				airports.add(strArr[i], strArr[i + 1]);
			}
		}
		sc.close();
		
		file = new File("distances.csv");
		sc = new Scanner(file);
		GraphInterface<String> vertices = new DirectedGraph<>();
		while (sc.hasNextLine())
		{
			String[] strArr = sc.nextLine().split(",");
			vertices.addVertex(strArr[0]);
			vertices.addVertex(strArr[1]);
			vertices.addEdge(strArr[0], strArr[1], Integer.valueOf(strArr[2]));
		}
		sc.close();
		
		while (true)
		{
			System.out.print("Command? ");
			String input = keyboard.nextLine();
			while (input.length() != 1)
			{
				System.out.println("Invalid command");
				System.out.print("Command? ");
				input = keyboard.nextLine();
			}
			char ch = input.charAt(0);
			
			if (ch == 'E' || ch == 'e')
				break;
			
			switch(ch)
			{
			case 'Q', 'q':
				System.out.println("Airport code?");
				String code = keyboard.nextLine().toUpperCase();
				if (airports.getValue(code) != null)
					System.out.println(airports.getValue(code));
				else
					System.out.println("Airport code unknown");
				break;
			
			case 'D', 'd':
				System.out.println("Airport codes from to?");
				try 	// Validating user input
				{
					String[] codes = keyboard.nextLine().toUpperCase().split(" ");
					StackInterface<String> stack = new LinkedStack<>();
					
					if (codes[0].equals(codes[1])) {
						System.out.println("Airports are the same.");
						break;
					}
					
					if (airports.getValue(codes[0]) == null || airports.getValue(codes[1]) == null) {
						System.out.println("Airport codes unknown");
						break;
					}
					
					if (vertices.getCheapestPath(codes[0], codes[1], stack) == 0) {
						System.out.println("Airports not connected.");
						break;
					}
					stack.clear();
					
					System.out.println("The minimum distance between "+ airports.getValue(codes[0]) + " and " + 
							airports.getValue(codes[1]) + " is " + 	
							vertices.getCheapestPath(codes[0], codes[1], stack) + " through the route:");
					while(!stack.isEmpty())
						System.out.println(airports.getValue(stack.pop()));
					break;
				}
				catch (ArrayIndexOutOfBoundsException e)	// Assertion: Invalid number of arguments passed
				{
					System.out.println("Airport codes unknown.");
					break;
				}
				
				
			case 'H', 'h':
				System.out.println(
						  "Q Query the airport information by entering the airport code.\n"
						+ "D Find the minimum distance between two airports.\n"
						+ "H Display this message.\n"
						+ "E Exit.");
				break;
				
			default:
				System.out.println("Invalid command");
				break;
			}
		}
		keyboard.close();
	}
}
