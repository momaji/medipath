package intialize_data;

public class ProviderDataObject implements Comparable<Object> {
	private final String DRGDef;
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
	
	public ProviderDataObject(String DRGDef, String ProviderName, String ProviderAddress, 
			String ProviderCity, String ProviderState, int ProviderZip, String HospitalRRD, 
			int TotalDischarges, double ACC, double ATP, double AMP){
		this.DRGDef = DRGDef;
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
		if(less(this ,(ProviderDataObject) a)) return -1;
		else if(less((ProviderDataObject) a,this)) return 1;
		else return 0;
	}
	
	private boolean less(ProviderDataObject a, ProviderDataObject b) {
		return (a.ProviderZip < b.ProviderZip);
	}
	
	@Override
	public String toString() {
		return this.ProviderName + ", " + this.ProviderZip;
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
}
