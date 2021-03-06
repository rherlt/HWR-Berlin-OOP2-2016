package Json;

import com.google.gson.JsonObject;

public interface JsonProcessor {

	/**
	 * serializes the instance of ExchangeRate and saves it under the given filename
	 */
	public void saveExchangeRateToJsonFile(String filename, ExchangeRate exchangeRate);
	
	/**
	 * deserializes the given filename and creates a new instance of ExchangeRate
	 */
	public ExchangeRate loadExchangeRateFromJsonFile(String filename);		
	
	public String loadTextFromWebsite(String website);

	public  void saveWebsiteTextAsJsonObject(String websiteText, String filename);
	
}