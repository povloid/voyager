/**
 * 
 */
package pk.home.voyager.dao.location;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
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
	@Override
	@Transactional
	public long getChildrensCount(Location parent) {
		if (parent == null) {
			return (Long) executeQueryByNameSingleResultO(
					"Location.findRootChildrensCount");
		} else {
			return (Long) executeQueryByNameSingleResultO(
					"Location.findChildrensCount", parent);
		}
	}

	@Override
	@Transactional
	public List<Location> getChildren(Location parent) {
		if (parent == null) {
			return executeQueryByName("Location.findRootChildrens");
		} else {
			return executeQueryByName("Location.findChildrens", parent);
		}
	}

}
