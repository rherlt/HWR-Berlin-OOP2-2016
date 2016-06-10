package Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * serializes and deserializes json file using Google gson library
 * https://github.com/google/gson
 * 
 * @author rherlt
 *
 */
public class GoogleGsonProcessor implements JsonProcessor {

	/**
	 * serializes the instance of ExchangeRate and saves it under the given
	 * filename
	 */
	@Override
	public void saveExchangeRateToJsonFile(String filename, ExchangeRate exchangeRate) {

		try {

			File file = new File(filename);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(exchangeRate, bw);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * deserializes the given filename and creates a new instance of
	 * ExchangeRate
	 */
	@Override
	public ExchangeRate loadExchangeRateFromJsonFile(String filename) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filename);
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd").create();
			ExchangeRate exchangeRate = gson.fromJson(fileReader, ExchangeRate.class);
			return exchangeRate;
		} catch (Exception ex) {
			return new ExchangeRate();
		} finally {
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public String loadTextFromWebsite(String website) {
		String httpBody = "";

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(website);
			HttpResponse response = client.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer body = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				body.append(line);
			}
			httpBody = body.toString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpBody;

	}

	@Override
	public void saveWebsiteTextAsJsonObject(String websiteText, String filename) {

		try {

			File file = new File(filename);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.append(websiteText);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}