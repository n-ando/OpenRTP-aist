
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for position.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="position">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LEFT"/>
 *     &lt;enumeration value="RIGHT"/>
 *     &lt;enumeration value="TOP"/>
 *     &lt;enumeration value="BOTTOM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "position", namespace = "http://www.openrtp.org/namespaces/rtc_ext")
@XmlEnum
public enum Position {

    LEFT,
    RIGHT,
    TOP,
    BOTTOM;

    public String value() {
        return name();
    }

    public static Position fromValue(String v) {
        return valueOf(v);
    }

}
