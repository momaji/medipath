package intialize_data;

public class ProviderDataObject {
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
	
	public ProviderDataObject(String ProviderName, String ProviderAddress, 
			String ProviderCity, String ProviderState, Integer ProviderZip, String HospitalRRD, 
			Integer TotalDischarges, Integer ACC, Integer ATP, Integer AMP){
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
}
