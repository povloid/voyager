/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk.home.voyager.dao.aproperties;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
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
import pk.home.voyager.domain.AProperties;

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
public class APropertiesDAOTest {

    /**
     * The DAO being tested, injected by Spring
     * 
     */
    private APropertiesDAO dataStore;

    /**
     * Instantiates a new TestrbDAOTest.
     * 
     */
    public APropertiesDAOTest() {
    }

    /**
     * Method to test Testrb domain object.
     * 
     */
    @Test
    @Rollback(true)
    public void Testrb() throws Exception{

        long id;

        {
            System.out.println(">>>> INSERT");
            AProperties instance = new AProperties();
            instance.setPkey("key 1");
            instance.setDescription("Описание");


            // update the object
            instance = dataStore.store(instance);

            id = instance.getId();
            System.out.println(">>>> id = " + id);
        }

        {
            System.out.println(">>>> UPDATE");
            AProperties instance = dataStore.find(id);

            instance.setPkey("key 3");
            instance.setDateValue(new Date());

            dataStore.store(instance);
        }


        {
            System.out.println(">>>> DELETE");
            AProperties instance = dataStore.find(id);

            dataStore.remove(instance);
        }


        {
//            boolean bool = true;
//            for (int i = 100; i < 500; ++i) {
//                bool = !bool;
//                
//                System.out.println(">>>> INSERT");
//                AProperties instance = new AProperties();
//                instance.setPkey("key " + i);
//                instance.setDescription("Описание Key " + i);
//                
//                instance.setDateValue(new Date());
//                instance.setBooleanValue(bool);
//                instance.setIntValue(i);
//                instance.setFloatValue(i + 0.5f);
//                instance.setDoubleValue( i + 0.5d);
//                instance.setStringValue("String " + i);
//                
//                
//                // update the object
//                instance = dataStore.store(instance);
//            }

            List<AProperties> list = dataStore.findAll();
            
            
            for(AProperties ap : list){
                System.out.println(ap.toString());
            }
           
            
            


        }


        assertTrue("OK!", true);

    }

    /**
     * Method to allow Spring to inject the DAO that will be tested
     * 
     */
    @Autowired
    public void setDataStore(APropertiesDAO dataStore) {
        this.dataStore = dataStore;
    }
}
