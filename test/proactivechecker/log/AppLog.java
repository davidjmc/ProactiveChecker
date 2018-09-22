package proactivechecker.log;

import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AppLog {
	
	public static void main(String[] args) {
	
		AppLog.run();
		
	}

	private static void run() {
		System.out.println("LOG initialized!");
		
		Timer timer = null;
		if(timer == null) {
			timer = new Timer();
			TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					
					logInto();
				
				}

				private void logInto() {
					
					//Random random = new Random();
					//double r = random.nextDouble();
					//System.out.println("Result: " + r);
					//String v = Double.toString(r);
					
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					
					try {
						DocumentBuilder builder = factory.newDocumentBuilder();
						Document doc = builder.parse("log/log.xml");
						
						NodeList constantList = doc.getElementsByTagName("constant");
						Node c = constantList.item(0);
						
						Element constant = (Element) c;						
						String name = constant.getAttribute("name");
						NodeList n_list = constant.getElementsByTagName("value");
						Node n = n_list.item(0);
						
						Element value = (Element) n;
						//String v = value.getTextContent();
						//value.setTextContent(v);
						
						System.out.println("Constant: " + name + ":" + value.getTagName() + "=" + value.getTextContent());	
						
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			};
			timer.schedule(task, 0, 2*1000);
		}		
	}

}
