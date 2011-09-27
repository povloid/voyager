package pk.home.voyager.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Image {
	
	
	public Image() {
		super();
	}
	
	public Image(String filename) {
		super();
		this.filename = filename;
	}




	private String filename;
	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	

}
