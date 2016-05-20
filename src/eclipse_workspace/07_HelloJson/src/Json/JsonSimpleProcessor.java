package Json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * serializes and deserializes json file using json simple library https://github.com/fangyidong/json-simple
 * @author rherlt
 *
 */
public class JsonSimpleProcessor implements JsonProcessor {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * serializes the instance of ExchangeRate and saves it under the given filename
	 */
	@SuppressWarnings("unchecked")
	public void saveExchangeRateToJsonFile(String filename, ExchangeRate exchangeRate) {
		try {

			File file = new File(filename);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			JSONObject root = new JSONObject();
			root.put("base", exchangeRate.getBase());
			root.put("date", dateFormat.format(new Date()));

			JSONObject rates = new JSONObject();
			rates.put("usd", exchangeRate.getRates().getUsd());
			root.put("rates", rates);

			root.writeJSONString(bw);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * deserializes the given filename and creates a new instance of ExchangeRate
	 */
	public ExchangeRate loadExchangeRateFromJsonFile(String filename) {
		
		FileReader fileReader = null;
		ExchangeRate exchangeRate = new ExchangeRate();
		try {

			fileReader = new FileReader(filename);

			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(fileReader);
			JSONObject rates = (JSONObject) root.get("rates");

			exchangeRate.setBase((String) root.get("base"));
			exchangeRate.setDate(dateFormat.parse((String) root.get("date")));
			exchangeRate.getRates().setUsd((double) rates.get("usd"));

		} catch (FileNotFoundException e) {
			return new ExchangeRate();
		} catch (Exception e) {
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
		
		return exchangeRate;
	}

}
