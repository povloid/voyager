/**
 * 
 */
package pk.home.voyager.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pk.home.pulibs.basic.intefaces.dao.DAOCRUDFunctional;
import pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl;
import pk.home.voyager.dao.LocationDAO;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 * 
 */

@Service("LocationService")
@Transactional
public class LocationServiceImpl extends
		AbstractServiceCRUDFunctionalImpl<Location> implements LocationService,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7077972070893538912L;
	@Autowired
	private LocationDAO dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.CRUDFinctional#store(java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Location store(Location object) throws Exception {
		if (object != null && object == object.getParent()) {
			return null;
		}

		return dao.store(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.TreeFunctional#getChildrensCount(java.
	 * lang.Object)
	 */
	@Override
	@Transactional
	public long getChildrensCount(Location parent) throws Exception {
		return dao.getChildrensCount(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.TreeFunctional#getChildren(java.lang.Object
	 * )
	 */
	@Override
	@Transactional
	public List<Location> getChildren(Location parent) throws Exception {
		return dao.getChildren(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#
	 * getDAOGRUDFunctional()
	 */
	@Override
	@Transactional
	public DAOCRUDFunctional<Location> getDAOGRUDFunctional() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.service.ServiceTreeFunctional#setNewParent(java.util.List, java.lang.Object)
	 */
	@Override
	@ExceptionHandler(Exception.class)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void setNewParent(List<Location> objects, Location parent)
			throws Exception {
		for (Location object : objects) { // цикл
			setNewParent(object, parent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.TreeFunctional#setNewParent(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	@ExceptionHandler(Exception.class)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public void setNewParent(Location object, Location parent) throws Exception {
		if (object != null)
			object = dao.find(object.getId());
		else
			// Иначе объект не выбран
			throw new Exception("P>>The moved object is null!");

		if (parent != null) {
			parent = dao.find(parent.getId());

			// Здесь мы проводим проверку на то чтобы родитель небыл помещен
			// к собственному
			// ребенку, иначе произойдет разрыв графа
			Location _parent = parent;
			do {
				if (object == _parent)
					throw new Exception("P>>This operation is bad!");
				_parent = _parent.getParent();
			} while (_parent != null);
		}

		if (object == parent || parent != null
				&& object.getId() == parent.getId())
			throw new Exception("p>> object == parent");

		object.setParent(parent);
	}
	
	
	@Override
	@Transactional
	public List<Location> getAllOrderById() throws Exception {
		return dao.getAllOrderById();
	}
	
	
	
	
	

}
