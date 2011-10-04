package pk.home.voyager.domain;

import javax.persistence.Embeddable;

@Embeddable
public class GMapMarker {
	
	private double lat;
	private double lng;
	private String title;
	
	
	
	
	public GMapMarker(double lat, double lng, String title) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.title = title;
	}

	public GMapMarker() {
		super();
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
