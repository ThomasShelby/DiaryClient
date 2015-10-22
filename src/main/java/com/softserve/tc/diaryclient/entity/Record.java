package com.softserve.tc.diaryclient.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Mykola-
 *         
 */
@XmlRootElement(name = "record")
@XmlType(propOrder = { "uuid", "nick", "title", "text", "supplement" })
public class Record {
    private String uuid;
    private String nick;
    private String title;
    private String text;
    private String supplement;
    
    public Record() {
    }
    
    public Record(String uuid, String nick, String title, String text,
            String supplement) {
        super();
        this.uuid = uuid;
        this.nick = nick;
        this.title = title;
        this.text = text;
        this.supplement = supplement;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    @XmlElement
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getNick() {
        return nick;
    }
    
    @XmlElement
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public String getText() {
        return text;
    }
    
    @XmlElement
    public void setText(String text) {
        this.text = text;
    }
    
    public String getTitle() {
        return title;
    }
    
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSupplement() {
        return supplement;
    }
    
    @XmlElement
    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }
    
    @Override
    public String toString() {
        return "Record [uuid=" + uuid + ", nick=" + nick + ", title=" + title
                + ", text=" + text + ", supplement=" + supplement + "]";
    }
    
}
