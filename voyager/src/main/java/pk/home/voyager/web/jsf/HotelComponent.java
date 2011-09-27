
/**
 * 
 */
package pk.home.voyager.web.jsf;


import java.io.Serializable;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.voyager.domain.Hotel;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface HotelComponent extends JSFCRUDInterface<Hotel>,Serializable {
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);

	
	
	DualListModel<String> getResortTypes();
	void setResortTypes(DualListModel<String> resortTypes);
	
	
	List<Location> locations();
	
	
	/**
	 * @return the ltypeId
	 */
	public long getLocationId();

	/**
	 * @param ltypeId the ltypeId to set
	 */
	public void setLocationId(long id);
	
	
	public void handleFileUpload(FileUploadEvent event);
	
	
	public List<String> getImages();

}
