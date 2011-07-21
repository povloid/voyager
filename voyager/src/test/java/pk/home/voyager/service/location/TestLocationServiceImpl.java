/**
 * 
 */
package pk.home.voyager.service.location;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pk.home.voyager.domain.Location;

/**
 * @author traveler
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class TestLocationServiceImpl {

	/**
	 * The Spring application context.
	 * 
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 * 
	 */
	@Autowired
	protected LocationService service;

	/**
	 * Instantiates a new TestrbServiceTest.
	 * 
	 */
	public TestLocationServiceImpl() {
		setupRequestContext();
	}

	/**
	 * Sets Up the Request context
	 * 
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(
				request);
		RequestContextHolder.setRequestAttributes(attributes);
	}

	/**
	 * Test method for
	 * {@link pk.home.voyager.service.location.LocationServiceImpl#store(pk.home.voyager.domain.Location)}
	 * .
	 */
	@Test
	public void testStoreLocation()  throws Exception{
		String key = "key - 65545";
		String key2 = "key - 65555";
		long id = 0;

		{
			Location o = new Location(key);
			id = service.store(o).getId();
		}

		assertTrue("Объект не был сохранен", id > 0);

		{
			Location o2 = service.find(id);
			assertEquals(key, o2.getTitle());

			o2.setTitle(key2);

			id = service.store(o2).getId();
		}

		{
			Location o3 = service.find(id);
			assertTrue(o3 != null);
			assertEquals(key2, o3.getTitle());
		}

	}

	/**
	 * Test method for
	 * {@link pk.home.voyager.service.location.LocationServiceImpl#getChildrensCount(pk.home.voyager.domain.Location)}
	 * .
	 */
	@Test
	public void testGetChildrensCount()  throws Exception{
		int len = 5;
		long count = len + len * len + len * len * len;
		System.out.print("cont..." + count);

		long lastId0 = 0;
		boolean lfisrt = false;

		for (int i = 0; i < len; i++) {
			Location l0 = new Location("l" + i + "1p0");

			l0 = service.store(l0);

			for (int j = 0; j < len; j++) {
				Location l2 = new Location(l0, "l" + j + "1p" + i);
				l2 = service.store(l2);

				for (int k = 0; k < len; k++) {
					Location l3 = new Location(l2, "l" + k + "1p" + j);
					l3 = service.store(l3);
					System.out.println("cont..." + count + " >> " + i + "." + j
							+ "." + k);
				}
			}

			if (!lfisrt) {
				lastId0 = l0.getId();
				lfisrt = true;
			}
		}

		Location llast = service.find(lastId0);

		System.out.println("***Count : " + service.getChildrensCount(llast));
	}

	/**
	 * Test method for
	 * {@link pk.home.voyager.service.location.LocationServiceImpl#getChildrensCount(pk.home.voyager.domain.Location)}
	 * .
	 */
	@Test
	public void testGetChildrensCountForParentNull()  throws Exception{
		int len = 5;
		long count = len + len * len + len * len * len;
		System.out.print("cont..." + count);

		for (int i = 0; i < len; i++) {
			Location l0 = new Location("l" + i + "1p0");

			l0 = service.store(l0);

			for (int j = 0; j < len; j++) {
				Location l2 = new Location(l0, "l" + j + "1p" + i);
				l2 = service.store(l2);

				for (int k = 0; k < len; k++) {
					Location l3 = new Location(l2, "l" + k + "1p" + j);
					l3 = service.store(l3);
					System.out.println("cont..." + count + " >> " + i + "." + j
							+ "." + k);
				}
			}
		}

		System.out.println("***Count : " + service.getChildrensCount(null));
	}

	/**
	 * Test method for
	 * {@link pk.home.voyager.service.location.LocationServiceImpl#getChildren(pk.home.voyager.domain.Location)}
	 * .
	 */
	@Test
	public void testGetChildren()  throws Exception{

		int len = 5;
		long count = len + len * len + len * len * len;
		System.out.print("cont..." + count);
		long lastId0 = 0;
		boolean lfisrt = false;

		for (int i = 0; i < len; i++) {
			Location l0 = new Location("l" + i + "1p0");

			l0 = service.store(l0);

			for (int j = 0; j < len; j++) {
				Location l2 = new Location(l0, "l" + j + "1p" + i);
				l2 = service.store(l2);

				for (int k = 0; k < len; k++) {
					Location l3 = new Location(l2, "l" + k + "1p" + j);
					l3 = service.store(l3);
					System.out.println("cont..." + count + " >> " + i + "." + j
							+ "." + k);
				}
			}

			if (!lfisrt) {
				lastId0 = l0.getId();
				lfisrt = true;
			}
		}

		Location llast = service.find(lastId0);
		System.out.println("llast.title=" + llast.getTitle());
		List<Location> chlist = service.getChildren(llast);
		System.out.println("childrens count:" + chlist.size());
	}

	/**
	 * Test method for
	 * {@link pk.home.voyager.service.location.LocationServiceImpl#getChildren(pk.home.voyager.domain.Location)}
	 * .
	 */
	@Test
	public void testGetChildrenForParentNull()  throws Exception{

		int len = 5;
		long count = len + len * len + len * len * len;
		System.out.print("cont..." + count);

		for (int i = 0; i < len; i++) {
			Location l0 = new Location("l" + i + "1p0");

			l0 = service.store(l0);

			for (int j = 0; j < len; j++) {
				Location l2 = new Location(l0, "l" + j + "1p" + i);
				l2 = service.store(l2);

				for (int k = 0; k < len; k++) {
					Location l3 = new Location(l2, "l" + k + "1p" + j);
					l3 = service.store(l3);
					System.out.println("cont..." + count + " >> " + i + "." + j
							+ "." + k);
				}
			}

		}

		List<Location> chlist = service.getChildren(null);
		System.out.println("*>>>>>>>childrens count:" + chlist.size());
	}

	/**
	 * Test method for
	 * {@link pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#remove(java.lang.Object)}
	 * .
	 */
	@Test
	public void testRemove()  throws Exception{
		String key = "key - 65545";
		long id = 0;

		{
			Location o = new Location(key);
			id = service.store(o).getId();
		}

		assertTrue("Объект не был сохранен", id > 0);

		{
			Location o2 = service.find(id);
			assertEquals(key, o2.getTitle());

			service.remove(o2);
		}

		{
			Location o3 = service.find(id);
			assertTrue("Объект небфл удален", (o3 == null));
		}

	}

	/**
	 * Test method for
	 * {@link pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#find(java.lang.Object)}
	 * .
	 */
	@Test
	@Rollback(true)
	public void testFind()  throws Exception{
		String key = "key - 65545";
		long id = 0;

		{
			Location o = new Location(key);
			id = service.store(o).getId();
		}

		assertTrue("Объект не был сохранен", id > 0);

		{
			Location o2 = service.find(id);
			assertEquals(key, o2.getTitle());
		}

	}

	/**
	 * Test method for
	 * {@link pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#findAll()}
	 * .
	 */
	@Test
	public void testFindAll() {

	}

	/**
	 * Test method for
	 * {@link pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#findAll(int, int)}
	 * .
	 */
	@Test
	public void testFindAllIntInt() {
	}

	/**
	 * Test method for
	 * {@link pk.home.pulibs.spring.service.AbstractServiceCRUDFunctionalImpl#count()}
	 * .
	 */
	@Test
	@Rollback(true)
	public void testCount()  throws Exception{
		long cout = -55;
		cout = service.count();
		assertTrue(">>> count:" + cout, cout >= 0);

		System.out.println(">>>count:" + cout);
	}

}
