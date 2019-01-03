package mainprocessing;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

//This module provides the score of a specific crime.
import abstractDataTypes.CrimeADT;
import abstractDataTypes.beatADT;
import abstractDataTypes.cityADT;
import abstractDataTypes.districtADT;
import fileIO.rawDataIO;
public class processing {
	
	private static int low;
	private static int mid;
	private static int high;
	
	public static void storesort() {
		rawDataIO.store();
		System.out.println("Read done!");
		
		// sort data by beat for easy storage
		Heap.sortHeap(rawDataIO.crimes, rawDataIO.crimes.length);
		System.out.println("Sort done!");
		
		// check that the sorting functioned correctly
		System.out.println("Beat Sort Check: " + isCrimesSorted(rawDataIO.crimes, "b"));
		System.out.println("District Sort Check: " + isCrimesSorted(rawDataIO.crimes, "d"));
	
	}
	
	public static cityADT fullCityScore(String cityname, String filename) throws FileNotFoundException {
		//read file for crime arrays
		
		Scanner lines = new Scanner(new File(filename));
		String[] A = lines.nextLine().split(",");
		String[] B = lines.nextLine().split(",");
		String[] C = lines.nextLine().split(",");
		lines.close();
		
		// initialize the first crime for future comparisons (since we compare to i-1)
		int year = Calendar.getInstance().get(Calendar.YEAR);
		low = 0;
		mid = 0;
		high = 0;
		double total = 0;
		
		cityADT city = new cityADT(cityname);
		districtADT area = new districtADT(rawDataIO.crimes[0].getDist());
		beatADT beat = new beatADT(rawDataIO.crimes[0].getBeat());
		beat.add(rawDataIO.crimes[0]);
		beat.setScore(getScore(A,B,C,rawDataIO.crimes[0], year));
		
		int i = 1;
		// loop through all other crimes
		while(i < rawDataIO.crimes.length) {
			
			// store beat when there are no more crimes in given beat
			if (rawDataIO.crimes[i].getBeat() != rawDataIO.crimes[i-1].getBeat()) {
				total = low + mid + high;
				beat.perLow(low/total);
				beat.perMid(mid/total);
				beat.perHigh(high/total);
				area.add(beat);
				low = 0; mid = 0; high = 0;
				beat = new beatADT(rawDataIO.crimes[i].getBeat());
				beat.setScore(getScore(A,B,C,rawDataIO.crimes[i], year));
				beat.add(rawDataIO.crimes[i]);
			}
			
			// store district when there are no more beats in given district
			if (rawDataIO.crimes[i].getDist() != rawDataIO.crimes[i-1].getDist()) {
				city.add(area);
				area = new districtADT(rawDataIO.crimes[i].getDist());
				
			}
			
			// store and score a crime in the current beat if it is the same as the last crime's beat
			if (rawDataIO.crimes[i].getBeat() == rawDataIO.crimes[i-1].getBeat()) {
				beat.add(rawDataIO.crimes[i]);
				beat.setScore(getScore(A,B,C,rawDataIO.crimes[i], year));
			}
			i++;
		}
		// add last areas not caught by loop
		area.add(beat);
		city.add(area);
		
		System.out.println("Score done!");
		return city;
	}
	
	// check if the beat or districts of a crime array are sorted, depending on input string a (d = dists, b = beats)
	public static boolean isCrimesSorted(CrimeADT[] list, String a) {
		boolean sorted = true;
		if (a == "d") {
			for (int k = 1; k < list.length; k++) {
				if (list[k].getDist() < list[k-1].getDist()) {
					sorted = false;
				}
			}
		}
		else if (a == "b") {
			for (int k = 1; k < list.length; k++) {
				if (list[k].getBeat() < list[k-1].getBeat()) {
					sorted = false;
				}
			}
		}
		return sorted;
	}
	
	//pass it the user generated preferences, and a crime
	private static double getScore(String[] A, String[] B, String[] C, CrimeADT Cr, int year) //A = severe, B = mid, C = petty
	{
		double rank = 0;
		//double score;
		if ( isIn(A, Cr.getType()) ) {
			rank = 3;
			high++;
		}
		else if ( isIn(B,Cr.getType())) {
			rank = 2;
			mid++;
		}
		else if ( isIn(C,Cr.getType())) {
			rank = 1;
			low++;
		}
		else
			System.out.println("Invalid Type for: " + Cr.getType());


		//the score is the severity of the crime, plus a modifier based on the year the crime was committed
		//score = rank / (1 + (difference of years)*0.1);

		//if there is a conviction the score is raised by 2
		if (rank != 0 && Cr.getIsArrested())
			rank++;
		int diff = year - Integer.parseInt(Cr.getYear());
		rank = rank / (1 + diff*0.1);
		return rank;
	}

	//Binary Search Function that returns true or false if the item is in the array

	 private static boolean isIn(String a[],String x){
	  	return binarySearch(a, 0, a.length, x);
	  }
	 
  private static boolean binarySearch(String a[], int l, int r, String x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
            if (mid >= a.length | mid < 0) 
            		return false;
            else if (a[mid].compareTo(x) == 0)
            		return true;
            else if (a[mid].compareTo(x) > 0)
            		return binarySearch(a, l, mid-1, x);
            else if (a[mid].compareTo(x) < 0)
            		return binarySearch(a, mid+1, r, x);
        }
        return false;
    } 
	
}

