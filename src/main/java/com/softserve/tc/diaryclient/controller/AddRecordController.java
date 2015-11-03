package com.softserve.tc.diaryclient.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.tc.diary.entity.Record;
import com.softserve.tc.diary.entity.Status;
import com.softserve.tc.diary.entity.User;
import com.softserve.tc.diary.webservice.DiaryService;
import com.softserve.tc.diaryclient.autosave.RecordJAXBParser;
import com.softserve.tc.diaryclient.log.Log;
import com.softserve.tc.diaryclient.service.MailSender;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServiceCashLoader;
import com.softserve.tc.diaryclient.webservice.diary.DiaryServicePortProvider;

@Controller
public class AddRecordController {

    public static final String HASH_TAG_SIGH = "#";

    @Autowired
    public DiaryServiceCashLoader diaryServiceCashLoader;
    
    private DiaryService port;

    private static Logger logger = Log.init(AddRecordController.class.toString());

    @SuppressWarnings("static-access")
    @Autowired
    public AddRecordController(DiaryServicePortProvider provider) {
        port = provider.getPort();
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public String addRecordPost(@RequestParam("title") String title, @RequestParam("text") String text,
            @RequestParam("status") String status, @RequestParam("nick") final String nick,
            @RequestParam("file") MultipartFile file,@RequestParam("canvasData") String canvasData, Model model) {

        final User user = port.getUserByNickName(nick);

        Record record = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            record = new Record(user.getUuid(), title, text, fileName, Status.valueOf(status));
            try {
                byte[] bytes = file.getBytes();
                record = port.addRecord(record, bytes);
            } catch(IOException e){
                logger.error("file not loaded");
            }

        } else {
            record = new Record(user.getUuid(), title, text, null, Status.valueOf(status));
            record = port.addRecord(record, null);
        }

        if (record == null) {
            model.addAttribute("result", false);
        } else {
            model.addAttribute("result", true);
            model.addAttribute("user", user);
            model.addAttribute("record", record);
        }

        List<String> hashTags = getHashTagsFromText(title + text);
        if (hashTags.size()>0) {
            addHashTagToCash(hashTags);
        }

        try{
            //logger.info(canvasData);
            int metaPngData = 22;
            //need to delete first 22 symbols to get pure img data without metadata
            String img64 = canvasData.substring(metaPngData);
            //logger.info(img64);
            byte[] decodedBytes = DatatypeConverter.parseBase64Binary(img64 );
            BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));    
            File outputfile = new File(System.getProperty("catalina.home")+ File.separator+"tmpFiles"
                    + File.separator+nick+File.separator+"canvas"+File.separator+"title-"+title+".png");
            if(!outputfile.exists()) {
                outputfile.getParentFile().mkdirs();
            } 
            ImageIO.write(bfi , "png", outputfile);
            logger.info(outputfile);
            bfi.flush();
        }catch(Exception e) {  
            logger.info("unable to save image from canvas");
        }
        
        if (status.equals("PUBLIC")) {
            new Thread(){
                public void run (){
            port.markUserWithNewRecord(user.getUuid());
            MailSender mail = MailSender.getInstance();
            List<User> followers = port.getAllUserFollowers(user.getUuid());
            System.out.println(followers);
            Iterator<User> followerIterator = followers.iterator();
            while (followerIterator.hasNext()) {
                User follower=followerIterator.next();
                mail.setParameters("The Diary. News", "Hi, " + follower.getNickName() + ".\nUser " + nick
                        + " added new public record.", follower.geteMail());
                mail.send();
            }
                }
            }.start();
        }

        File xmlFile = new File(System.getProperty("catalina.home")
                + File.separator + "tmpFiles"
                + File.separator + "autosaved_records" + File.separator + nick
                + "-tempRecord.xml");
        if (xmlFile.exists() && xmlFile.isFile()) {
            xmlFile.delete();
            logger.info("DELETED " + xmlFile.getAbsolutePath());
        }
        
        return "recordsDescription";
    }

    private void addHashTagToCash(List<String> hashTags) {
        for (String hashTag : hashTags) {
            diaryServiceCashLoader.addToCashOfHashTags(hashTag);
        }
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.GET)
    public String addRecordGet(HttpServletRequest request, Model model) {

        String userNickName = request.getUserPrincipal().getName();

        File xmlFile = new File(System.getProperty("catalina.home")
                + File.separator + "tmpFiles"
                + File.separator + "autosaved_records" + File.separator + userNickName
                + "-tempRecord.xml");
        com.softserve.tc.diaryclient.entity.Record temporaryRecord=null;
        if (xmlFile.isFile() && xmlFile.exists()) {
            logger.info("Load previous not submited record");
            RecordJAXBParser jaxb = new RecordJAXBParser();
            temporaryRecord =
                    jaxb.unmarshalTextFromFile(xmlFile.getAbsolutePath());
            model.addAttribute("record", temporaryRecord);
        }
        com.softserve.tc.diary.entity.User user = port.getUserByNickName(userNickName);
        model.addAttribute("user", user);

        return "addRecord";
    }

    private List<String> getHashTagsFromText(String string) {

        Pattern pattern = Pattern.compile(HASH_TAG_SIGH + "[a-zA-Z\\d]+");
        Matcher matcher =pattern.matcher(string);

        List<String> hashTags = new ArrayList<String>();
        while (matcher.find( )) {
            hashTags.add(matcher.group());
        }
        return hashTags;
    }

}
