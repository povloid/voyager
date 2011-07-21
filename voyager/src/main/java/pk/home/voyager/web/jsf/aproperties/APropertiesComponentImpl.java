/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.web.jsf.aproperties;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.AProperties;
import pk.home.voyager.service.aproperties.APropertiesService;

/**
 *
 * @author traveler
 */
@Scope("session")
@Component("APropertiesComponent")
public class APropertiesComponentImpl extends AbstractJSFCRUDFunctionalImpl<AProperties>
        implements APropertiesComponent, Serializable {
    
    
    private static final long serialVersionUID = 8118895248032305567L;
    
    /**
     * Service injected by Spring that provides CRUD operations for Testrb entities
     * 
     */
    @Autowired
    private APropertiesService aPropertiesService;

    @Override
    @Transactional
    public String view() {
    	if (this.so == null) {
            return "";
        }
    	
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    @Transactional
	public List<?> getViewModel() {
		// TODO Auto-generated method stub
		return null;
	}
    

    public APropertiesComponentImpl() {
    }

    /* (non-Javadoc)
     * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list()
     */
    @Override
    @Transactional
    public List<AProperties> list() {
        try {
			return aPropertiesService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list(int, int)
	 */
	@Override
	public List<AProperties> list(int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}
    

    /* (non-Javadoc)
     * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_create()
     */
    @Override
    @Transactional
    protected String _create() {
        this.eo = new AProperties();
        return "/jsf/aproperties/addAproperties.xhtml";
    }

    /* (non-Javadoc)
     * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_edit()
     */
    @Override
    @Transactional
    protected String _edit() {
        try {
			this.eo = aPropertiesService.find(so.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "/jsf/aproperties/editAproperties.xhtml";
    }

    /* (non-Javadoc)
     * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_store()
     */
    @Override
    @Transactional
    protected String _store() {
        try {
			aPropertiesService.store(eo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
    }


    /* (non-Javadoc)
     * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_delete()
     */
    @Override
    @Transactional
    protected String _delete() {
        try {
			this.eo = aPropertiesService.find(so.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "/jsf/aproperties/delAproperties.xhtml";
    }


    /* (non-Javadoc)
     * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_confirmDelete()
     */
    @Override
    @Transactional
    protected String _confirmDelete() {
        try {
			aPropertiesService.remove(eo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
    }


	

	
}
