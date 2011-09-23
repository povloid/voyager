
/**
 * 
 */
package pk.home.voyager.web.jsf;


import java.io.Serializable;
import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.voyager.domain.ResortType;

/**
 * @author traveler
 *
 */
public interface ResortTypeComponent extends JSFCRUDInterface<ResortType>,Serializable {
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);

}
