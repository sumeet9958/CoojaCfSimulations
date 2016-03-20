
public class StatisticData {
	int noOfSentMsg=0;
	int noOfSucSentMsg=0;
	int noOfUnSucSentMsg=0;
	
	
	public StatisticData() {
		super();
	}
	public int getNoOfSentMsg() {
		return noOfSentMsg;
	}
	public void setNoOfSentMsg(int noOfSentMsg) {
		this.noOfSentMsg = noOfSentMsg;
	}
	public int getNoOfSucSentMsg() {
		return noOfSucSentMsg;
	}
	public void setNoOfSucSentMsg(int noOfSucSentMsg) {
		this.noOfSucSentMsg = noOfSucSentMsg;
	}
	public int getNoOfUnSucSentMsg() {
		return noOfUnSucSentMsg;
	}
	public void setNoOfUnSucSentMsg(int noOfUnSucSentMsg) {
		this.noOfUnSucSentMsg = noOfUnSucSentMsg;
	}
	@Override
	public String toString() {
		return "StatisticData [noOfSentMsg=" + noOfSentMsg + ", noOfSucSentMsg=" + noOfSucSentMsg
				+ ", noOfUnSucSentMsg=" + noOfUnSucSentMsg + "]";
	}
	
}
