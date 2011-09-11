/**
 * 
 */
package pk.home.voyager.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.spring.jpa.AbstractJPADAOCRUDFunctionalImpl;
import pk.home.voyager.domain.LType;

/**
 * @author traveler
 * 
 */
@Repository("LTypeDAO")
@Transactional
public class LTypeDAOImpl extends AbstractJPADAOCRUDFunctionalImpl<LType>
		implements LTypeDAO, Serializable {

	
	
	
	/**
	 * @param entityManager
	 */
	public LTypeDAOImpl() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 691600624816525442L;

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
    private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[]{LType.class}));

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Set<Class<?>> getTypes() {
        return dataTypes;
    }

    @Override
    public boolean canBeMerged(LType o) {
        return true;
    }

	@Override
	protected Class<LType> getTClass() {
		return LType.class;
	}

}
