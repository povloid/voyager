/**
 * 
 */
package pk.home.voyager.dao;

/**
 * @author traveler
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pk.home.voyager.dao.LTypeDAO;
import pk.home.voyager.domain.LType;

/**
 * Class used to test the basic Data Store Functionality
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class})
@Transactional
@ContextConfiguration(locations = {"file:./src/main/resources/applicationContext.xml"})
public class LTypeDAOTest {
	
	/**
     * The DAO being tested, injected by Spring
     * 
     */
    private LTypeDAO dataStore;

    /**
     * Instantiates a new TestrbDAOTest.
     * 
     */
    public LTypeDAOTest() {
    }
	
    
    /**
     * Method to allow Spring to inject the DAO that will be tested
     * 
     */
    @Autowired
    public void setDataStore(LTypeDAO dataStore) {
        this.dataStore = dataStore;
    }
    
    
    /**
     * Method to test Testrb domain object.
     * 
     */
    @Test
    @Rollback(true)
    public void Test1() throws Exception{

        {
           LType lt = new LType();
           lt.setKeyName("Регионы");
           lt = dataStore.store(lt);
        }
        
        assertTrue("OK!", true);
    }
    

}
