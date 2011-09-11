/**
 * 
 */
package pk.home.voyager.web.jsf;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.LType;
import pk.home.voyager.service.LTypeService;

/**
 * @author traveler
 *
 */
@Scope("session")
@Component("LTypeComponent")
public class LTypeComponentImpl extends AbstractJSFCRUDFunctionalImpl<LType>
		implements Serializable, LTypeComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4815174702756345320L;
	
	private int currentPage;

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		System.out.printf("GetCurrentPage = " + currentPage);
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		System.out.printf("SetCurrentPage = " + currentPage);
		this.currentPage = currentPage;
	}

	
	/**
	 * Service injected by Spring that provides CRUD operations for Testrb
	 * entities
	 * 
	 */
	@Autowired
	private LTypeService lTypeService;

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

	public LTypeComponentImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDFunctional#list()
	 */
	@Override
	@Transactional
	public List<LType> list() {
		try {
			return  lTypeService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface#list(int, int)
	 */
	@Override
	public List<LType> list(int maxResults, int firstResult) {
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
		this.eo = new LType();
		return "/jsf/ltype/opLtype.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_edit()
	 */
	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = lTypeService.find(so.getId());
		return "/jsf/ltype/opLtype.xhtml";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_store()
	 */
	@Override
	@Transactional
	protected String _store() throws Exception {
		lTypeService.store(eo);
		return "/jsf/ltype/listLtype.xhtml?faces-redirect=true";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_delete()
	 */
	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = lTypeService.find(so.getId());
		return "/jsf/ltype/opLtype.xhtml";
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
		lTypeService.remove(eo);
		return "/jsf/ltype/listLtype.xhtml?faces-redirect=true";
	}

	/* (non-Javadoc)
	 * @see pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl#_parseRequestPars(javax.faces.context.ExternalContext)
	 */
	@Override
	protected void _parseRequestPars(ExternalContext externelContext)
			throws Exception {
	}

	

}
