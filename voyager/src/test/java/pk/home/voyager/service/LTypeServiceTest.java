/**
 * 
 */
package pk.home.voyager.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author traveler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class LTypeServiceTest {

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
	protected LTypeService service;

	/**
	 * Instantiates a new TestrbServiceTest.
	 * 
	 */
	public LTypeServiceTest() {
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
	 * Operation Unit Test Return a count of all Testrb entity
	 * 
	 */
	@Test
	public void countTestrbs() throws Exception {
		Long count = null;
		count = service.count();

		System.out.println(">>> count = " + count);
	}

}
