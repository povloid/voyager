/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.AProperties;
import pk.home.voyager.service.APropertiesService;

/**
 * 
 * @author traveler
 */
@Scope("session")
@Component("APropertiesComponent")
public class APropertiesComponentImpl extends
		AbstractJSFCRUDFunctionalImpl<AProperties> implements
		APropertiesComponent, Serializable {

	private static final long serialVersionUID = 8118895248032305567L;

	/**
	 * Service injected by Spring that provides CRUD operations for Testrb
	 * entities
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

	
	@Transactional
	@Override
	public void preRenderView(){
		try {
			System.out.println("INIT LIST");
			list =  aPropertiesService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	List<AProperties> list;
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list()
	 */
	@Override
	@Transactional
	public List<AProperties> list() {
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list(int, int)
	 */
	@Override
	public List<AProperties> list(int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_create()
	 */
	@Override
	@Transactional
	protected String _create() throws Exception {
		this.eo = new AProperties();
		return "/jsf/aproperties/addAproperties.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_edit()
	 */
	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = aPropertiesService.find(so.getId());
		return "/jsf/aproperties/editAproperties.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_store()
	 */
	@Override
	@Transactional
	protected String _store() throws Exception {
		aPropertiesService.store(eo);
		return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_delete()
	 */
	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = aPropertiesService.find(so.getId());
		return "/jsf/aproperties/delAproperties.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_confirmDelete()
	 */
	@Override
	@Transactional
	protected String _confirmDelete() throws Exception {
		aPropertiesService.remove(eo);
		return "/jsf/aproperties/listAproperties.xhtml?faces-redirect=true";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_parseRequestPars(javax.faces.context.ExternalContext)
	 */
	@Override
	protected void _parseRequestPars(ExternalContext externelContext)
			throws Exception {
	}

}
