//This class is maintained by Lucas, if you have any problems with it contact him

package abstractDataTypes;

import java.util.ArrayList;
import java.util.List;

public class districtADT 
{
	private final int id;
	private List<beatADT> beats = new ArrayList<beatADT>();
	
	/*
	 * Default district constructor
	 * sets id to 0	
	 * sets beats to empty list  
	 */
	public districtADT()
	{
		this.id = 0;
		this.beats = new ArrayList<beatADT>();
	}
	
	/*
	 * Non-default constructor
	 * Takes int id as parameter
	 * sets beats to empty list
	 */
	public districtADT(int id)
	{
		this.id = id;
		this.beats = new ArrayList<beatADT>();
	}
	
	public void add(beatADT a) {
		this.beats.add(a);
	}
	
	public int getId() {
		return id;
	}
	
	public List<beatADT> getbeats(){
		return beats;
	}
	
	public beatADT getbeat(int i) {
		return beats.get(i);
	}
	
	public double getHigh() {
		double high = 0;
		for (int i = 0; i < beats.size(); i++) {
			high += beats.get(i).getHigh();
		}
		return Math.round((high/beats.size())*100.0)/100.0;
	}
	
	public double getMid() {
		double mid = 0;
		for (int i = 0; i < beats.size(); i++) {
			mid += beats.get(i).getMid();
		}
		return Math.round((mid/beats.size()) *100.0)/100.0;
	}
	
	public double getLow() {
		double low = 0;
		for (int i = 0; i < beats.size(); i++) {
			low += beats.get(i).getLow();
		}
		return Math.round((low/beats.size()) *100.0)/100.0;
	}
	
	public double getTotalPer() {
		double total = 0;
		for (int i = 0; i < beats.size(); i++) {
			total += beats.get(i).getTotalPer();
		}
		return Math.round((total/beats.size()) * 100.0) / 100.0;
	}
	
	public String toString()
	{
		return "District: " + this.getId() + ", " + this.getLow() + "% Petty, " + this.getMid() + "% Moderate, " + this.getHigh() + "% Severe, " + this.getTotalPer() + "% Overall";
	}
	
	public double getScore() {
		double acc = 0;
		for (int i = 0; i < beats.size(); i++) {
			acc += beats.get(i).getScore();
		}
		return acc;
	}
}
