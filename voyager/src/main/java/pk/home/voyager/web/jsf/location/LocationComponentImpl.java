/**
 * 
 */
package pk.home.voyager.web.jsf.location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl;
import pk.home.voyager.domain.Location;
import pk.home.voyager.service.location.LocationService;

/**
 * @author traveler
 * 
 */
@Scope("session")
@Component("LocationComponent")
public class LocationComponentImpl extends
		AbstractJSFCRUDTreeFunctionalImpl<Location,MenuModel> implements LocationComponent,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4566162361249638443L;

	/**
	 * Service injected by Spring that provides CRUD operations for Testrb
	 * entities
	 * 
	 */
	@Autowired
	private LocationService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#view()
	 */
	@Override
	@Transactional
	public String view() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#getViewModel()
	 */
	@Override
	@Transactional
	public List<?> getViewModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list()
	 */
	@Override
	@Transactional
	public List<Location> list() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list(int, int)
	 */
	@Override
	@Transactional
	public List<Location> list(int maxResults, int firstResult) {
		return null;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildrensCount
	 * (java.lang.Object)
	 */
	@Override
	@Transactional
	public long getChildrensCount() {
		return service.getChildrensCount(po);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildren(java.
	 * lang.Object)
	 */
	@Override
	@Transactional
	public List<Location> getChildren() {
		return service.getChildren(po);
	}
	
	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildren(int, int)
	 */
	@Override
	public List<Location> getChildren(int maxResults, int firstResult) {
		return service.getChildren(po);
	}
	

	public void parseId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String idParameter = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("id");

		if (idParameter != null && idParameter.trim().length() > 0) {
			if (idParameter.equals("root")) {
				this.po = null;
			} else {
				long pid = Long.parseLong(idParameter);
				this.po = service.find(pid);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getPathModel(java
	 * .lang.Object)
	 */
	@Override
	@Transactional
	public MenuModel getPathModel() {

		MenuModel model = new DefaultMenuModel();
		MenuItem item1 = new MenuItem();
		item1.setValue("<u><b>root:/</b></u>");
		item1.setUrl("/faces/jsf/location/listLocation.xhtml?id=root");

		model.addMenuItem(item1);

		if (po != null) {
			// FacesContext facesCtx = FacesContext.getCurrentInstance();
			// ELContext elCtx = facesCtx.getELContext();
			// ExpressionFactory expFact =
			// facesCtx.getApplication().getExpressionFactory();

			ArrayList<MenuItem> parents = new ArrayList<MenuItem>();
			Location parent = po;
			do {
				MenuItem item2 = new MenuItem();
				item2.setValue(parent.getTitle());
				item2.setUrl("/faces/jsf/location/listLocation.xhtml?id="
						+ parent.getId());
				item2.setAjax(false);

				parents.add(item2);
				parent = parent.getParent();

			} while (parent != null);

			for (int i = parents.size() - 1; i >= 0; i--) {
				model.addMenuItem(parents.get(i));
			}
		}

		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#showSelectedObject()
	 */
	@Override
	@Transactional
	protected String _gotoSelectedObject() {
		this.po = service.find(so.getId());
		return "/jsf/location/listLocation.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#showSelectedObject
	 * (java.lang.Object)
	 */
	@Override
	@Transactional
	protected String _gotoSelectedObject(Object key) {
		if (key == null)
			this.po = null;
		else
			this.po = service.find(key);
		
		return "/jsf/location/listLocation.xhtml";
	}

	
	// ------------------------------------------------------------------------------------------------

	
	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_create()
	 */
	@Override
	@Transactional
	protected String _create() {
		this.eo = new Location();
		eo.setParent(po);
		return "/jsf/location/opLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_edit()
	 */
	@Override
	@Transactional
	protected String _edit() {
		this.eo = service.find(so.getId());
		return "/jsf/location/opLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_store()
	 */
	@Override
	@Transactional
	protected String _store() {
				
		service.store(eo);
		return "/jsf/location/listLocation.xhtml?faces-redirect=true";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_delete()
	 */
	@Override
	@Transactional
	protected String _delete() {
		this.eo = service.find(so.getId());
		return "/jsf/location/opLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_confirmDelete()
	 */
	@Override
	@Transactional
	protected String _confirmDelete() {
		service.remove(eo);
		return "/jsf/location/listLocation.xhtml?faces-redirect=true";
	}

	
	// ----------------------------------------------------------------------------------------
	
	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#_editParent()
	 */
	@Override
	@Transactional
	protected String _editParent() {
		this.eo = po;
		return "/jsf/location/opLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#_deleteParent()
	 */
	@Override
	@Transactional
	protected String _deleteParent() {
		this.eo=po;
		return "/jsf/location/opLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#_beforeConfirmDeleteParent()
	 */
	@Override
	@Transactional
	protected void _beforeConfirmDeleteParent() {
		//if(po==eo && eo != null)
		po = eo.getParent();
	}

	

	

	

}
