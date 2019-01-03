//This class is maintained by Lucas, if you have any problems with it contact him

package abstractDataTypes;

import java.util.List;
import java.util.ArrayList;

public class cityADT 
{
	private final String name;
	private List<districtADT> districts = new ArrayList<districtADT>();
	
	/*
	 * Default constructor for cityADT
	 */
	public cityADT()
	{
		this.name = "";
		this.districts = new ArrayList<districtADT>();
	}
	
	public cityADT(String name)
	{
		this.name = name;
		this.districts = new ArrayList<districtADT>();
	}
	
	public void add(districtADT a) {
		this.districts.add(a);
	}
	
	public String getName() {
		return name;
	}
	
	public List<districtADT> getdists(){
		return districts;
	}
	
	public districtADT getdist(int i) {
		return districts.get(i);
	}
	
	public double getAvgScore() {
		double acc = 0;
		int numd = 0;
		for (int i = 0; i < districts.size(); i++) {
			if (districts.get(i).getScore() > 30000) {
				acc += districts.get(i).getScore();
				numd++;	
			}
		}
		return Math.round(acc/numd);
	}
}
