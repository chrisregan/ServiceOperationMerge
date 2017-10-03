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

import org.jdom2.Content;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
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
		
		//Element Filter variables - super useful for like everything!
		ElementFilter operationdetails = new org.jdom2.filter.ElementFilter("operationdetails");
		ElementFilter gvid = new org.jdom2.filter.ElementFilter("gvid");
		ElementFilter servicerecommendation = new org.jdom2.filter.ElementFilter("servicerecommendation");
		ElementFilter invoiceline = new org.jdom2.filter.ElementFilter("invoiceline");
		ElementFilter cappedprice = new org.jdom2.filter.ElementFilter("cappedprice");
		ElementFilter desc = new org.jdom2.filter.ElementFilter("desc");
		
		
		System.out.println("childElement1 Decendants: " + childElement1.getDescendants(gvid));
		
		Element childElement2 = childElement1.getChild(null);
		System.out.println("childElement2: " + childElement2);
		
		Element childElement3 = childElement2.getChild(null);
		System.out.println("childElement3: " + childElement3);
		
		
		List<Element> listedChildren1 = childElement1.getChildren(null);
		System.out.println("listedChildren1: " + listedChildren1);
		//System.out.println("listedChildren1: " + listedChildren1.toArray());
		
		//Parent Service Capped Price
		for(Element operationdetailsElement : rootElement.getDescendants(operationdetails)) {
		    System.out.println("Parent Capped Price: " +operationdetailsElement.getContent(cappedprice));
		    Content cappedpricecontent =  operationdetailsElement.getContent(18);
		    System.out.println("Parent Capped Price: " +cappedpricecontent);
		    
		}
		
		//Service Recommendations Capped Prices
		for(Element servicerecommendationElement : rootElement.getDescendants(servicerecommendation)) {
		    System.out.println("servicerecommendations Content?: " +servicerecommendationElement.getContent(cappedprice));
		}
		//Invoice Lines
		//for(Element invoicelineElement : rootElement.getDescendants(invoiceline)) {
		//    System.out.println("invoicelines?: " +invoicelineElement.getContent(desc));
		//}
		
		//for (Content content : rootElement.getDescendants()) {
		//	System.out.println("RootDecedentsContent: " + content.getValue());
		//}
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