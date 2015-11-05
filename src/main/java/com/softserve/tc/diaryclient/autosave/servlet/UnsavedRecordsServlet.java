package com.softserve.tc.diaryclient.autosave.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.softserve.tc.diaryclient.log.Log;

public class UnsavedRecordsServlet extends HttpServlet {
    
    private static Logger logger =
            Log.init(UnsavedRecordsServlet.class.toString());
            
    private static final long serialVersionUID = 1L;
    private static final File AUTOSAVED_RECORDS_FOLDER =
            new File(System.getProperty("catalina.home")
                    + File.separator + "tmpFiles"
                    + File.separator + "autosaved_records");
                    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nickName = req.getUserPrincipal().getName();
        mergeUserRecordsToOneFile(nickName);
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String app_path = req.getSession().getServletContext().getRealPath("/");
        String filepathXML = AUTOSAVED_RECORDS_FOLDER + File.separator
                + nickName + "UnsavedRecords.xml";
        String filepathXSL = app_path + "WEB-INF\\xsl\\unsavedRecords.xsl";
        
        TransformerFactory tFactory = TransformerFactory.newInstance();
        
        Transformer transformer;
        try {
            transformer =
                    tFactory.newTransformer(new StreamSource(filepathXSL));
            transformer.transform(new StreamSource(filepathXML),
                    new StreamResult(out));
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        deleteFileWithRecords(filepathXML);
    }
    
    private void mergeUserRecordsToOneFile(String nickName) {
        StringBuilder sb = new StringBuilder("<unsavedRecords>\n");
        String pattern = "^" + nickName + ".*$";
        File[] files = getUnsavedRecords(pattern);
        BufferedReader br = null;
        for (File file : files) {
            try {
                br = new BufferedReader(new FileReader(file));
                
                String firstLine = br.readLine();
                String line = br.readLine();
                
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                logger.error("File not found");
            } catch (IOException e) {
                logger.error("IOException " + e);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("Error closing the file: " + e);
                }
            }
            
        }
        sb.append("</unsavedRecords>");
        
        Writer writer = null;
        try {
            writer = new FileWriter(AUTOSAVED_RECORDS_FOLDER + File.separator
                    + nickName + "UnsavedRecords.xml", false);
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        } catch (IOException e) {
            logger.error("IOException: " + e);
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                logger.error("Error closing the file: " + e);
            }
        }
        
    }
    
    private File[] getUnsavedRecords(String regex) {
        if (!AUTOSAVED_RECORDS_FOLDER.isDirectory()) {
            throw new IllegalArgumentException(
                    AUTOSAVED_RECORDS_FOLDER + " is no directory.");
        }
        final Pattern p = Pattern.compile(regex);
        return AUTOSAVED_RECORDS_FOLDER.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return p.matcher(file.getName()).matches();
            }
        });
    }
    
    private void deleteFileWithRecords(String path) {
        File unsavedRecords = new File(path);
        if (unsavedRecords.exists() && unsavedRecords.isFile()) {
            unsavedRecords.delete();
            logger.info("DELETED " + unsavedRecords.getAbsolutePath());
        }
    }
    
}
