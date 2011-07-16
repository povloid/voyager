/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.dao.aproperties;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.datatools.jpa.AbstractJpaDao;
import pk.home.voyager.domain.AProperties;

/**
 *
 * @author traveler
 */
@Repository("APropertiesDAO")
@Transactional
public class APropertiesDAOImpl extends AbstractJpaDao<AProperties> implements APropertiesDAO, Serializable {
    
    private static final long serialVersionUID = -8606873665286675337L;
    
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
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Set<Class<?>> getTypes() {
        return dataTypes;
    }

    @Override
    public boolean canBeMerged(AProperties o) {
        return true;
    }

    @Transactional
    @Override
    public List<AProperties> findAll() throws DataAccessException {
        return findAll(-1, -1);
    }

    @Override
    @Transactional
    public List<AProperties> findAll(int startResult, int maxRows) throws DataAccessException {
        Query query = createNamedQuery("findAllAProperties", startResult, maxRows);
        return query.getResultList();
    }

    @Override
    @Transactional
    public AProperties findByPrimaryKey(Long id) throws DataAccessException {
        try {
            return executeQueryByNameSingleResult("findAPropertiesByPrimaryKey", id);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    @Transactional
    public long count() {
        return (Long) createQuerySingleResult("select count(o) from AProperties o").getSingleResult();
    }

	@Override
	protected Class<AProperties> getTClass() {
		return AProperties.class;
	}
}
