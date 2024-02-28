
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.ConstraintHashType;
import org.openrtp.namespaces.rtc.version02.ConstraintListType;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.Not;
import org.openrtp.namespaces.rtc.version02.Or;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLike;
import org.openrtp.namespaces.rtc.version02.PropertyIsNotEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsNullType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openrtp.namespaces.rtc.version03 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RtcProfile_QNAME = new QName("http://www.openrtp.org/namespaces/rtc", "RtcProfile");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openrtp.namespaces.rtc.version03
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BasicInfoExt }
     * 
     */
    public BasicInfoExt createBasicInfoExt() {
        return new BasicInfoExt();
    }

    /**
     * Create an instance of {@link ServiceinterfaceExt }
     * 
     */
    public ServiceinterfaceExt createServiceinterfaceExt() {
        return new ServiceinterfaceExt();
    }

    /**
     * Create an instance of {@link Library }
     * 
     */
    public Library createLibrary() {
        return new Library();
    }

    /**
     * Create an instance of {@link TargetEnvironment }
     * 
     */
    public TargetEnvironment createTargetEnvironment() {
        return new TargetEnvironment();
    }

    /**
     * Create an instance of {@link ServiceportExt }
     * 
     */
    public ServiceportExt createServiceportExt() {
        return new ServiceportExt();
    }

    /**
     * Create an instance of {@link ConfigurationExt }
     * 
     */
    public ConfigurationExt createConfigurationExt() {
        return new ConfigurationExt();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link DataportExt }
     * 
     */
    public DataportExt createDataportExt() {
        return new DataportExt();
    }

    /**
     * Create an instance of {@link LanguageExt }
     * 
     */
    public LanguageExt createLanguageExt() {
        return new LanguageExt();
    }

    /**
     * Create an instance of {@link RtcProfile }
     * 
     */
    public RtcProfile createRtcProfile() {
        return new RtcProfile();
    }

    /**
     * Create an instance of {@link PropertyIsEqualTo }
     * 
     */
    public PropertyIsEqualTo createPropertyIsEqualTo() {
        return new PropertyIsEqualTo();
    }

    /**
     * Create an instance of {@link Dataport }
     * 
     */
    public Dataport createDataport() {
        return new Dataport();
    }

    /**
     * Create an instance of {@link Configuration }
     * 
     */
    public Configuration createConfiguration() {
        return new Configuration();
    }

    /**
     * Create an instance of {@link ConfigurationSet }
     * 
     */
    public ConfigurationSet createConfigurationSet() {
        return new ConfigurationSet();
    }

    /**
     * Create an instance of {@link Language }
     * 
     */
    public Language createLanguage() {
        return new Language();
    }

    /**
     * Create an instance of {@link ConstraintType }
     * 
     */
    public ConstraintType createConstraintType() {
        return new ConstraintType();
    }

    /**
     * Create an instance of {@link Not }
     * 
     */
    public Not createNot() {
        return new Not();
    }

    /**
     * Create an instance of {@link ConstraintListType }
     * 
     */
    public ConstraintListType createConstraintListType() {
        return new ConstraintListType();
    }

    /**
     * Create an instance of {@link PropertyIsNullType }
     * 
     */
    public PropertyIsNullType createPropertyIsNullType() {
        return new PropertyIsNullType();
    }

    /**
     * Create an instance of {@link And }
     * 
     */
    public And createAnd() {
        return new And();
    }

    /**
     * Create an instance of {@link PropertyIsGreaterThanOrEqualTo }
     * 
     */
    public PropertyIsGreaterThanOrEqualTo createPropertyIsGreaterThanOrEqualTo() {
        return new PropertyIsGreaterThanOrEqualTo();
    }

    /**
     * Create an instance of {@link Serviceinterface }
     * 
     */
    public Serviceinterface createServiceinterface() {
        return new Serviceinterface();
    }

    /**
     * Create an instance of {@link PropertyIsLessThanOrEqualTo }
     * 
     */
    public PropertyIsLessThanOrEqualTo createPropertyIsLessThanOrEqualTo() {
        return new PropertyIsLessThanOrEqualTo();
    }

    /**
     * Create an instance of {@link PropertyIsLike }
     * 
     */
    public PropertyIsLike createPropertyIsLike() {
        return new PropertyIsLike();
    }

    /**
     * Create an instance of {@link PropertyIsBetween }
     * 
     */
    public PropertyIsBetween createPropertyIsBetween() {
        return new PropertyIsBetween();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link ConstraintHashType }
     * 
     */
    public ConstraintHashType createConstraintHashType() {
        return new ConstraintHashType();
    }

    /**
     * Create an instance of {@link ActionStatus }
     * 
     */
    public ActionStatus createActionStatus() {
        return new ActionStatus();
    }

    /**
     * Create an instance of {@link PropertyIsNotEqualTo }
     * 
     */
    public PropertyIsNotEqualTo createPropertyIsNotEqualTo() {
        return new PropertyIsNotEqualTo();
    }

    /**
     * Create an instance of {@link TransmissionMethod }
     * 
     */
    public TransmissionMethod createTransmissionMethod() {
        return new TransmissionMethod();
    }

    /**
     * Create an instance of {@link BasicInfo }
     * 
     */
    public BasicInfo createBasicInfo() {
        return new BasicInfo();
    }

    /**
     * Create an instance of {@link Or }
     * 
     */
    public Or createOr() {
        return new Or();
    }

    /**
     * Create an instance of {@link PropertyIsLessThan }
     * 
     */
    public PropertyIsLessThan createPropertyIsLessThan() {
        return new PropertyIsLessThan();
    }

    /**
     * Create an instance of {@link ConstraintUnitType }
     * 
     */
    public ConstraintUnitType createConstraintUnitType() {
        return new ConstraintUnitType();
    }

    /**
     * Create an instance of {@link PropertyIsGreaterThan }
     * 
     */
    public PropertyIsGreaterThan createPropertyIsGreaterThan() {
        return new PropertyIsGreaterThan();
    }

    /**
     * Create an instance of {@link Serviceport }
     * 
     */
    public Serviceport createServiceport() {
        return new Serviceport();
    }

    /**
     * Create an instance of {@link Actions }
     * 
     */
    public Actions createActions() {
        return new Actions();
    }

    /**
     * Create an instance of {@link ServiceportDoc }
     * 
     */
    public ServiceportDoc createServiceportDoc() {
        return new ServiceportDoc();
    }

    /**
     * Create an instance of {@link DocServiceinterface }
     * 
     */
    public DocServiceinterface createDocServiceinterface() {
        return new DocServiceinterface();
    }

    /**
     * Create an instance of {@link DocEventport }
     * 
     */
    public DocEventport createDocEventport() {
        return new DocEventport();
    }

    /**
     * Create an instance of {@link BasicInfoDoc }
     * 
     */
    public BasicInfoDoc createBasicInfoDoc() {
        return new BasicInfoDoc();
    }

    /**
     * Create an instance of {@link DocAction }
     * 
     */
    public DocAction createDocAction() {
        return new DocAction();
    }

    /**
     * Create an instance of {@link ActionStatusDoc }
     * 
     */
    public ActionStatusDoc createActionStatusDoc() {
        return new ActionStatusDoc();
    }

    /**
     * Create an instance of {@link DocBasic }
     * 
     */
    public DocBasic createDocBasic() {
        return new DocBasic();
    }

    /**
     * Create an instance of {@link DataportDoc }
     * 
     */
    public DataportDoc createDataportDoc() {
        return new DataportDoc();
    }

    /**
     * Create an instance of {@link EventDoc }
     * 
     */
    public EventDoc createEventDoc() {
        return new EventDoc();
    }

    /**
     * Create an instance of {@link ServiceinterfaceDoc }
     * 
     */
    public ServiceinterfaceDoc createServiceinterfaceDoc() {
        return new ServiceinterfaceDoc();
    }

    /**
     * Create an instance of {@link DocConfiguration }
     * 
     */
    public DocConfiguration createDocConfiguration() {
        return new DocConfiguration();
    }

    /**
     * Create an instance of {@link DocDataport }
     * 
     */
    public DocDataport createDocDataport() {
        return new DocDataport();
    }

    /**
     * Create an instance of {@link DocServiceport }
     * 
     */
    public DocServiceport createDocServiceport() {
        return new DocServiceport();
    }

    /**
     * Create an instance of {@link ConfigurationDoc }
     * 
     */
    public ConfigurationDoc createConfigurationDoc() {
        return new ConfigurationDoc();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RtcProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.openrtp.org/namespaces/rtc", name = "RtcProfile")
    public JAXBElement<RtcProfile> createRtcProfile(RtcProfile value) {
        return new JAXBElement<RtcProfile>(_RtcProfile_QNAME, RtcProfile.class, null, value);
    }

}
