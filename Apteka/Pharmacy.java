/**
 * Created by Мария on 28.02.16.
 */
public class Pharmacy
{
	private String phName;
	private String phLocation;
	private double phLongitude;
	private double phLatitude;
	private Double phDistanceToObj;

	public Pharmacy(String name, String location, double longitude, double latitude){
		phName = name;
		phLocation = location;
		phLongitude = longitude;
		phLatitude = latitude;
	}

	String getName() { return phName; }

	void setName(String name) { phName = name; }

	String getLoc() { return  phLocation; }

	void setLoc(String loc) { phLocation = loc; }

	double getLong() { return phLongitude; }

	void setLong(double longit) { phLongitude = longit; }

	double getLat() { return phLatitude; }

	void setLat(double lat) { phLatitude = lat; }

	Double getDistance() { return phDistanceToObj; }

	void setDistance(double x, double y){
		phDistanceToObj =  Math.sqrt( Math.pow((x - getLong()), 2.0) + Math.pow((y - getLat()), 2.0));
	}

	@Override
	public String toString(){
		return "\n" + getName() + "|" + getLoc();
	}

}
