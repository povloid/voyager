/**
 * 
 */
package pk.home.voyager.dao.location;

import java.util.List;

import pk.home.pulibs.datatools.jpa.JpaDaoPlusToCRUD;
import pk.home.voyager.domain.Location;

/**
 * @author traveler
 *
 */
public interface LocationDAO extends JpaDaoPlusToCRUD<Location> {
	
	/**
	 * Получить количество дочерних объектов
	 * @param parent
	 * @return
	 */
	long getChildrensCount(Location parent);
	
	
	/**
	 * Получить список дочерних объектов
	 * @param parent
	 * @return
	 */
	List<Location> getChildren(Location parent);
	
	
	

}
