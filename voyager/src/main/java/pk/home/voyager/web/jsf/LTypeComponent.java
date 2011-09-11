/**
 * 
 */
package pk.home.voyager.web.jsf;


import java.io.Serializable;

import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.voyager.domain.LType;

/**
 * @author traveler
 *
 */
public interface LTypeComponent extends JSFCRUDInterface<LType>,Serializable {
	
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);

}
