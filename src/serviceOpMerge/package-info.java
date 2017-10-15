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
import java.lang.*;

import org.jdom2.Content;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.ElementFilter;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;

import java.text.DecimalFormat;

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
		//ElementFilter operationdetails = new org.jdom2.filter.ElementFilter("operationdetails");
		ElementFilter gvid = new org.jdom2.filter.ElementFilter("gvid");
		ElementFilter servicerecommendation = new org.jdom2.filter.ElementFilter("servicerecommendation");
		ElementFilter invoiceline = new org.jdom2.filter.ElementFilter("invoiceline");
		//ElementFilter cappedprice = new org.jdom2.filter.ElementFilter("cappedprice");
		ElementFilter desc = new org.jdom2.filter.ElementFilter("desc");
		
		
		System.out.println("childElement1 Decendants: " + childElement1.getDescendants(gvid));
		
		Element childElement2 = childElement1.getChild(null);
		System.out.println("childElement2: " + childElement2);
		
		Element childElement3 = childElement2.getChild(null);
		System.out.println("childElement3: " + childElement3);
		
		Element parentOperationdetails = rootElement.getChild("operationdetails"); // gets the first 'operationdetails'
		Element opcode = parentOperationdetails.getChild("opcode");
		String opcodeValue = opcode.getValue();
		Element cappedprice = parentOperationdetails.getChild("cappedprice"); // gets the first 'cappedprice'
		String cappedPriceString = cappedprice.getValue();
		float cappedpriceValue = Float.valueOf(cappedPriceString);
		System.out.println("Capped Price for " + opcodeValue + " is: " + cappedpriceValue);
		
		
		//Create an Element Variable which is all "ServiceRecommendation" Childs
		Element servicerecommendationElement = parentOperationdetails.getChild("servicerecommendations");
		
		//Make a list of the children Elements of servicerecommendationElement
		List <Element> servicerecommendationList = servicerecommendationElement.getChildren();
		System.out.println("Service Recommendation List: " + servicerecommendationList);

		//Count the Number of SR's in the List 
		int servicerecommendationElementCount = servicerecommendationList.size();
		System.out.println("No of Child Service Recommendations: " + servicerecommendationElementCount);
		
		//Get the first SR at array 0
		Element servicerecommendationListFirst = servicerecommendationList.get(0);
		
		//Get the value of the SR at 0
		String servicerecommendationListFirstValue = servicerecommendationListFirst.getValue();
		
		//Get Children of SR 0
		List <Element> SR0 = servicerecommendationListFirst.getChildren();
			System.out.println("SR0" + SR0);
		//Get capped Price for SR 0
		Element SR0Price = servicerecommendationListFirst.getChild("cappedprice");
		String SR0cappedPrice = SR0Price.getValue();
		float SR0cappedPriceValue = Float.valueOf(SR0cappedPrice);
			System.out.println("SR0 Capped Price is: " + SR0cappedPriceValue);
			
		float totalprice = (cappedpriceValue+SR0cappedPriceValue);
		DecimalFormat format = new DecimalFormat();
		format.setMaximumIntegerDigits(2);
			java.lang.System.out.println("Total Price is: " + (format.format(totalprice)));
				
		System.out.println("1st Service Recommendation : " + servicerecommendationListFirstValue);
			
		
		List<Element> listedChildren1 = childElement1.getChildren(null);
		System.out.println("listedChildren1: " + listedChildren1);
		//System.out.println("listedChildren1: " + listedChildren1.toArray());
		
		//Parent Service Capped Price
		//for(Element operationdetailsElement : rootElement.getDescendants(operationdetails)) {
		//    System.out.println("Parent Capped Price: " +operationdetailsElement.getContent(cappedprice));
		//    Content cappedpricecontent =  operationdetailsElement.getContent(18);
		//    System.out.println("Parent Capped Price: " +cappedpricecontent);
		    
		//}
		
		//Service Recommendations Capped Prices
		//for(Element servicerecommendationElement : rootElement.getDescendants(servicerecommendation)) {
		//    System.out.println("servicerecommendations Content?: " +servicerecommendationElement.getContent(cappedprice));
		//}
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