import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TokenCache {

	private final String filename = "ApiToken.txt";
	private String token = null;
	
	
	public String getApiToken()
	{
		if (token == null){
			token = loadFromFile() ;
		}
		
		return token;
	}
	
	private String loadFromFile() {

		BufferedReader br = null;
		String apiToken = null;
		try {
			
			br = new BufferedReader(new FileReader(filename));
			apiToken = br.readLine();

		} catch (FileNotFoundException e) {
			apiToken = null;
		} catch (IOException e) {
			apiToken = null;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return apiToken;
	}
}
