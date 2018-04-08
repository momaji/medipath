package data_mangement;
/**
 * creates a class for the all operations that hospitals perform and their data, creates comparable objects
 */
public class ProviderDataObject implements Comparable<Object> {
	private final String DRGDef;
	private final int ProviderID;
	private final String ProviderName;
	private final String ProviderAddress;
	private final String ProviderCity;
	private final String ProviderState;
	private final int ProviderZip;
	private final String HospitalRRD; //Hospital Referral Region Description
	private final int TotalDischarges;
	private final double ACC;
	private final double ATP;
	private final double AMP;
	private double Distance = 0;
	/**
	 * Constructor for the hospitals.
	 * @param DRGdef - the procedure number of this object
	 * @param ProviderID - the provider ID of this object
	 * @param ProviderName - the providerName of this object 
	 * @param ProviderAddress - the address of this object
	 * @param ProviderCity - the city of this object
	 * @param ProviderState - the state of this object (literally in the USA)
	 * @param ProviderZip - the zip code of this object
	 * @param HospitalRRD -  the Hospital Referral Region Description
	 * @param TotalDischarges - the number of discharges that this object has made for this procedure
	 * @param ACC - Price after deductable for this object
	 * @param ATP - price total for this object
	 * @param AMP - average medicare payable for this object
	 */
	public ProviderDataObject(String DRGDef, int ProviderID, String ProviderName, String ProviderAddress, 
			String ProviderCity, String ProviderState, int ProviderZip, String HospitalRRD, 
			int TotalDischarges, double ACC, double ATP, double AMP){
		this.DRGDef = DRGDef;
		this.ProviderID = ProviderID;
		this.ProviderName = ProviderName;
		this.ProviderAddress = ProviderAddress;
		this.ProviderCity = ProviderCity;
		this.ProviderState = ProviderState;
		this.ProviderZip = ProviderZip;
		this.HospitalRRD = HospitalRRD;
		this.TotalDischarges = TotalDischarges;
		this.ACC = ACC;
		this.ATP = ATP;
		this.AMP = AMP;
	}	

	/**
	 * Method for comparing objects using less
	 * @param a - what object you are comparing this this too
	 * @return 0,-1,1 - 0 if equal, -1 if this is less than a, 1 if this is greater than a
	 */
	public int compareTo(Object a) {
		if(less(this, (ProviderDataObject) a)) return -1;
		else if(less((ProviderDataObject) a,this)) return 1;
		else return 0;
	}
	/**
	 * determines if one object is less than another, in terms of distance and price
	 * @param a - first object to compare
	 * @param b - second obejct to compare
	 * @return if a is less than b
	 */
	private boolean less(ProviderDataObject a, ProviderDataObject b) {
		
		if(a.Distance != 0 && b.Distance != 0) {
			if(Math.abs(a.ATP - b.ATP) < 400)
				return (a.Distance < b.Distance);
			return (a.ATP < b.ATP);
		}
		if(a.ProviderZip == b.ProviderZip)
			return (a.ATP < b.ATP);
		return (a.ProviderZip < b.ProviderZip);
	}
	
	@Override
	/**
	 * converts objects to strings
	 * @return returns a string that displays all the information regarding an object
	 */
	public String toString() {
		if(this.Distance != 0){
			return "\nProcedure: " + this.DRGDef + 
				"\nHospital: " + this.ProviderName + 
				"\nAddress: " + this.ProviderAddress + ", " + this.ProviderZip +
				"\nPrice: " + this.ATP +
				"\nDistance: " + this.Distance + " miles";
		}
		return "\nProcedure: " + this.DRGDef + 
				"\nHospital: " + this.ProviderName + 
				"\nPrice: " + this.ATP +
				"\nAddress: " + this.ProviderAddress + ", " + this.ProviderZip;
	}

	/**
	 * getter for AMP
	 * @return the AMP of the object
	 */
	public double getAMP() {
		return AMP;
	}
	
	/**
	 * getter for ATP
	 * @return the ATP of the object
	 */
	public double getATP() {
		return ATP;
	}
	
	/**
	 * getter for ACC
	 * @return the ACC of the object
	 */
	public double getACC() {
		return ACC;
	}
	/**
	 * getter for getTotalDischarges
	 * @return the getTotalDischarges of the object
	 */
	public int getTotalDischarges() {
		return TotalDischarges;
	}
	
	/**
	 * getter for getProviderID
	 * @return etProviderID
	 */
	public int getProviderID() {
		return this.ProviderID;
	}
	
	/**
	 * getter for getProviderZIP
	 * @return getProvider
	 */	
	public int getProviderZip() {
		return this.ProviderZip;
	}
	
	/**
	 * getter for getProviderZIPStr,so that it will have the proper formatting GoogleMaps api
	 * @return the zip as a string 
	 */		
	public String getProviderZipStr() {
		if(String.valueOf(this.ProviderZip).length() == 4) 
			return ("0" + String.valueOf(this.ProviderZip));
		return (String.valueOf(this.ProviderZip));
	}
	
	/**
	 * getter for HospitalRRD
	 * @return for HospitalRRD
	 */	
	public String getHospitalRRD() {
		return HospitalRRD;
	}
	/**
	 * getter for the provider state
	 * @return for the provider state
	 */	
	public String getProviderState() {
		return ProviderState;
	}
	/**
	 * getter for the provider city
	 * @return for the provider city
	 */	
	public String getProviderCity() {
		return ProviderCity;
	}
	/**
	 * getter for the providerAddress()
	 * @return the ProviderAddress()
	 */	
	public String getProviderAddress() {
		return ProviderAddress;
	}
	/**
	 * gets the DRGDef()
	 * @return the DRGDef() for this object
	 */
	public String getDRGDef() {
		return DRGDef;
	}
	/**
	 * gets the DRG operation number
	 * @return the DRG operation number of this object
	 */
	public int getDRGDefNum() {
		return Integer.parseInt(DRGDef.substring(0, 3));
	}
	/**
	 * gets the distance (relative to the user)
	 * @return the distance
	 */
	public double getDistance() {
		return Distance;
	}
	/**
	 * sets the distance (relative to the user)
	 * @param distance - distance between the user and this object
	 */
	public void setDistance(double distance) {
		Distance = distance;
	}

}
