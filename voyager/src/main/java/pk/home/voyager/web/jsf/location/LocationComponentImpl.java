/**
 * 
 */
package pk.home.voyager.web.jsf.location;

import java.awt.Menu;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

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
		AbstractJSFCRUDTreeFunctionalImpl<Location, MenuModel> implements
		LocationComponent, Serializable {

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
		try {
			return service.getChildrensCount(po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
		try {
			return service.getChildren(po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFTreeInterface#getChildren(int,
	 * int)
	 */
	@Override
	public List<Location> getChildren(int maxResults, int firstResult) {
		try {
			return service.getChildren(po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
				try {
					this.po = service.find(pid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

			Deque<MenuItem> parents = new ArrayDeque<MenuItem>(); 
			Location parent = po;
			do {
				MenuItem item2 = new MenuItem();
				item2.setValue(parent.getTitle());
				item2.setUrl("/faces/jsf/location/listLocation.xhtml?id="
						+ parent.getId());
				item2.setAjax(false);

				parents.push(item2);
				parent = parent.getParent();

			} while (parent != null);

			while (parents.peek() != null) {
				model.addMenuItem(parents.pop());
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
	protected String _gotoSelectedObject() throws Exception {
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
	protected String _gotoSelectedObject(Object key) throws Exception {
		if (key == null)
			this.po = null;
		else
			this.po = service.find(key);

		return "/jsf/location/listLocation.xhtml";
	}

	// ------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_create()
	 */
	@Override
	@Transactional
	protected String _create() throws Exception {
		this.eo = new Location();
		eo.setParent(po);
		return "/jsf/location/opLocation.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_edit()
	 */
	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = service.find(so.getId());
		return "/jsf/location/opLocation.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_store()
	 */
	@Override
	@Transactional
	protected String _store() throws Exception {
		service.store(eo);
		return "/jsf/location/listLocation.xhtml?faces-redirect=true";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_delete()
	 */
	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = service.find(so.getId());
		return "/jsf/location/opLocation.xhtml";
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
		service.remove(eo);
		return "/jsf/location/listLocation.xhtml?faces-redirect=true";
	}

	// ----------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#_editParent()
	 */
	@Override
	@Transactional
	protected String _editParent() throws Exception {
		this.eo = po;
		return "/jsf/location/opLocation.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#_deleteParent
	 * ()
	 */
	@Override
	@Transactional
	protected String _deleteParent() throws Exception {
		this.eo = po;
		return "/jsf/location/opLocation.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDTreeFunctionalImpl#
	 * _beforeConfirmDeleteParent()
	 */
	@Override
	@Transactional
	protected void _beforeConfirmDeleteParent() throws Exception {
		// if(po==eo && eo != null)
		po = eo.getParent();
	}

}
