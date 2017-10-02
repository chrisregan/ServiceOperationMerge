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

//import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

class recommendationcollator {
	
	public static void main(String[] arguments){
		try {
		
		//Specify Input File and Check relative path, output that to console	
		File inputFile = new File(".\\IFM.xml");
		System.out.println("Source XML: " + inputFile.getAbsolutePath());
		
		//Use SAX to ingest the input xml file
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(inputFile);
		
		
		Element rootElement = document.getRootElement();
		System.out.println("Root element: " + rootElement.getName());
		
		Element childElement1 = rootElement.getChild(null);
		System.out.println("childElement1: " + childElement1);
		
		Element childElement2 = childElement1.getChild(null);
		System.out.println("childElement2: " + childElement2);
		
		Element childElement3 = childElement2.getChild(null);
		System.out.println("childElement3: " + childElement3);
		
		
		List<Element> listedChildren1 = childElement1.getChildren(null);
		System.out.println("listedChildren1: " + listedChildren1);
		System.out.println("listedChildren1: " + listedChildren1.toArray());
		//System.out.println("Doc Content: " + document.getDescendants());
				
		//String cappedPrice = childElement2.getAttribute("cappedPrice").getValue();
		//Element cappedPriceElement = childElement2.getChild(cappedPrice);
		//System.out.println("CPR Testing Output: " + childElement2.);
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