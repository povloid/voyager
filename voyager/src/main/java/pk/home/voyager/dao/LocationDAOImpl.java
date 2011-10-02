/**
 * 
 */
package pk.home.voyager.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.spring.jpa.AbstractJPADAOCRUDFunctionalImpl;

import pk.home.voyager.domain.AProperties;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 * 
 */
@Repository("LocationDAO")
@Transactional
public class LocationDAOImpl extends AbstractJPADAOCRUDFunctionalImpl<Location>
		implements LocationDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6857042454304608795L;

	/**
	 * EntityManager injected by Spring for persistence unit
	 * 
	 */
	@PersistenceContext(unitName = "")
	private EntityManager entityManager;
	/**
	 * Set of entity classes managed by this DAO. Typically a DAO manages a
	 * single entity.
	 * 
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { AProperties.class }));

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Override
	public boolean canBeMerged(Location o) {
		return true;
	}

	@Override
	protected Class<Location> getTClass() {
		return Location.class;
	}

	// Реализация Tree функционала
	// Работа с дочерними объектами
	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.TreeFunctional#getChildrensCount(java.lang.Object)
	 */
	@Override
	@Transactional
	public long getChildrensCount(Location parent)  throws Exception {
		if (parent == null) {
			return (Long) executeQueryByNameSingleResultO(
					"Location.findRootChildrensCount");
		} else {
			return (Long) executeQueryByNameSingleResultO(
					"Location.findChildrensCount", parent);
		}
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.TreeFunctional#getChildren(java.lang.Object)
	 */
	@Override
	@Transactional
	public List<Location> getChildren(Location parent)  throws Exception {
		if (parent == null) {
			return executeQueryByName("Location.findRootChildrens");
		} else {
			return executeQueryByName("Location.findChildrens", parent);
		}
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.TreeFunctional#setNewParent(java.lang.Object, java.lang.Object)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setNewParent(Location object, Location parent) throws Exception {
		
		if(object == null)
			throw new Exception("RB> The source object is null!");
		
		object.setParent(parent);
		store(object);
	}
	
	@Override
	@Transactional
	public List<Location> getAllOrderById() throws Exception {
		return executeQueryByName("Location.findAllOrderById");
	}
	


}
