/**
 * 
 */
package pk.home.voyager.service.location;

import pk.home.pulibs.basic.intefaces.service.ServiceCRUDFunctional;
import pk.home.pulibs.basic.intefaces.service.ServiceTreeFunctional;
import pk.home.voyager.domain.Location;

/**
 * Интерфейс сервиса локаций
 * 
 * Включает в себя как CRUD и Tree функционал
 * @author traveler
 *
 */
public interface LocationService extends ServiceCRUDFunctional<Location>,
		ServiceTreeFunctional<Location> {

	
}
