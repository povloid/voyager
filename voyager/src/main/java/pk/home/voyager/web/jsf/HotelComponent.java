
/**
 * 
 */
package pk.home.voyager.web.jsf;


import java.io.Serializable;
import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.voyager.domain.Hotel;

/**
 * @author traveler
 *
 */
public interface HotelComponent extends JSFCRUDInterface<Hotel>,Serializable {
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);

}
