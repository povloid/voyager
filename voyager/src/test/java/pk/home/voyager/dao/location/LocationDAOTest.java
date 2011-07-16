package pk.home.voyager.dao.location;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

import pk.home.voyager.domain.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class LocationDAOTest {

	/**
	 * The DAO being tested, injected by Spring
	 * 
	 */
	private LocationDAO dataStore;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	@Rollback(true)
	public void testStore() {

		int len = 5;
		long count= len+len*len+len*len*len;
		System.out.print("cont..."+ count);
		long lastId0 = 0;
		boolean last = false;
		
		for (int i = 0; i < len; i++) {
			Location l0 = new Location("l" + i + "1p0");

			l0 = dataStore.store(l0);
			
			for(int j=0; j < len; j++){
				Location l2 = new Location(l0 , "l" + j + "1p" + i);
				l2 = dataStore.store(l2);
				
				for(int k=0; k < len; k++){
					Location l3 = new Location(l2 , "l" + k + "1p" + j);
					l3 = dataStore.store(l3);
					System.out.println("cont..."+ count + " >> " + i + "." + j + "." + k );
				}
			}
			
			if(!last){
				lastId0 = l0.getId();
				last = true;
			}
		}
		
		System.out.println("***Count : " + dataStore.count());

		Location llast = dataStore.findByPrimaryKey(lastId0);
		
		System.out.println("llast.title=" + llast.getTitle());
		
		System.out.println("childrens count:" + dataStore.getChildrensCount(llast));
		
		System.out.println("*");
		List<Location> chlist = dataStore.getChildren(llast);
		System.out.println("*");
		System.out.println("childrens count:" + chlist.size());
		
		
		/*
		 * Location l1 = new Location(); l1.setTitle("l1"); l1 =
		 * dataStore.store(l1); System.out.println(l1.getId());
		 * 
		 * Location l2 = new Location(); l2.setTitle("l2"); l1.addChildren(l2);
		 * l2.setParent(l1);
		 * 
		 * l2.setDescription("wefdwefwegf");
		 * 
		 * l2 = dataStore.store(l2);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * { System.out.println("***Count: " + dataStore.count());
		 * 
		 * List<Location> list = dataStore.findAll();
		 * 
		 * System.out.println("***List size: " + list.size());
		 * 
		 * for (Location l : list) { System.out.println("*** id=" + l.getId() +
		 * " parent = " + l.getParent()); System.out.println("*** childs:" +
		 * l.getChildren().size());
		 * 
		 * }
		 * 
		 * Location l50 = dataStore.findByPrimaryKey(50L);
		 * 
		 * System.out.println("*** l5 - " + l50.getId() + " childs: " +
		 * l50.getChildren().size() );
		 * 
		 * 
		 * }
		 */

	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 * 
	 */
	@Autowired
	public void setDataStore(LocationDAO dataStore) {
		this.dataStore = dataStore;
	}
}
