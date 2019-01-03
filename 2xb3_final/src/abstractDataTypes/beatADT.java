//This class is maintained by Lucas, if you have any problems with it contact him


package abstractDataTypes;

import java.util.ArrayList;
import java.util.List;

public class beatADT 
{
	private final int id;
	private List<CrimeADT> crimes = new ArrayList<CrimeADT>();
	private double score;
	private double low;
	private double mid;
	private double high;
	
	/*
	 * Default constructor for the beatADT
	 * takes no arguments
	 */
	public beatADT()
	{
		this.id = 0;
		this.crimes = new ArrayList<CrimeADT>();
		this.score = 0;
		this.low = 0;
		this.mid = 0;
		this.high = 0;
	}
	
	/*
	 * Non-default constructor that takes
	 * Id as the only parameter
	 */
	public beatADT(int id)
	{
		this.id = id;
		this.crimes = new ArrayList<CrimeADT>();
		this.score = 0;
		this.low = 0;
		this.mid = 0;
		this.high = 0;
	}
	
	/*
	 * Default getter for the beat id
	 */
	public int getId()
	{
		return this.id;
	}
	
	/*
	 * Gets a specific crime at a given index
	 * Throws out of bounds exception on invalid length
	 */
	public CrimeADT getCrime(int i)
	{
		if (i < 0 || i >= this.crimes.size())
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		return this.crimes.get(i);
	}
	
	/*
	 * Getter for score of the beat
	 */
	public double getScore()
	{
		return this.score;
	}
	
	/*
	 * Setter for the score of the beat
	 */
	public void setScore(double score)
	{
		this.score = this.score + score;
	}
	
	/*
	 * Displays the string representation of a crime at index i
	 * Throws out of bounds exception on invalid length
	 */
	public String displayCrime(int i)
	{
		return this.crimes.get(i).toString();
	}
	
	
	public void add(CrimeADT a) {
		this.crimes.add(a);
	}
	
	public List<CrimeADT> getCrimes(){
		return crimes;
	}
	
	public void perLow(double percentage) {
		this.low = percentage;
	}
	
	public void perMid(double percentage) {
		this.mid = percentage;
	}
	
	public void perHigh(double percentage) {
		this.high = percentage;
	}
	
	public double getLow() {
		return Math.round(low * 10000.0)/100.0;
	}
	
	public double getMid() {
		return Math.round(mid * 10000.0) / 100.0;
	}
	
	public double getHigh() {
		return Math.round(high * 10000.0) / 100.0;
	}
	
	public double getTotalPer() {
		return Math.round((((high*7) + (mid * 2) + (low * 1))/10)*10000.0)/100.0;
	}
	
	public String toString()
	{
		return "Beat: " + this.getId() + ", " +this.getLow() + "% Petty, " + this.getMid() + "% Moderate, " + this.getHigh() + "% Severe, " + this.getTotalPer() + "% Overall";
	}
}
