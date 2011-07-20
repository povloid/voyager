/**
 * 
 */
package pk.home.voyager.web.jsf.app;

import java.io.IOException;
import java.util.Stack;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author traveler
 * 
 */
@ManagedBean(name = "navigator")
@ApplicationScoped
public class Navigator {

	private MenuModel model = new DefaultMenuModel();

	/**
	 * 
	 */
	public Navigator() {

		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		try {

			{
				MenuItem item1 = new MenuItem();
				item1.setValue("First");
				item1.setUrl("#");
				
				model.addMenuItem(item1);
			}
			
			XMLReader parser = XMLReaderFactory.createXMLReader();
			parser.setContentHandler(new NavigatorXMLHandler(model));
			parser.parse(extContext.getRealPath("WEB-INF/navigator.xml"));



		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}

	/**
	 * Обработчик
	 * 
	 * @author traveler
	 * 
	 */
	class NavigatorXMLHandler extends DefaultHandler {
		
		private Stack<Object> stack = new Stack<Object>();
		
		private Object parent;

		/**
		 * 
		 */
		public NavigatorXMLHandler(MenuModel model) {
			super();
			parent = model;
		}

		private void addToParent(MenuItem item) {
			if (parent instanceof MenuModel) {
				MenuModel p = (MenuModel) parent;
				p.addMenuItem(item);
			} else if (parent instanceof Submenu) {
				Submenu p = (Submenu) parent;
				p.getChildren().add(item);
			} 
		}
		
		private void addToParent(Submenu submenu) {
			if (parent instanceof MenuModel) {
				MenuModel p = (MenuModel) parent;
				p.addSubmenu(submenu);
			} else if (parent instanceof Submenu) {
				Submenu p = (Submenu) parent;
				p.getChildren().add(submenu);
			
			} 
		}
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
		 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			
			if(qName.equals("submenu")){
				Submenu submenu = new Submenu();
				submenu.setLabel(attributes.getValue("label"));
				addToParent(submenu);
				stack.add(parent);
				parent = submenu;

			} else if(qName.equals("menu_item")){
				MenuItem item1 = new MenuItem();
				item1.setValue(attributes.getValue("value"));
				item1.setUrl(attributes.getValue("url"));
				addToParent(item1);
			}
			
			
			
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
		 * java.lang.String, java.lang.String)
		 */
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if(qName.equals("submenu")){
				parent = stack.lastElement();
			}
		}

	}

}
