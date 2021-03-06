/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import pk.home.voyager.domain.AProperties;
import pk.home.voyager.service.APropertiesService;

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
public class APropertiesServiceTest {
    
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
	protected APropertiesService service;

	/**
	 * Instantiates a new TestrbServiceTest.
	 *
	 */
	public APropertiesServiceTest() {
		setupRequestContext();
	}

	/**
	 * Operation Unit Test
	 * Return a count of all Testrb entity
	 * 
	 */
	@Test
	public void countTestrbs()  throws Exception{
		Long response = null;
		response = service.count();
                
                System.out.println(">>> count = " + response);
	}

	/**
	 * Operation Unit Test
	 * Return all Testrb entity
	 * 
	 */
	@Test
	public void findAllTestrbs() throws Exception{
		Integer startResult = 0;
		Integer maxRows = 0;
		List<AProperties> response = null;
		response = service.findAll(startResult, maxRows);
                
	}
//
//	/**
//	 * Operation Unit Test
//	 * Delete an existing Testrb entity
//	 * 
//	 */
//	@Test
//	public void deleteTestrb() {
//		// TODO: JUnit - Populate test inputs for operation: deleteTestrb 
//		Testrb testrb = new Testrb();
//		testrb.setCdate(new Date());
//		testrb.setKeyname("Row 1");
//		service.deleteTestrb(testrb);
//	}
//
//	/**
//	 * Operation Unit Test
//	 * Load an existing Testrb entity
//	 * 
//	 */
//	@Test
//	public void loadTestrbs() {
//		Set<Testrb> response = null;
//		response = service.loadTestrbs();
//		// TODO: JUnit - Add assertions to test outputs of operation: loadTestrbs
//	}
//
//	/**
//	 * Operation Unit Test
//	 */
//	@Test
//	public void findTestrbByPrimaryKey() {
//		// TODO: JUnit - Populate test inputs for operation: findTestrbByPrimaryKey 
//		Integer id = 0;
//		Testrb response = null;
//		response = service.findTestrbByPrimaryKey(id);
//		// TODO: JUnit - Add assertions to test outputs of operation: findTestrbByPrimaryKey
//	}
//
//	/**
//	 * Operation Unit Test
//	 * Save an existing Testrb entity
//	 * 
//	 */
//	@Test
//	public void saveTestrb() {
//		// TODO: JUnit - Populate test inputs for operation: saveTestrb 
//		Testrb testrb_1 = new Testrb();
//		testrb_1.setCdate(new Date());
//		testrb_1.setKeyname("Row 1");
//		service.saveTestrb(testrb_1);
//	}
//
//	/**
//	 * Autowired to set the Spring application context.
//	 *
//	 */
//	@Autowired
//	public void setContext(ApplicationContext context) {
//		this.context = context;
//		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
//		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
//	}

        
	/**
	 * Sets Up the Request context
	 *
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
}
