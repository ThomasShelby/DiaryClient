package com.softserve.tc.diaryclient.autosave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.softserve.tc.diaryclient.entity.Record;
import com.softserve.tc.diaryclient.log.Log;

/**
 * 
 * @author Mykola-Andriy
 *         
 */
public class RecordJAXBParser implements XMLParser {
    
    private static Logger logger = Log.init(RecordJAXBParser.class.toString());
    
    public String marshalText(Record record) {
        try {
            logger.debug(
                    String.format("Converting to XML %s", record.toString()));
                    
            JAXBContext context = JAXBContext.newInstance(Record.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter str = new StringWriter();
            marshaller.marshal(record, str);
            return str.toString();
        } catch (JAXBException e) {
            logger.error("JAXBException !!!", e);
        }
        return null;
    }
    
    public Record unmarshalText(String record) {
        try {
            logger.debug(String.format("Converting XML to record %s", record));
            
            JAXBContext context = JAXBContext.newInstance(Record.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader str = new StringReader(record);
            Record res = (Record) unmarshaller.unmarshal(str);
            return res;
        } catch (JAXBException e) {
            logger.error("JAXBException !!!", e);
        }
        return null;
    }
    
    public boolean marshalTextToFile(Record record, String file) {
        
        FileOutputStream fos=null;
        try {
            logger.debug(
                    String.format("Converting record to XML file %s ", record));
            JAXBContext context = JAXBContext.newInstance(Record.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            fos = new FileOutputStream(file);
            marshaller.marshal(record, fos);
            
            return true;
        } catch (FileNotFoundException error) {
            logger.error(String.format("File was not found: %s", file),
                    error);
        } catch (JAXBException e) {
            logger.error("Exception occured during converting record to XML",
                    e);
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                logger.error(e);
            }
            
        }
        
        return false;
    }
    
    public Record unmarshalTextFromFile(String file) {
        try {
            
            JAXBContext context = JAXBContext.newInstance(Record.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file1 = new File(file);
            
            return (Record) unmarshaller.unmarshal(file1);
        } catch (JAXBException e) {
            logger.error(
                    "Exception occured during converting XML file to record",
                    e);
        }
        return null;
    }
}
