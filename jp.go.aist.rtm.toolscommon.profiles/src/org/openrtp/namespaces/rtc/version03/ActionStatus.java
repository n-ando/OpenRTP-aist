
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for action_status complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="action_status">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="implemented" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action_status")
@XmlSeeAlso({
    ActionStatusDoc.class
})
public class ActionStatus {

    @XmlAttribute(name = "implemented", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected boolean implemented;
    protected String implementeds;

    public void setImplementedbln(boolean value) {
        this.implemented = value;
    }
    
    public String getImplemented() {
        if(implementeds==null) implementeds = Boolean.valueOf(implemented).toString();
        return implementeds;
    }
    public void setImplemented(String value) {
    	implementeds = value;
    }
}
