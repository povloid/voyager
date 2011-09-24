

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
import pk.home.voyager.dao.HotelDAO;
import pk.home.voyager.domain.Hotel;

/**
 * @author traveler
 *
 */
@Service("HotelService")
@Transactional
public class HotelServiceImpl extends AbstractServiceCRUDFunctionalImpl<Hotel>
		implements HotelService, Serializable {

	
	
	/**
	 * 
	 */
	public HotelServiceImpl() {
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
	private HotelDAO HotelDAO;
	
	@Override
	@Transactional
	public Hotel store(Hotel object) throws Exception {
		return store(object, object.getId());
	}

	@Override
	@Transactional
	public DAOCRUDFunctional<Hotel> getDAOGRUDFunctional() {
		return HotelDAO;
	}

}
