package data_mangement;

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
	
	@Override
	public int compareTo(Object a) {
		if(less(this, (ProviderDataObject) a)) return -1;
		else if(less((ProviderDataObject) a,this)) return 1;
		else return 0;
	}

	private boolean less(ProviderDataObject a, ProviderDataObject b) {
		
		if(a.Distance != 0 && b.Distance != 0) {
			if(a.Distance == b.Distance)
				return (a.ATP < b.ATP);
			return (a.Distance < b.Distance);
		}
		if(a.ProviderZip == b.ProviderZip)
			return (a.ATP < b.ATP);
		return (a.ProviderZip < b.ProviderZip);
	}
	
	@Override
	public String toString() {
		return "Procedure: " + this.DRGDef + 
				"\nHospital: " + this.ProviderName + 
				"\nAddress: " + this.ProviderAddress + ", " + this.ProviderZip + ", " + 
				" Distance: " + this.Distance + " miles";
	}

	public double getAMP() {
		return AMP;
	}

	public double getATP() {
		return ATP;
	}

	public double getACC() {
		return ACC;
	}

	public int getTotalDischarges() {
		return TotalDischarges;
	}
	
	public int getProviderID() {
		return this.ProviderID;
	}
	
	public int getProviderZip() {
		return this.ProviderZip;
	}
	
	public String getProviderZipStr() {
		if(String.valueOf(this.ProviderZip).length() == 4) 
			return ("0" + String.valueOf(this.ProviderZip));
		return (String.valueOf(this.ProviderZip));
	}

	
	public String getHospitalRRD() {
		return HospitalRRD;
	}

	public String getProviderState() {
		return ProviderState;
	}

	public String getProviderCity() {
		return ProviderCity;
	}

	public String getProviderAddress() {
		return ProviderAddress;
	}

	public String getDRGDef() {
		return DRGDef;
	}
	
	public int getDRGDefNum() {
		return Integer.parseInt(DRGDef.substring(0, 3));
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

}
