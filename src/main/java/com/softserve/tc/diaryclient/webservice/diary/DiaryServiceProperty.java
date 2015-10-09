package com.softserve.tc.diaryclient.webservice.diary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.softserve.tc.diaryclient.log.Log;

public class DiaryServiceProperty {

	public Properties properties;

	private static final Logger logger = Log.init(DiaryServiceConnection.class.getName());
	
	private static DiaryServiceProperty propertiesSingleton;
	
	private DiaryServiceProperty() throws IOException{
		
		properties = new Properties();
		InputStream inputStream = null;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();

		try{
			inputStream = classloader.getResourceAsStream("properties/webservice.properties");
			properties.load(inputStream);
		}catch(FileNotFoundException e){
			logger.error(e.getMessage());
		}
		catch(IOException e){
			logger.error(e.getMessage());
		}
		catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	public static DiaryServiceProperty getInstance(){
		
		if (propertiesSingleton == null){
			try {
				propertiesSingleton = new DiaryServiceProperty();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return propertiesSingleton;
	}
	
	public Properties getProperties() {
		
		return properties;
	}
}