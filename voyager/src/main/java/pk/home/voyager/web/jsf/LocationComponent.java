/**
 * 
 */
package pk.home.voyager.web.jsf;

import java.util.List;

import org.primefaces.model.MenuModel;

import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface;
import pk.home.voyager.domain.LType;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface LocationComponent extends JSFCRUDInterface<Location>, JSFTreeInterface<Location,MenuModel> {
	
	/**
	 * Получить список типов
	 * @return
	 */
	List<LType> lTypes();
	
	
	/**
	 * @return the ltypeId
	 */
	public long getLtypeId();

	/**
	 * @param ltypeId the ltypeId to set
	 */
	public void setLtypeId(long ltypeId);
	
}
