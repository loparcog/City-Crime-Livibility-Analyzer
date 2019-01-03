package abstractDataTypes;

public class CrimeADT {
  private final String[] date; // date[0] = month, date[1] =day, date[2] = year
  private final String type;
  private final String desc;
  private final String locdesc;
  private final boolean isArrested;
  private final boolean isDomestic;
  private final int beat;
  private final int district;

  public CrimeADT()
  {
	  this.date = new String[3];
	  this.type = null;
	  this.desc = null;
	  this.locdesc = null;
	  this.isArrested = false;
	  this.isDomestic = false;
	  this.beat = 0;
	  this.district = 0;
  }
  
  public CrimeADT(String[] date, String type, String desc, String locdesc, boolean arr, boolean dom, int beat, int dist)
  {
    this.date = date;
    this.type = type;
    this.desc = desc;
    this.locdesc = locdesc;
    this.isArrested = arr;
    this.isDomestic = dom;
    this.beat = beat;
    this.district = dist;
  }

  public String getDay()
  {
    return date[1];
  }

  public String getMonth()
  {
    return date[0];
  }

  public String getYear()
  {
    return date[2];
  }

  public String getType()
  {
      return type;
  }

  public String getDesc()
  {
    return desc;
  }

  public String getLocDesc()
  {
    return locdesc;
  }

  public boolean getIsArrested()
  {
    return isArrested;
  }

  public boolean getIsDomestic()
  {
    return isDomestic;
  }

  public int getBeat()
  {
    return beat;
  }

  public int getDist()
  {
    return district;
  }
  
  public String toString() {
	  return "Crime: " + type + " '" + desc + "', on " + date[1] + "/" + date[0] + "/" + date[2] + " at " + beat + ", " + district + ", '" + locdesc + "'. Arrested: " + isArrested + ", Domestic: " + isDomestic; 
  }

}
