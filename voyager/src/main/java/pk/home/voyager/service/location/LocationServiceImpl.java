/**
 * 
 */
package pk.home.voyager.service.location;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.basic.intefaces.dao.DAOCRUDFunctional;
import pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl;
import pk.home.voyager.dao.location.LocationDAO;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */

@Service("LocationService")
@Transactional
public class LocationServiceImpl extends
		AbstractServiceCRUDFunctionalImpl<Location> implements
		LocationService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7077972070893538912L;
	@Autowired
	
	
	private LocationDAO dao;
	
	
	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.CRUDFinctional#store(java.lang.Object)
	 */
	@Override
	@Transactional
	public Location store(Location object) throws Exception {
		if(object != null && object == object.getParent()){
			return null;
		}
		
		return dao.store(object);
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.TreeFunctional#getChildrensCount(java.lang.Object)
	 */
	@Override
	@Transactional
	public long getChildrensCount(Location parent) throws Exception{
		return dao.getChildrensCount(parent);
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.TreeFunctional#getChildren(java.lang.Object)
	 */
	@Override
	@Transactional
	public List<Location> getChildren(Location parent) throws Exception{
		return dao.getChildren(parent);
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#getDAOGRUDFunctional()
	 */
	@Override
	@Transactional
	public DAOCRUDFunctional<Location> getDAOGRUDFunctional() {
		return dao;
	}

}
