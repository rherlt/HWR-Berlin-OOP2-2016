import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class JsonRoot {

	public JsonRoot()
	{
		data = new ArrayList<Beer>();
	}
	
	/**
	 * @return the data
	 */
	public ArrayList<Beer> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArrayList<Beer> data) {
		this.data = data;
	}

	@SerializedName("data")
	private ArrayList<Beer> data;

	
	
}
