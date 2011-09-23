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
import pk.home.voyager.dao.LTypeDAO;
import pk.home.voyager.domain.LType;

/**
 * @author traveler
 *
 */
@Service("LTypeService")
@Transactional
public class LTypeServiceImpl extends AbstractServiceCRUDFunctionalImpl<LType>
		implements LTypeService, Serializable {

	
	
	/**
	 * 
	 */
	public LTypeServiceImpl() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3253874749069740026L;
	/**
	 * DAO injected by Spring that manages Testrb entities
	 * 
	 */
	@Autowired
	private LTypeDAO lTypeDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.GRUDFinctional#store(java.lang.Object)
	 */
	@Override
	@Transactional
	public LType store(LType object) throws Exception {
		return store(object, object.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.service.AbstractServiceGRUDFunctionalImpl#
	 * getDAOGRUDFunctional()
	 */
	@Override
	@Transactional
	public DAOCRUDFunctional<LType> getDAOGRUDFunctional() {
		return lTypeDAO;
	}

}
