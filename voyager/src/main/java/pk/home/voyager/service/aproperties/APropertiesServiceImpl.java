/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.service.aproperties;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.basic.intefaces.dao.DAOCRUDFunctional;
import pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl;
import pk.home.voyager.dao.aproperties.APropertiesDAO;
import pk.home.voyager.domain.AProperties;

/**
 * 
 * @author traveler
 */
@Service("APropertiesService")
@Transactional
public class APropertiesServiceImpl extends
		AbstractServiceCRUDFunctionalImpl<AProperties> implements
		APropertiesService, Serializable {

	private static final long serialVersionUID = -5078257795961676596L;

	/**
	 * DAO injected by Spring that manages Testrb entities
	 * 
	 */
	@Autowired
	private APropertiesDAO aPropertiesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.GRUDFinctional#store(java.lang.Object)
	 */
	@Override
	@Transactional
	public AProperties store(AProperties object) throws Exception {
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
	public DAOCRUDFunctional<AProperties> getDAOGRUDFunctional() {
		return aPropertiesDAO;
	}

}
