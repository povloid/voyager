
/**
 * 
 */
package pk.home.voyager.web.jsf;


import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.map.MapModel;

import pk.home.pulibs.basic.intefaces.jsf.JSFCRUDInterface;
import pk.home.voyager.domain.GMapMarker;
import pk.home.voyager.domain.Hotel;
import pk.home.voyager.domain.Image;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface HotelComponent extends JSFCRUDInterface<Hotel>,Serializable {
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);

	
	
	DualListModel<String> getResortTypes();
	void setResortTypes(DualListModel<String> resortTypes);
	
	
	List<Location> locations();
	
	
	/**
	 * @return the ltypeId
	 */
	public long getLocationId();

	/**
	 * @param ltypeId the ltypeId to set
	 */
	public void setLocationId(long id);
	
	
	public void handleFileUpload(FileUploadEvent event);
	
	
	public List<String> getImagesO();
	
	
	
	public TreeNode getRoot();
	public void setRoot(TreeNode root);

	public TreeNode getSelectedNode();
	public void setSelectedNode(TreeNode selectedNode);
	void selectLocation() throws Exception;
	Image getSelectedImage();
	void setSelectedImage(Image selectedImage);
	void deleteImage() throws Exception;
	void setImages(List<Image> images);
	List<Image> getImages();
	MapModel getSimpleModel();
	void addNewMarker() throws Exception;
	GMapMarker geteMarcker();
	void seteMarcker(GMapMarker eMarcker);
	void addMarker(ActionEvent actionEvent) throws Exception;


}
