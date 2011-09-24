
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
import pk.home.voyager.domain.Hotel;
import pk.home.voyager.service.HotelService;

/**
 * @author traveler
 *
 */
@Scope("session")
@Component("HotelComponent")
public class HotelComponentImpl extends AbstractJSFCRUDFunctionalImpl<Hotel>
		implements Serializable, HotelComponent {

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
	private HotelService HotelService;

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

	public HotelComponentImpl() {
	}

	@Override
	@Transactional
	public List<Hotel> list() {
		try {
			return  HotelService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Hotel> list(int maxResults, int firstResult) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	@Transactional
	protected String _create() throws Exception {
		this.eo = new Hotel();
		return "/jsf/Hotel/opHotel.xhtml";
	}

	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = HotelService.find(so.getId());
		return "/jsf/Hotel/opHotel.xhtml";
	}

	@Override
	@Transactional
	protected String _store() throws Exception {
		HotelService.store(eo);
		return "/jsf/Hotel/listHotel.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = HotelService.find(so.getId());
		return "/jsf/Hotel/opHotel.xhtml";
	}

	@Override
	@Transactional
	protected String _confirmDelete() throws Exception {
		HotelService.remove(eo);
		return "/jsf/Hotel/listHotel.xhtml?faces-redirect=true";
	}

	@Override
	protected void _parseRequestPars(ExternalContext externelContext)
			throws Exception {
	}

	

}
