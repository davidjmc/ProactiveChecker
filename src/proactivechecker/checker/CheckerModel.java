package proactivechecker.checker;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import parser.Values;
import parser.ast.ModulesFile;
import prism.PrismLangException;

public class CheckerModel {

	public void checker(ModulesFile modulesFile) {
		// TODO Auto-generated method stub
		
	}

	public void configure(ModulesFile modulesFile) {
		List<String> undefinedConstantsList = modulesFile.getUndefinedConstants();
		String constatModel = undefinedConstantsList.get(0);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("log/log.xml");
			
			NodeList constantList = doc.getElementsByTagName("constant");
			Node c = constantList.item(0);
			
			Element constant = (Element) c;						
			//String name = constant.getAttribute("name"); // t_trigger
			NodeList n_list = constant.getElementsByTagName("value");
			Node n = n_list.item(0);
	
			Element value = (Element) n;	
			value.getTextContent();	
			
			Integer i = Integer.parseInt(value.getTextContent());
			
			Values v = new Values();
			v.addValue(constatModel, new Integer(i));
			modulesFile.setUndefinedConstants(v);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrismLangException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
