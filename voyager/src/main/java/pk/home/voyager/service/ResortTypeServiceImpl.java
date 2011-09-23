

/**
 * 
 */
package pk.home.voyager.service;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.basic.intefaces.dao.DAOCRUDFunctional;
import pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl;
import pk.home.voyager.dao.ResortTypeDAO;
import pk.home.voyager.domain.ResortType;

/**
 * @author traveler
 *
 */
@Service("ResortTypeService")
@Transactional
public class ResortTypeServiceImpl extends AbstractServiceCRUDFunctionalImpl<ResortType>
		implements ResortTypeService, Serializable {

	
	
	/**
	 * 
	 */
	public ResortTypeServiceImpl() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	/**
	 * DAO injected by Spring that manages Testrb entities
	 * 
	 */
	@Autowired
	private ResortTypeDAO ResortTypeDAO;
	
	@Override
	@Transactional
	public ResortType store(ResortType object) throws Exception {
		return store(object, object.getId());
	}

	@Override
	@Transactional
	public DAOCRUDFunctional<ResortType> getDAOGRUDFunctional() {
		return ResortTypeDAO;
	}

}
