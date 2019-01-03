package fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import abstractDataTypes.CrimeADT;

public class rawDataIO 
{
	//Array of Crime types
	public static CrimeADT[] crimes;
	
	//Integers to keep track of the number of lines and a counter for the crime's array index
	private static int lines;
	private static int curCrime;
	
	//One scanner for reading the amount of lines and one for reading the content of the file
	private static Scanner counter;
	private static Scanner reader;
	
	//Strings to hold the header, current line, and each section of the current line
	private static String curLine;
	private static String[] items;
	
	//Variables to hold CrimeADT's attributes
	private static String date;
	private static String[] dateSplit;
	private static boolean arrested;
	private static boolean domestic;
	private static int beat;
	private static int district;
	
	//Constants for array indexes 
	private static final int TYPE = 5;
	private static final int DESC = 6;
	private static final int LOC_DESC = 7;
	
	public static void store() 
	{
		try
		{
			crimes = null;
			lines = 0;
			curCrime = 0;
			counter = new Scanner(new File("Crimes_-_2001_to_present.csv"));
			counter.nextLine(); //Skips the first header line
			
			//Counts how many lines are in the file (size of crime array)
			while (counter.hasNextLine())
			{
				lines++;
				counter.nextLine();
			}
			
			counter.close();
			crimes = new CrimeADT[lines];
			
			reader = new Scanner(new File("Crimes_-_2001_to_present.csv"));
			reader.nextLine(); //Skips the first header line
			
			while (reader.hasNextLine())
			{
				//read the current line and split it into its different components
				curLine = reader.nextLine();
				items = curLine.split(",");

				//convert each attribute to its respective type
				arrested = Boolean.parseBoolean(items[8]);
				domestic = Boolean.parseBoolean(items[9]);
				beat = Integer.parseInt(items[10]);
				district = Integer.parseInt(items[11]);
				
				//splits the date into 3 respective parts
				date = items[2].substring(0, 10);
				dateSplit = date.split("/");
				
				//stores the current line's parts as a crime in the array
				if (district == Integer.parseInt(Integer.toString(beat).substring(0,Integer.toString(beat).length()-2)))
					crimes[curCrime] = new CrimeADT(dateSplit, items[TYPE], items[DESC], items[LOC_DESC], arrested, domestic, beat, district);	
				else {
					crimes[curCrime] = new CrimeADT(dateSplit, items[TYPE], items[DESC], items[LOC_DESC], arrested, domestic, beat, Integer.parseInt(Integer.toString(beat).substring(0,Integer.toString(beat).length()-2)));
				}
				curCrime++;
				//System.out.println(curCrime);
				//System.out.print(date + " " + items[TYPE] + " " + items[DESC] + " " + items[LOC_DESC] + " " +  String.valueOf(arrested) + " " + String.valueOf(domestic) + " " + String.valueOf(beat) + " " +  String.valueOf(district) + "\n");
			}
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found!");
		}
	}
}
