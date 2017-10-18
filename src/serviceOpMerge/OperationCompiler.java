package serviceOpMerge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.jdom2.output.XMLOutputter;

import java.text.DecimalFormat;

public class OperationCompiler {

	//Purpose is to take the cappedprice (float), invoicelines (xml), opparts(xml), opsundries(xml) 
	//for all SR's of the OperationDetail Parent and collate them and add price together
	
	public static void main(String[] args) {
		try {
		//Specify Input File and Check relative path, output that to console	
		File inputFile = new File(".\\IFM.xml");
			//System.out.println("Source XML: " + inputFile.getAbsolutePath());
		
		//Use SAX to ingest the input xml file
		SAXBuilder saxBuilder = new SAXBuilder();
		Document IFMXML = saxBuilder.build(inputFile);
		
		//Starting variable for adding the capped prices together
		float totalpricevalueexgst = 0;
		
		//Get the top most element and display it
		Element dmsimessage = IFMXML.getRootElement();
			//System.out.println("Root element: " + dmsimessage.getName());
		
		Element operationdetail = dmsimessage.getChild("operationdetails");
			//System.out.println("Root element: " + operationdetail.getName());

		Element operationName = operationdetail.getChild("name");
			System.out.println("Operation Name: " + operationName.getValue());
		
		Element operationDetailCappedPrice = operationdetail.getChild("cappedprice");
		String operationDetailcappedPriceString = operationDetailCappedPrice.getValue();
		float cappedpricefloatvalue = Float.valueOf(operationDetailcappedPriceString);
		
		System.out.println("Operation Capped Price: " + cappedpricefloatvalue);
		
		//Add the capped Price to the current total price
		totalpricevalueexgst += cappedpricefloatvalue;
			
		// create an element for SR's
		Element operationsr = operationdetail.getChild("servicerecommendations");
		
		//Make a list of all the SR elements
		List <Element> servicerecommendationList = operationsr.getChildren();
		
		//iterate through all the Operations SR's to to get the details
		for (Element SRElement : servicerecommendationList)
			{
				String srpreselectedvalue = SRElement.getChild("preselected").getValue();
				int srpreselectedvalueint = Integer.parseInt(srpreselectedvalue);
				
				// weed out the SR's that are not preselected (equal to 1)
				if (srpreselectedvalueint == 1)
				{
					
					//Get the name of each preselected SR
					Element srname = SRElement.getChild("name");
					
					//Get and convert the capped Price element string to float for later calculation
					Element srcappedprice = SRElement.getChild("cappedprice");
					String srcappedpricestring = srcappedprice.getValue();
					float srcappedpricefloatvalue = Float.valueOf(srcappedpricestring);
					
					//Add the capped Price to the current total price
					totalpricevalueexgst += srcappedpricefloatvalue;
					
					System.out.println("Service Recommendation " + srname.getValue() + " is required and Capped Price is $" + srcappedpricefloatvalue + " ex GST");
				}
			}
		
		//Create formatting rule for prices with 2 decimal places and default rounding
		DecimalFormat formatedprice = new DecimalFormat();
		formatedprice.setMaximumFractionDigits(2);
		
		//include GST and set as variable
		float totalpricevalueincgst = (float) (totalpricevalueexgst*1.15);
		
		System.out.println("Total Price is $" + formatedprice.format(totalpricevalueexgst) + " excl. GST");
		System.out.println("Total Price is $" + formatedprice.format(totalpricevalueincgst) + " incl. GST");
		
		
		// Need to add all the capped prices together here using an array (have all the values as floats already)
		// Trick will be variably setting the array size based on the number of SR's where preselected equals 1
		// managed this not by creating an array, but just starting with 0 and adding each one together as it went. No array needed.
		
		
		//Original XML Input echoed out to console (whole document by the looks - need to figure out how to output an element+children only
		XMLOutputter oldOperationDetailXML = new XMLOutputter();
		oldOperationDetailXML.output(IFMXML, System.out);
		
		//New XML Output echoed out to console
		XMLOutputter newOperationDetailXML = new XMLOutputter();
		
		//Need to figure out how to build new components with compiled prices, oplines etc. 
		//New Document, or just adding XML to existing document.....
		
		}
		catch(JDOMException e) {
		    e.printStackTrace();
			}
		    catch(IOException ioe) {
		    ioe.printStackTrace();
		    }
	}

}
