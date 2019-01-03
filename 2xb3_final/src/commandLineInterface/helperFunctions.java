package commandLineInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mainprocessing.output;
import mainprocessing.processing;

public class helperFunctions 
{
	private static output test;
	private static List<String> low = new ArrayList<String>();
	private static List<String> mid = new ArrayList<String>();
	private static List<String> high = new ArrayList<String>();
	
	public static void clearScreen() 
	{  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public static void displayMenu()
	{
		System.out.println("Welcome to CCLA!");
		System.out.println("Current profile: "+ test.getProfile() + "\n");
		System.out.println("1. examine district");
		System.out.println("2. find best district");
		System.out.println("3. user preference");
		System.out.println("4. info");
		System.out.println("5. exit");
		System.out.print("\n\t> ");
	}
	
	public static void menu() throws IOException
	{
	clearScreen();
	System.out.println("Welcome to CCLA!\n\nLoading...");
	processing.storesort();
	test = new output("student.txt");
    	int input;
    	do
    	{
    		clearScreen();
    		displayMenu();
    		input = getInput();
    		
    		switch (input)
    		{
    			case 1:
    				selectDistrict();
    				break;
    			case 2:
    				bestDistrict();
    				break;
    			case 3:
    				chooseScore(test.getProfile() + ".txt");
    				break;
    			case 4:
    				help();
    				break;
    			case 5:
    				clearScreen();
    				System.exit(0);
    				break;
    			default:
    				break;
    		}
    	}while(0 < input && input <= 5);
	}
	
	private static void bestDistrict() throws IOException {
		clearScreen();
		int input;
		int input2;
		boolean repeat = true;
		do {
			test.BestDist();
			System.out.println("\nNearby Districts:");
			test.printNearby(test.BestDistInt());
			System.out.println("\nPress 0 to return to menu");
			System.out.println("Input district ID to get more information (1-25)");
			System.out.print("\n\t> ");
			input = getInput();
    			if (input == 0) {repeat = false;}
    		}while((0 >= input && input > 25) && repeat);
    	if (input != 0)
    	{
    		test.printDistrict(input);
    		System.out.println("");
    		test.printBeats(input);
    		System.out.println("");
    		test.printDistScore(input);
    		System.out.println("\nPress 8 to view statistics for nearby districts");
    		System.out.println("Press 0 to return to menu");
    		System.out.print("\n\t> ");
    		input2 = getInput();
    		if (input2 == 8) {
    			clearScreen();
    			System.out.println("Nearby Districts:");
    			test.printNearby(input);
    			System.out.println("\nPress enter to return to menu");
    			System.in.read();
    		}
    		else {}
    	}
    	//else we skip as 0 is our back out input
	}

	private static void help() throws IOException {
		clearScreen();
		System.out.println("Welcome to CCLA!\n");
		System.out.println("Welcome to City Crime Livabiliy Analyzer (CCLA)");
		System.out.println("Crime output is given as percentages of the the types of crimes happening");
		System.out.println("out of the total crimes in an area, and a score of that area\n");
		System.out.println("The percentages are split into petty, moderate, and severe crimes, to give");
		System.out.println("the user an idea on what types of crimes are occuring in a given area.");
		System.out.println("example: 49% petty means that 49% of the total crimes in that district are petty\n");
		System.out.println("Scoring is also used to get an idea on how a certain district compares to another district");
		System.out.println("The score is made based on the addition of the number of crimes in an area and");
		System.out.println("their ranking, so a higher score means it is a worse area (highly based on population!)");
		System.out.println("The score will be given along with a city average for comparison.");
		System.out.println("example: a district with a score of 70000 is better than a district with a score of 80000\n");
		System.out.println("This software was written by:");
		System.out.println("Giacomo Loparco");
		System.out.println("Jacob Gordon");
		System.out.println("Mohammad Hussain");
		System.out.println("Lucas Zacharewicz\n");
		System.out.println("\nPress enter to return to menu");
		System.in.read();
	}
	
	private static void chooseScore(String filename) throws IOException {
	int input;
	do {
		clearScreen();
		System.out.println("Profile: " + filename.substring(0, filename.length()-4));
		Scanner a = new Scanner(new File(filename));
		System.out.println("\nSevere Crimes: " + a.nextLine());
		System.out.println("\nModerate Crimes: " + a.nextLine());
		System.out.println("\nPetty Crimes: " + a.nextLine() + "\n");
		a.close();
		System.out.println("1. student");
		System.out.println("2. elderly");
		System.out.println("3. family");
		System.out.println("4. single adult");
		System.out.println("5. custom profile\n");
		System.out.println("Press 0 to load current profile");
		System.out.print("\n\t> ");
		
		input = getInput();
		switch (input)
		{
			case 0:
				test = new output(filename);
				break;
			case 1:
				chooseScore("student.txt");
				break;
			case 2:
				chooseScore("elderly.txt");
				break;
			case 3:
				chooseScore("family.txt");
				break;
			case 4:
				chooseScore("singleAdult.txt");
				break;
			case 5:
				File file = new File("custom.txt");
				file.delete();
				customScore(0);
				break;
			default:
				break;
		}
	}while(0 >= input && input > 5);
		
	}
	
	private static void customScore(int i) throws IOException {
		String[] a = {"ARSON","ASSAULT","BATTERY","BURGLARY","CONCEALED CARRY LICENSE VIOLATION","CRIM SEXUAL ASSAULT","CRIMINAL DAMAGE","CRIMINAL TRESPASS","DECEPTIVE PRACTICE","DOMESTIC VIOLENCE","GAMBLING","HOMICIDE","HUMAN TRAFFICKING","INTERFERENCE WITH PUBLIC OFFICER","INTIMIDATION","KIDNAPPING","LIQUOR LAW VIOLATION","MOTOR VEHICLE THEFT","NARCOTICS","NON - CRIMINAL","NON-CRIMINAL","NON-CRIMINAL (SUBJECT SPECIFIED)","OBSCENITY","OFFENSE INVOLVING CHILDREN","OTHER NARCOTIC VIOLATION","OTHER OFFENSE","PROSTITUTION","PUBLIC INDECENCY","PUBLIC PEACE VIOLATION","RITUALISM","ROBBERY","SEX OFFENSE","STALKING","THEFT","WEAPONS VIOLATION"};
		int input;
		if (i == a.length) {
			File file = new File("custom.txt");
			file.delete();
			BufferedWriter writer = new BufferedWriter(new FileWriter("custom.txt", true));
			if (high.size() > 0) {
				writer.write(high.get(0));
				for (int j = 1; j < high.size(); j++) {
					writer.append("," + high.get(j));
				}
				writer.append("\n");
			}
			else {
				writer.append("\n");
			}
			if (mid.size() > 0) {
				writer.append(mid.get(0));
				for (int j = 1; j < mid.size(); j++) {
					writer.append("," + mid.get(j));
				}
				writer.append("\n");
			}
			else {
				writer.append("\n");
			}
			if (low.size() > 0) {
				writer.append(low.get(0));
				for (int j = 1; j < low.size(); j++) {
					writer.append("," + low.get(j));
				}
			}
			else {
				writer.append("\n");
			}
			writer.close();
			low.clear();
			mid.clear();
			high.clear();
			chooseScore("custom.txt");
		}
		else {
		do{
			clearScreen();
			System.out.println("Please rank the following crime:");
			System.out.println("\n\t" + a[i]);
			System.out.println("1. petty crime");
			System.out.println("2. moderate crime");
			System.out.println("3. severe crime");
			System.out.println("Press 0 to exit");
			System.out.print("\n\t> ");
			
			input = getInput();
			switch (input)
			{
				case 0:
					File file = new File("custom.txt");
					file.delete();
					low.clear();
					mid.clear();
					high.clear();
					break;
				case 1:
					low.add(a[i]);
					customScore(i+1);
					break;
				case 2:
					mid.add(a[i]);
					customScore(i+1);
					break;
				case 3:
					high.add(a[i]);
					customScore(i+1);
					break;
				default:
					break;
			}
			
		}while(0 >= input && input > 3);
		}
	}

	public static void selectDistrict() throws IOException
	{
    	int input;
    	int input2;
    	boolean repeat = true;
    	do
    	{
    		clearScreen();
    		System.out.println("Select a district number (1 - 25)");
    		System.out.print("\n\t> ");
    		input = getInput();
    		if (input == 0) {repeat = false;}
    	}while((0 >= input && input > 25) && repeat);
    	
    	/*
    	 * Below should be the function call to the method that displays info of
    	 * the inputed district.
    	 * Takes argument "input"
    	 */
    	if (input != 0)
    	{
    		clearScreen();
    		test.printDistrict(input);
    		System.out.println("");
    		test.printBeats(input);
    		System.out.println("");
    		test.printDistScore(input);
    		System.out.println("\nPress 8 to view statistics for nearby districts");
    		System.out.println("Press 0 to return to menu");
    		System.out.print("\n\t> ");
    		input2 = getInput();
    		if (input2 == 8) {
    			clearScreen();
    			System.out.println("Nearby Districts:");
    			test.printNearby(input);
    			System.out.println("\nPress enter to return to menu");
    			System.in.read();
    		}
    		else {}
    	}
    	//else we skip as 0 is our back out input
	}
	
	public static int getInput()
	{
		Scanner userInput = new Scanner(System.in);
    	int input;
    	
    	while(!userInput.hasNextInt())
    	{
    		userInput.next();
    	}
    	input = userInput.nextInt();
    	return input;
	}
}
