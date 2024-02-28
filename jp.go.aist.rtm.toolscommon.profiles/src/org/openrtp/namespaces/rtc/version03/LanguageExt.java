
package org.openrtp.namespaces.rtc.version03;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for language_ext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="language_ext">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.openrtp.org/namespaces/rtc}language">
 *       &lt;sequence>
 *         &lt;element name="targets" type="{http://www.openrtp.org/namespaces/rtc_ext}target_environment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "language_ext", namespace = "http://www.openrtp.org/namespaces/rtc_ext", propOrder = {
    "targets"
})
public class LanguageExt
    extends Language
{

    protected List<TargetEnvironment> targets;

    /**
     * Gets the value of the targets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TargetEnvironment }
     * 
     * 
     */
    public List<TargetEnvironment> getTargets() {
        if (targets == null) {
            targets = new ArrayList<TargetEnvironment>();
        }
        return this.targets;
    }

    public void setTargets(List<TargetEnvironment> list) {
    }
}
