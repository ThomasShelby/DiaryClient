package com.softserve.tc.diaryclient.autosave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.log4j.Logger;

import com.softserve.tc.diaryclient.entity.Record;
import com.softserve.tc.diaryclient.log.Log;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

public class XStreamRecordParser implements XMLParser {
    
    private static Logger LOG =
            Log.init(XStreamRecordParser.class.toString());
    XStream xs = new XStream();
    
    public String marshalText(Record record) {
        LOG.debug(String.format("Converting to XML %s", record.toString()));
        xs.alias("record", Record.class);
        return xs.toXML(record);
    }
    
    public Record unmarshalText(String record) {
        LOG.debug(String.format("Converting XML to record %s", record));
        xs.alias("record", Record.class);
        return (Record) xs.fromXML(record);
    }
    
    public boolean marshalTextToFile(Record record, String file) {
        
        try {
            LOG.debug(
                    String.format("Converting record to XML file %s ", record));
            FileOutputStream fos = new FileOutputStream(file);
            xs.alias("record", Record.class);
            xs.toXML(record, fos);
            return true;
        } catch (FileNotFoundException error) {
            LOG.error(String.format("File was not found: %s", file),
                    error);
        } catch (XStreamException e) {
            LOG.error("Exception occured during converting record to XML", e);
        }
        return false;
    }
    
    public Record unmarshalTextFromFile(String file) {
        
        try {
            LOG.debug(
                    String.format("Converting XML file to record %s", file));
            FileInputStream fileInputStream = new FileInputStream(file);
            return (Record) xs.fromXML(fileInputStream);
            
        } catch (FileNotFoundException error) {
            LOG.error(String.format("File was not found: %s", file),
                    error);
        } catch (XStreamException e) {
            LOG.error("Exception occured during converting XML file to record",
                    e);
        }
        return null;
    }
}
