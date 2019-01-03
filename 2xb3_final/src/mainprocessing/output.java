package mainprocessing;

import java.io.FileNotFoundException;

import abstractDataTypes.cityADT;
import abstractDataTypes.districtADT;
import graphing.Graph;

public class output 
{
    private cityADT chicago;
    private String ID;
    private Graph nearby = new Graph();
    
    public output(String filename) throws FileNotFoundException
	{
		this.chicago = processing.fullCityScore("Chicago", filename);
		this.ID = filename.substring(0, filename.length()-4);
	}
    
    public void printBeats(int districtID)
    {
        districtADT d = new districtADT(districtID);
        for ( int i = 0 ; i < this.chicago.getdists().size(); i++)
        {
            if( this.chicago.getdists().get(i).getId() == districtID){
                d = this.chicago.getdists().get(i);
                break;
            }
        }
        for( int i = 0 ; i < d.getbeats().size(); i ++)
        {
            //Assuming beatADT.toString() prints something like x% light y%medium z%severe xyz%weightedAverage
            System.out.println(d.getbeats().get(i).toString());
        }
        
    }
    
    public void printDistricts()
    {
        for(int i = 0; i < this.chicago.getdists().size(); i++)
        {
            //Assuming beatADT.toString() prints something like x% light y%medium z%severe xyz%weightedAverage

            System.out.println(this.chicago.getdists().get(i).toString());
        }
    }
    
    public void printDistrict(int i) 
    {
    		System.out.println(chicago.getdist(i-1).toString());
    }
    
    public void printNearby(int district)
    {
    	for (int w : nearby.adj(district)) 
    	{
    		for (int i = 0; i < this.chicago.getdists().size(); i++) 
    		{
    			if (this.chicago.getdists().get(i).getId() == w) 
    			{
					printDistrict(this.chicago.getdists().get(i).getId());
					printDistScore(this.chicago.getdists().get(i).getId());
					System.out.println(" ");
				}
			}
		}
    }
    
    public void printDistScore(int i) {
    		System.out.println("District " + i + " has a crime rating of " + Math.round(chicago.getdist(i-1).getScore()) + "\nCompared to a Chicago district average of " + Math.round(chicago.getAvgScore()));
    }
    
    public String getProfile() {
    		return ID;
    }
    
    public void BestDist() {
    		districtADT a = chicago.getdist(0);
    		int best = 0;
    		for (int i = 0; i < chicago.getdists().size(); i++) {
    			if (chicago.getdist(i).getTotalPer() < a.getTotalPer()) {
    				a = chicago.getdist(i);
    				best = i;
    			}
    		}
    		printDistrict(best+1);
    		printDistScore(best+1);
    }
    
    public int BestDistInt() {
		districtADT a = chicago.getdist(0);
		int best = 0;
		for (int i = 0; i < chicago.getdists().size(); i++) {
			if (chicago.getdist(i).getTotalPer() < a.getTotalPer()) {
				a = chicago.getdist(i);
				best = i;
			}
		}
		return best+1;
    }

}
