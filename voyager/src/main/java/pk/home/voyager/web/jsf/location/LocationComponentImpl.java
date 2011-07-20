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
import pk.home.pulibs.datatools.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.Location;
import pk.home.voyager.service.location.LocationService;

/**
 * @author traveler
 * 
 */
@Scope("session")
@Component("LocationComponent")
public class LocationComponentImpl extends
		AbstractJSFCRUDFunctionalImpl<Location> implements LocationComponent,
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
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#create()
	 */
	@Override
	@Transactional
	public String create() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#edit()
	 */
	@Override
	@Transactional
	public String edit() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#store()
	 */
	@Override
	@Transactional
	public String store() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#delete()
	 */
	@Override
	@Transactional
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#confirmDelete()
	 */
	@Override
	@Transactional
	public String confirmDelete() {
		// TODO Auto-generated method stub
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
		return service.getChildrensCount(o);
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
		return service.getChildren(o);
	}

	public void parseId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String idParameter = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("id");

		if (idParameter != null && idParameter.trim().length() > 0) {
			if (idParameter.equals("root")) {
				this.o = null;
			} else {
				long pid = Long.parseLong(idParameter);
				this.o = service.find(pid);
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

		if (o != null) {
			// FacesContext facesCtx = FacesContext.getCurrentInstance();
			// ELContext elCtx = facesCtx.getELContext();
			// ExpressionFactory expFact =
			// facesCtx.getApplication().getExpressionFactory();

			ArrayList<MenuItem> parents = new ArrayList<MenuItem>();
			Location parent = o;
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
	public String showSelectedObject() {
		if (so == null)
			return "";

		this.o = service.find(so.getId());
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
	public String showSelectedObject(Object key) {
		if (key == null)
			this.o = null;
		else
			this.o = service.find(key);
		
		return "/jsf/location/listLocation.xhtml";
	}

	/* (non-Javadoc)
	 * @see pk.home.voyager.web.jsf.location.LocationComponent#isObjectNull()
	 */
	@Override
	public boolean isObjectNull() {
		return this.o == null;
	}

}
