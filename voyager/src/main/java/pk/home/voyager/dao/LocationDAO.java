/**
 * 
 */
package pk.home.voyager.dao;


import java.util.List;

import pk.home.pulibs.basic.intefaces.dao.DAOCRUDFunctional;
import pk.home.pulibs.basic.intefaces.dao.DAOTreeFunctional;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface LocationDAO extends DAOCRUDFunctional<Location>, DAOTreeFunctional<Location> {

	List<Location> getAllOrderById() throws Exception;
	
}
