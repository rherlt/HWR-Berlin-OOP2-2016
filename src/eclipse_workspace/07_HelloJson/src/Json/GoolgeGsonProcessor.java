package Json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonToken;

/**
 * serializes and deserializes json file using Google gson library https://github.com/google/gson
 * @author rherlt
 *
 */
public class GoolgeGsonProcessor implements JsonProcessor {

	/**
	 * serializes the instance of ExchangeRate and saves it under the given filename
	 */
	@Override
	public void saveExchangeRateToJsonFile(String filename, ExchangeRate exchangeRate) {

		try {

			File file = new File(filename);

			// if file doesnt exists, then create it
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
	 * deserializes the given filename and creates a new instance of ExchangeRate
	 */
	@Override
	public ExchangeRate loadExchangeRateFromJsonFile(String filename) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filename);
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			ExchangeRate exchangeRate = gson.fromJson(fileReader, ExchangeRate.class);
			return exchangeRate;
		} catch (Exception ex) {
			return new ExchangeRate();
		} finally {
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
