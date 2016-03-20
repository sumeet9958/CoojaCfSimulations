import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.eclipse.californium.core.*;

public class CoapClientTest {

	Properties prop = null;
	HashMap<String, StatisticData> results = new HashMap<String, StatisticData>();
    
	public HashMap<String, StatisticData> getResults() {
		return results;
	}

	public void setResults(String ipaddress, StatisticData result) {
		this.results.put(ipaddress, result);
	}

	public CoapClientTest(Properties prop) {
		super();
		this.prop = prop;
	}

	public static void main(String[] args) throws NumberFormatException, InterruptedException {

		URI uri = null; // URI parameter of the request

		if (args.length > 0) {
			CoapClientTest cct = new CoapClientTest(LoadPropFile(args[0]));
			cct.startTest();

			
		} else {
			// display help
			System.out.println("Californium (Cf) GET Client");
			System.out.println("(c) 2014, Institute for Pervasive Computing, ETH Zurich");
			System.out.println();
			System.out.println("Usage: " + CoapClientTest.class.getSimpleName() + " URI");
			System.out.println("  URI: The CoAP URI of the remote resource to GET");
		}

	}

	public void startTest() throws NumberFormatException, InterruptedException{
		int noOfMote=Integer.parseInt(this.prop.getProperty("noOfMote"));
		int delay=Integer.parseInt(this.prop.getProperty("delay"));
		int noOfmsg=Integer.parseInt(this.prop.getProperty("noOfmsg"));
		
		for(int i=0;i<noOfMote;i++){
			String IpAddress=this.prop.getProperty("mote"+(i+1));
			CoapRequestSender crs= new CoapRequestSender(IpAddress,delay
					,noOfmsg,this);
			crs.start();
		}
		while(true){
			System.out.println("---------------------------------------------");
			for (Entry<String, StatisticData> entry : results.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
			System.out.println("---------------------------------------------");
			Thread.sleep(Long.parseLong(prop.getProperty("delay"))+1000);
		}
	}

	public static Properties LoadPropFile(String path) {
		Properties prop = new Properties();

		try {

			prop.load(new FileInputStream(path));
			Set<Object> keys = prop.keySet();
			for (Object k : keys) {
				String key = (String) k;
				System.out.println("--" + key + ": " + prop.getProperty(key));
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return prop;
	}

}
