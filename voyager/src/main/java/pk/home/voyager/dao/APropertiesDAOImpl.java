/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import pk.home.voyager.domain.AProperties;

/**
 *
 * @author traveler
 */
@Repository("APropertiesDAO")
@Transactional
public class APropertiesDAOImpl extends AbstractJPADAOCRUDFunctionalImpl<AProperties> implements APropertiesDAO, Serializable {
    
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

	@Override
	protected Class<AProperties> getTClass() {
		return AProperties.class;
	}
}
