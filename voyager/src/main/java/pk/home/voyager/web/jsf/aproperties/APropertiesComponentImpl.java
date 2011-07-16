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
import pk.home.pulibs.datatools.jsf.AbstractCRUDOperationsForJSF;
import pk.home.voyager.domain.AProperties;
import pk.home.voyager.service.aproperties.APropertiesService;

/**
 *
 * @author traveler
 */
@Scope("session")
@Component("APropertiesComponent")
public class APropertiesComponentImpl extends AbstractCRUDOperationsForJSF<AProperties>
        implements APropertiesComponent, Serializable {
    
    
    private static final long serialVersionUID = 8118895248032305567L;
    
    /**
     * Service injected by Spring that provides CRUD operations for Testrb entities
     * 
     */
    @Autowired
    private APropertiesService aPropertiesService;

    @Override
    public String view() {
    	if (this.so == null) {
            return "";
        }
    	
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
	public List<?> getViewModel() {
		// TODO Auto-generated method stub
		return null;
	}
    

    public APropertiesComponentImpl() {
    }

    @Override
    @Transactional
    public List<AProperties> list() {
        return aPropertiesService.load();
    }

    @Override
    @Transactional
    public String create() {
        this.o = new AProperties();
        return "/jsf/aproperties/addAproperties.xhtml";
    }

    @Override
    @Transactional
    public String edit() {
        if (this.so == null) {
            return "";
        }

        this.o = aPropertiesService.findByPrimaryKey(so.getId());
        return "/jsf/aproperties/editAproperties.xhtml";
    }

    @Override
    @Transactional
    public String save() {
        aPropertiesService.save(o);
        return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
    }

    @Override
    @Transactional
    public String delete() {
        if (this.so == null) {
            return "";
        }

        this.o = aPropertiesService.findByPrimaryKey(so.getId());
        return "/jsf/aproperties/delAproperties.xhtml";
    }

    @Override
    @Transactional
    public String confirmDelete() {
        aPropertiesService.delete(o);
        return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
    }

	
}
