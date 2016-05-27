import com.google.gson.annotations.SerializedName;

public class Beer {
	
	public Beer()
	{
		style = new BeerStyle();
	}
	
	@SerializedName("nameDisplay")
	private String name;
	
	@SerializedName("style")
	private BeerStyle style;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the style
	 */
	public BeerStyle getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(BeerStyle style) {
		this.style = style;
	}
}
