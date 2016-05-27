import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
 
public class HttpUtil {

	public String GetUrlContentAsString(String url) {
		
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer body = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				body.append(line);
			}
			return body.toString();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		
		return null;
		
	}

}
