package Json;
import java.util.Date;

public class ExchangeRate {

	public ExchangeRate()
	{
		rates = new ExchangeRateFactor();
	}
	
	private Date date; 
	private String base;
	private ExchangeRateFactor rates;
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the base
	 */
	public String getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}
	/**
	 * @return the rates
	 */
	public ExchangeRateFactor getRates() {
		return rates;
	}
	/**
	 * @param rates the rates to set
	 */
	public void setRates(ExchangeRateFactor rates) {
		this.rates = rates;
	}
	
	
	
}