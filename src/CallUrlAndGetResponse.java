
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


/**
 * @author Frank Lamprea
 * 
 */

public class CallUrlAndGetResponse {

	public static void main(String[] args) {
		while (true) {
			System.out.println("\nOutput: \n" + callURL("http://172.16.10.107:3000/users"));
		}
	}

	public static String callURL(String myURL) {
		
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
		try
		{
			//Sleep for 1 Second
			System.out.println("Sleeping...");
			Thread.sleep(60000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		return sb.toString();
		
	}
}
