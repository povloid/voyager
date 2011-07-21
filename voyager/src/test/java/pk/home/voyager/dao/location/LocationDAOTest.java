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
	}

	@Test
	public void testFindAll() {
	}

	@Test
	public void testFindAllIntInt() {
		
	}

	@Test
	public void testCount() {
		
	}

	@Test
	@Rollback(true)
	public void testStore()  throws Exception{

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

		Location llast = dataStore.find(lastId0);
		
		System.out.println("llast.title=" + llast.getTitle());
		
		System.out.println("childrens count:" + dataStore.getChildrensCount(llast));
		
		System.out.println("*");
		List<Location> chlist = dataStore.getChildren(llast);
		System.out.println("*");
		System.out.println("childrens count:" + chlist.size());
	}

	@Test
	public void testRemove()  throws Exception{
		long id = 0;
		
		{
			Location l = new Location("l444");
			
			l = dataStore.store(l);
			id = l.getId();
		}
		
		System.out.println("id=" + id);
		
		{
			Location l = dataStore.find(id);
			
			System.out.println("load object title=" + l.getTitle());
			
			dataStore.remove(l);
		}
		
		{
			Location l = dataStore.find(id);
			
			System.out.println("l=" + l);
			
			//l = new Location();
			assertTrue("OK!",l == null);
			
		}
		
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
