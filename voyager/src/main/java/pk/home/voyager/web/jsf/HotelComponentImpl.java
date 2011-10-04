/**
 * 
 */
package pk.home.voyager.web.jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pk.home.pulibs.fileutils.FileUtils;
import pk.home.pulibs.spring.jsf.AbstractJSFCRUDFunctionalImpl;
import pk.home.voyager.domain.GMapMarker;
import pk.home.voyager.domain.Hotel;
import pk.home.voyager.domain.Image;
import pk.home.voyager.domain.Location;
import pk.home.voyager.domain.ResortType;
import pk.home.voyager.service.HotelService;
import pk.home.voyager.service.LocationService;
import pk.home.voyager.service.ResortTypeService;

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
	 * @param currentPage
	 *            the currentPage to set
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
	private HotelService hotelService;

	@Autowired
	private ResortTypeService resortTypeService;

	@Autowired
	private LocationService locationService;

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
			return hotelService.findAll();
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
		populateResortTypes();
		System.out.print(">>>ADD");
		return "/jsf/Hotel/opHotel.xhtml";
	}

	private Map<String, Long> resortTypesMap = new HashMap<String, Long>();

	private void populateResortTypes() {
		resortTypesMap.clear();

		List<String> source = new ArrayList<String>();
		List<String> target = new ArrayList<String>();
		try {
			for (ResortType rt : resortTypeService.findAll()) {
				source.add(rt.getKeyName());
				resortTypesMap.put(rt.getKeyName(), rt.getId());
			}

			for (ResortType rt : eo.getResortTypes()) {
				target.add(rt.getKeyName());
				resortTypesMap.put(rt.getKeyName(), rt.getId());
			}

			source.removeAll(target);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.resortTypes = new DualListModel<String>(source, target);
	}

	@Override
	@Transactional
	protected String _edit() throws Exception {
		this.eo = hotelService.find(so.getId());
		populateResortTypes();
		updateImages();
		initSimpleModel();
		return "/jsf/Hotel/opHotel.xhtml";
	}

	@Override
	@Transactional
	protected String _store() throws Exception {
		System.out.println(">>> Rating - " + eo.getRating());

		eo.getResortTypes().clear();

		for (String s : resortTypes.getTarget()) {
			long id = resortTypesMap.get(s);
			ResortType rt = resortTypeService.find(id);
			eo.getResortTypes().add(rt);
		}

		hotelService.store(eo);
		return "/jsf/Hotel/listHotel.xhtml?faces-redirect=true";
	}

	@Override
	@Transactional
	protected String _delete() throws Exception {
		this.eo = hotelService.find(so.getId());
		return "/jsf/Hotel/opHotel.xhtml";
	}

	@Override
	@Transactional
	protected String _confirmDelete() throws Exception {
		hotelService.remove(eo);
		return "/jsf/Hotel/listHotel.xhtml?faces-redirect=true";
	}

	@Override
	protected void _parseRequestPars(ExternalContext externelContext)
			throws Exception {
	}

	private DualListModel<String> resortTypes;

	public DualListModel<String> getResortTypes() {
		return resortTypes;
	}

	public void setResortTypes(DualListModel<String> resortTypes) {
		this.resortTypes = resortTypes;
	}

	@Override
	public List<Location> locations() {
		try {
			List<Location> list = locationService.findAll();
			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getLocationId() {

		if (this.eo.getLocation() != null)
			return this.eo.getLocation().getId();
		else
			return 0;
	}

	@Override
	public void setLocationId(long id) {
		try {
			this.eo.setLocation(locationService.find(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------------------------------------------
	// Выбор локации

	private TreeNode root;

	private TreeNode selectedNode;

	public TreeNode getRoot() {
		root = new DefaultTreeNode("Root", null);

		try {

			// TreeNode node0 = new DefaultTreeNode("Node 0", root);
			// TreeNode node1 = new DefaultTreeNode("Node 1", root);
			// TreeNode node2 = new DefaultTreeNode("Node 2", root);
			//
			// TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
			// TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
			//
			// TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
			// TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);
			//
			// TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);
			// TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);
			// TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);
			//
			// TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);

			Map<Long, TreeNode> map = new HashMap<Long, TreeNode>();

			List<Location> locationsL = locationService.getAllOrderById();

			for (Location l : locationsL) {

				TreeNode parent = l.getParent() != null ? map.get(l.getParent()
						.getId()) : root;

				TreeNode node00 = new DefaultTreeNode(l, parent);
				map.put(l.getId(), node00);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	@Override
	@Transactional
	public void selectLocation() throws Exception {
		Location selLocation = locationService.find(((Location) selectedNode
				.getData()).getId());
		eo.setLocation(selLocation);
	}

	// ---------------------------------------------------------------------------------------------------
	// Работа с картинками

	// Выложить фаил
	private static final int BUFFER_SIZE = 512;

	// Базовая директория для файлов
	private static final String BASE_FILES_DIR = "/tmp";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pk.home.voyager.web.jsf.HotelComponent#handleFileUpload(org.primefaces
	 * .event.FileUploadEvent)
	 */
	@Transactional
	public void handleFileUpload(FileUploadEvent event) {
		// FacesMessage msg = new FacesMessage("Succesful",
		// event.getFile().getFileName() + " is uploaded.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);

		// System.out.println(event.getFile().getFileName() + " is uploaded.");

		// ExternalContext extContext = FacesContext.getCurrentInstance()
		// .getExternalContext();

		// File result = new File(extContext.getRealPath("//tmp")
		// + "//" + event.getFile().getFileName());

		try {

			String dirPath = FileUtils.getCurentTimeDirsPath();
			String absDirPath = BASE_FILES_DIR + dirPath;
			System.out.println(absDirPath);
			FileUtils.mkDirs(absDirPath);

			File result = new File(absDirPath + event.getFile().getFileName());

			FileOutputStream fileOutputStream = new FileOutputStream(result);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bulk;
			InputStream inputStream = event.getFile().getInputstream();
			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();

			eo = hotelService.find(eo.getId());
			eo.getImages().add(
					new Image(dirPath + event.getFile().getFileName()));
			hotelService.store(eo);

			FacesMessage msg = new FacesMessage("Succesful", eo.getId() + "<--"
					+ event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			// System.out.println(event.getFile(). + " is uploaded.");

			updateImages();

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage error = new FacesMessage(
					"The files were not uploaded!");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}

	}

	List<Image> images = new ArrayList<Image>();

	private void updateImages() {
		try {
			images.clear();
			Hotel eot = hotelService.find(eo.getId());
			images.addAll(eot.getImages());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public List<Image> getImages() {
		return images;
	}

	@Override
	public void setImages(List<Image> images) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pk.home.voyager.web.jsf.HotelComponent#getImages()
	 */
	@Override
	@Transactional
	public List<String> getImagesO() {
		System.out.println(">>>!!!!");
		// List<Image> images = new ArrayList<Image>(eo.getImages());

		// try {
		// Hotel eot = hotelService.find(eo.getId());
		// for(Image i: eot.getImages()){
		// images.add(i);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return null;
	}

	private Image selectedImage;

	@Override
	public Image getSelectedImage() {
		return selectedImage;
	}

	@Override
	public void setSelectedImage(Image selectedImage) {
		System.out.println(">>select>>" + selectedImage.getFilename());
		this.selectedImage = selectedImage;
	}

	@Override
	@Transactional
	public void deleteImage() throws Exception {
		System.out.println(">>delete>>" + selectedImage.getFilename());

		eo = hotelService.find(eo.getId());

		for (Image i : eo.getImages()) {
			if (i.getFilename().equals(selectedImage.getFilename())) {
				eo.getImages().remove(i);
				break;
			}
		}

		hotelService.store(eo);

		updateImages();

	}

	// ---------------------------------------------------------------------------------------------
	// Google MAP

	private MapModel simpleModel;
	
	@Transactional
	@Override
	public void initSimpleModel(){
		simpleModel = new DefaultMapModel();
		eMarcker = new GMapMarker(0d, 0d, "");

		try {
		
			Set<GMapMarker> set = eo.getgMapMarkers();

			for (GMapMarker gmm : set) {

				simpleModel.addOverlay(new Marker(new LatLng(gmm.getLat(), gmm
						.getLng()), gmm.getTitle()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private GMapMarker eMarcker;

	
	@Override
	@Transactional
	public MapModel getSimpleModel() {
		return simpleModel;
	}

	@Override
	@Transactional
	public void addNewMarker() throws Exception {
		
		if(eMarcker.getTitle().trim().length() == 0)
			return;
		
		eo.getgMapMarkers().add(eMarcker);
	}

	@Override
	@Transactional
	public void addMarker(ActionEvent actionEvent) throws Exception {		
		eo.getgMapMarkers().add(eMarcker);
		
		eMarcker = new GMapMarker(0d, 0d, "");

	}

	@Override
	public GMapMarker geteMarcker() {
		return eMarcker;
	}

	@Override
	public void seteMarcker(GMapMarker eMarcker) {
		this.eMarcker = eMarcker;
	}
	

	
	@Override
	public void onMarkerSelect(OverlaySelectEvent event) {  
		Marker eMarker2 = (Marker) event.getOverlay();  
          
        for(GMapMarker gmm : eo.getgMapMarkers()){
        	if(gmm.getLat() == eMarker2.getLatlng().getLat()
        			&& gmm.getLng() == eMarker2.getLatlng().getLng()){
        		this.eMarcker = gmm;
        		
        		break;
        	}
        }
    }  
	
	@Transactional
	@Override
	public void deleteMarker(){
		
		eo.getgMapMarkers().remove(eMarcker);
		initSimpleModel();
	}
	

}
