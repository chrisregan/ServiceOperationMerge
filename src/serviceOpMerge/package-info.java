/**
 * 
 */
/**
 * @author CPRegan
 *
 */
package serviceOpMerge;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

class recommendationcollator {
	
	public static void main(String[] arguments){
		try {
		File inputFile = new File("Y:\\Documents\\__MLO Projects\\FOTF\\IFM.xml");
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		Element rootElement = document.getRootElement();
		Element childElement1 = rootElement.getChild(null);
		Element childElement2 = childElement1.getChild(null);
		List listedChildren1 = childElement1.getChildren(null);
		//String cappedPrice = childElement2.getAttribute("cappedPrice").getValue();
		//Element cappedPriceElement = childElement2.getChild(cappedPrice);
		System.out.println("Root element: " + document.getRootElement().getName());
		System.out.println("Root element: " + rootElement.getName());
		System.out.println("CPR Testing Output: " + childElement1);
		System.out.println("CPR Testing Output: " + childElement2);
		System.out.println("CPR Testing Output: " + listedChildren1);
		System.out.println("CPR Testing Output: " + childElement2.);
		
		//List<Element> Operations = classElement.getChildren();
		//System.out.println("Childern of Root :" + classElement.getChildren().toString());
		//System.out.println("Childern of Root :" + Operations.getChildren());		
		
		}
		catch(JDOMException e) {
	    e.printStackTrace();
		}
	    catch(IOException ioe) {
	    ioe.printStackTrace();
	    }
	}
}