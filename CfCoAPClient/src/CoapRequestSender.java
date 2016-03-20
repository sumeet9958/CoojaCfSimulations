import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;

public class CoapRequestSender extends Thread{
	String ipAddress="";
	int delay=0;
	int noOfMsg=0;
	int noOfSentMsg=0;
	int noOfSucSentMsg=0;
	int noOfUnSucSentMsg=0;
	CoapClientTest parent;
	

	public CoapRequestSender(String ipAddress, int delay, int noOfMsg, CoapClientTest parent) {
		super();
		this.ipAddress = ipAddress;
		this.delay = delay;
		this.noOfMsg = noOfMsg;
		this.parent = parent;
	}


	public void run(){
		URI uri = null;
		StatisticData sd = new StatisticData();
		sd.setNoOfSentMsg(noOfMsg);
		while(noOfMsg>0){
			
			  try{
			  // input URI from command line arguments try { 
				  uri = new URI("coap://["+this.ipAddress+"]/temp"); 
			  } 
			catch (URISyntaxException e) {
			  System.err.println("Invalid URI: " + e.getMessage());
			  System.exit(-1); 
			 }
			  
			  CoapClient client = new CoapClient(uri);
			  
			  CoapResponse response = client.get();
			  System.out.println(this.ipAddress+" Request Sent: " + client.toString());
			  if (response!=null) {
				  noOfSucSentMsg++;
				  System.out.println(response.getCode()+" : "+response.getOptions()+" : "+response.getResponseText());
				  //System.out.println(response.getOptions());
				  //System.out.println(response.getResponseText());
				  
				  //System.out.println("\nADVANCED\n"); 
				  // access advanced API with access to more details through .advanced()
				  //System.out.println(Utils.prettyPrint(response));
				  
			  } else { 
				  noOfUnSucSentMsg++;
				  System.out.println("No response received."); 
			  }
			 

			noOfMsg--;
			System.out.println("MyThread running: "+toString());
			sd.setNoOfSucSentMsg(noOfSucSentMsg);
			sd.setNoOfUnSucSentMsg(noOfUnSucSentMsg);
			this.parent.setResults(ipAddress, sd);
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	       
	    }


	@Override
	public String toString() {
		return "CoapRequestSender [ipAddress=" + ipAddress + ", delay=" + delay + ", noOfMsg=" + noOfMsg + "]";
	}
	
	
}
