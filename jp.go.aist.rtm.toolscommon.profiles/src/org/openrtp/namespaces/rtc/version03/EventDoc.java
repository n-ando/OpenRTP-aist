
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for event_doc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="event_doc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}event">
 *       &lt;sequence>
 *         &lt;element name="Doc" type="{http://www.openrtp.org/namespaces/rtc_doc}doc_eventport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event_doc", namespace = "http://www.openrtp.org/namespaces/rtc_doc", propOrder = {
    "doc"
})
public class EventDoc
    extends Event
{

    @XmlElement(name = "Doc")
    protected DocEventport doc;

    /**
     * Gets the value of the doc property.
     * 
     * @return
     *     possible object is
     *     {@link DocEventport }
     *     
     */
    public DocEventport getDoc() {
        return doc;
    }

    /**
     * Sets the value of the doc property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocEventport }
     *     
     */
    public void setDoc(DocEventport value) {
        this.doc = value;
    }

}
