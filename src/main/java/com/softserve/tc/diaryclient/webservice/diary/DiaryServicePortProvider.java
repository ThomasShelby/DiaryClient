package com.softserve.tc.diaryclient.webservice.diary;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.log.Log;

public class DiaryServicePortProvider {

	private static Logger logger = Log.init(DiaryServicePortProvider.class.getName());
  private static DiaryService port;

	private DiaryServicePortProvider() {
		port = getDairyServicePort();
	}
	
	public static synchronized DiaryService getPort(){
		if (port == null) {
			new DiaryServicePortProvider();
		}
		return port;
	}
	
	private DiaryService getDairyServicePort() {

		if (port != null) {
			return port;
		}

		String nameSpaceURI = getProperty("webservice.diary.namespace_uri");
		String localPart = getProperty("webservice.diary.local_part");
		String WSDL = getProperty("webservice.diary.wsdl");

		QName qname = new QName(nameSpaceURI, localPart);

		try {
			URL wsdlUrl = new URL(WSDL);
			Service service = Service.create(wsdlUrl, qname);
			port = service.getPort(DiaryService.class);

			//using for checking connection
			logger.info(port.sayHello("Java-159.1"));

			return port;
		} catch (WebServiceException w) {
			w.printStackTrace();
			logger.error("\nThere is an error during creation of the proxy to Diary web service.\n"
					+ "OR there is any missing WSDL metadata as required by this method.\n"
					+ "OR an illegal serviceEndpointInterface is specified.");
			return port;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error("Wrong WSDL in getting connection to Diary web service");
		}
		return port;
	}

	public String getProperty(String propertyName){
		Properties properties = DiaryServiceProperty.getInstance().properties;
		String gettedProperty = "";
		gettedProperty = properties.getProperty(propertyName);		
		if (gettedProperty == null) {
			logger.info("Wrong property in webservice.properties: " + propertyName);
			logger.info("Program closed");
			System.exit(0);
		}
		return gettedProperty;
	}
}