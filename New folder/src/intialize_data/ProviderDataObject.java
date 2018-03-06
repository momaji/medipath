package intialize_data;

public class ProviderDataObject implements Comparable {
	private final String DRGDef;
	private final String ProviderName;
	private final String ProviderAddress;
	private final String ProviderCity;
	private final String ProviderState;
	private final int ProviderZip;
	private final String HospitalRRD; //Hospital Referral Region Description
	private final int TotalDischarges;
	private final int ACC;
	private final int ATP;
	private final int AMP;
	
	public ProviderDataObject(String DRGDef, String ProviderName, String ProviderAddress, 
			String ProviderCity, String ProviderState, Integer ProviderZip, String HospitalRRD, 
			Integer TotalDischarges, Integer ACC, Integer ATP, Integer AMP){
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
}
