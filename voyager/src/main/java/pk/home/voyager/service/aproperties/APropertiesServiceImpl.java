/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.service.aproperties;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.beanutils.BeanUtils;
import pk.home.voyager.dao.aproperties.APropertiesDAO;
import pk.home.voyager.domain.AProperties;

/**
 *
 * @author traveler
 */
@Service("APropertiesService")
@Transactional
public class APropertiesServiceImpl implements APropertiesService, Serializable{
    
    
    private static final long serialVersionUID = -5078257795961676596L;


    /**
     * DAO injected by Spring that manages Testrb entities
     * 
     */
    @Autowired
    private APropertiesDAO aPropertiesDAO;

    @Override
    @Transactional
    public long count() {
        return aPropertiesDAO.count();
    }

    @Override
    @Transactional
    public List<AProperties> findAll(int startResult, int maxRows) {
        return aPropertiesDAO.findAll(startResult, maxRows);
    }

    @Override
    @Transactional
    public void delete(AProperties o) {
        aPropertiesDAO.remove(o);
        aPropertiesDAO.flush();
    }

    @Override
    @Transactional
    public List<AProperties> load() {
        return aPropertiesDAO.findAll();
    }


    @Override
    @Transactional
    public AProperties findByPrimaryKey(Long id) {
        return aPropertiesDAO.findByPrimaryKey(id);
    }
    
    /* (non-Javadoc)
     * @see pk.home.pulibs.datatools.service.ICRUDOperationsForService#save(java.lang.Object)
     */
    @Override
    @Transactional
    public void save(AProperties aProperties) {
        AProperties existingAProperties = aPropertiesDAO.findByPrimaryKey(aProperties.getId());
        if (existingAProperties != null) {
            if (existingAProperties != aProperties) {
            	try {
					BeanUtils.copyBean(aProperties, existingAProperties, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
            aProperties = aPropertiesDAO.store(existingAProperties);
        } else {
            aProperties = aPropertiesDAO.store(aProperties);
        }
        aPropertiesDAO.flush();
    }

    
}
