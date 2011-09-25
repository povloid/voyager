package pk.home.voyager.dao;

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

import pk.home.voyager.domain.Hotel;
import pk.home.voyager.domain.ResortType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class HotelDAOTest {

	/**
	 * The DAO being tested, injected by Spring
	 * 
	 */
	private ResortTypeDAO dataStoreResortType;

	@Autowired
	public void setDataStoreResortType(ResortTypeDAO dataStoreResortType) {
		this.dataStoreResortType = dataStoreResortType;
	}

	/**
	 * The DAO being tested, injected by Spring
	 * 
	 */
	private HotelDAO dataStore;

	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 * 
	 */
	@Autowired
	public void setDataStore(HotelDAO dataStore) {
		this.dataStore = dataStore;
	}

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
	public void testStore() throws Exception {

		{
			ResortType resortType = new ResortType();
			resortType.setKeyName("ResortType 2");
			resortType.setDescription("ResortType description 2");
			resortType = dataStoreResortType.store(resortType);

			Hotel hotel = new Hotel();

			hotel.setKeyName("Hotel 2");
			hotel.setDescription("Hotel description 2");

			hotel = dataStore.store(hotel);

			hotel.getResortTypes().add(resortType);

			hotel = dataStore.store(hotel);
		}

	}
	
	@Test
	@Rollback(true)
	public void testSelectHotel() throws Exception {
		List<Hotel> hotels = dataStore.findAll();
		
		System.out.println(hotels.get(0).getResortTypes().size());
		System.out.println(hotels.get(0).getResortTypes().get(0).getKeyName());
	}
	
	
	@Test
	@Rollback(true)
	public void testSelectResortType() throws Exception {
		List<ResortType> rt = dataStoreResortType.findAll();
		
		System.out.println(rt.get(0).getHotels().size());
		System.out.println(rt.get(0).getHotels().get(0).getKeyName());
		
	}

}
