/**
 * 
 */
package pk.home.voyager.web.jsf;

import org.primefaces.model.MenuModel;

import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface LocationComponent extends JSFCRUDInterface<Location>, JSFTreeInterface<Location,MenuModel> {
	

}
