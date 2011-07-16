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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.datatools.jpa.AbstractJpaDao;
import pk.home.voyager.domain.AProperties;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
@Repository("LocationDAO")
@Transactional
public class LocationDAOImpl extends AbstractJpaDao<Location> implements
		LocationDAO, Serializable {

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
     * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
     *
     */
    private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[]{AProperties.class}));
	
	
	@Override
	@Transactional
	public Location findByPrimaryKey(Long id) throws DataAccessException {
		try {
            //return executeQueryByNameSingleResult("Location.findByPrimaryKey", id);
			return find(id);
        } catch (NoResultException nre) {
            return null;
        }
	}

	@Override
	@Transactional
	public List<Location> findAll() throws DataAccessException {
		return findEntities();
	}

	@Override
	@Transactional
	public List<Location> findAll(int startResult, int maxRows)
			throws DataAccessException {
		return findEntities(maxRows, startResult);
	}

	@Override
	@Transactional
	public long count() throws DataAccessException {
		return getCount();
	}

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

	
	// Работа с дочерними объектами 
	@Override
	@Transactional
	public long getChildrensCount(Location parent) {
		return (Long) executeQueryByNameSingleResultO("Location.findChildrensCount", parent);
	}

	@Override
	@Transactional
	public List<Location> getChildren(Location parent) {
		return executeQueryByName("Location.findChildrens",parent);
	}

	

}
