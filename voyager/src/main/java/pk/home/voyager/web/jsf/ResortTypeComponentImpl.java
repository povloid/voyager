
/**
 * 
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
import pk.home.voyager.domain.ResortType;
import pk.home.voyager.service.ResortTypeService;

/**
 * @author traveler
 *
 */
@Scope("session")
@Component("ResortTypeComponent")
public class ResortTypeComponentImpl extends AbstractJSFCRUDFunctionalImpl<ResortType>
		implements Serializable, ResortTypeComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private ResortTypeService ResortTypeService;

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

	public ResortTypeComponentImpl() {
		
		
	}

	
	
	@Transactional
	@Override
	public void preRenderView(){
		try {
			System.out.println("INIT LIST");
			list =  ResortTypeService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	List<ResortType> list;
	
	@Override
	@Transactional
	public List<ResortType> list() {
		return list;
	}


	@Override
	public List<ResortType> list(int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	@Transactional
	protected String _create() throws Exception {
		this.eo = new ResortType();
		return "/jsf/ResortType/opResortType.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = ResortTypeService.find(so.getId());
		//System.out.println(">>Hotels:" + this.eo.getHotels().size());
		return "/jsf/ResortType/opResortType.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _store() throws Exception {
		ResortTypeService.store(eo);
		return "/jsf/ResortType/listResortType.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = ResortTypeService.find(so.getId());
		return "/jsf/ResortType/opResortType.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _confirmDelete() throws Exception {
		ResortTypeService.remove(eo);
		return "/jsf/ResortType/listResortType.xhtml?faces-redirect=true";
	}

	@Override
	protected void _parseRequestPars(ExternalContext externelContext)
			throws Exception {
	}

	

}
