/**
 * 
 */
package pk.home.voyager.web.jsf.location;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.datatools.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.Location;
import pk.home.voyager.service.location.LocationService;

/**
 * @author traveler
 *
 */
@Scope("session")
@Component("LocationComponent")
public class LocationComponentImpl extends
		AbstractJSFCRUDFunctionalImpl<Location> implements LocationComponent, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4566162361249638443L;


	/**
     * Service injected by Spring that provides CRUD operations for Testrb entities
     * 
     */
    @Autowired
    private LocationService service;
	

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#getSelectedObject()
	 */
	@Override
	public Location getSelectedObject() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#setSelectedObject(java.lang.Object)
	 */
	@Override
	public void setSelectedObject(Location so) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#view()
	 */
	@Override
	public String view() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#getViewModel()
	 */
	@Override
	@Transactional
	public List<?> getViewModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list()
	 */
	@Override
	@Transactional
	public List<Location> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list(int, int)
	 */
	@Override
	@Transactional
	public List<Location> list(int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#create()
	 */
	@Override
	@Transactional
	public String create() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#edit()
	 */
	@Override
	@Transactional
	public String edit() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#store()
	 */
	@Override
	@Transactional
	public String store() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#delete()
	 */
	@Override
	@Transactional
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#confirmDelete()
	 */
	@Override
	@Transactional
	public String confirmDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildrensCount(java.lang.Object)
	 */
	@Override
	@Transactional
	public long getChildrensCount() {
		return service.getChildrensCount(o);
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildren(java.lang.Object)
	 */
	@Override
	@Transactional
	public List<Location> getChildren() {
		return service.getChildren(o);
	}

}
