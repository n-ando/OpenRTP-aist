package jp.go.aist.rtm.toolscommon.profiles.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.ConstraintHashType;
import org.openrtp.namespaces.rtc.version02.ConstraintListType;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.Or;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;
import org.openrtp.namespaces.rtc.version03.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version03.Actions;
import org.openrtp.namespaces.rtc.version03.BasicInfoExt;
import org.openrtp.namespaces.rtc.version03.ConfigurationExt;
import org.openrtp.namespaces.rtc.version03.ConfigurationSet;
import org.openrtp.namespaces.rtc.version03.Dataport;
import org.openrtp.namespaces.rtc.version03.DataportExt;
import org.openrtp.namespaces.rtc.version03.DocAction;
import org.openrtp.namespaces.rtc.version03.DocBasic;
import org.openrtp.namespaces.rtc.version03.DocConfiguration;
import org.openrtp.namespaces.rtc.version03.DocDataport;
import org.openrtp.namespaces.rtc.version03.DocServiceinterface;
import org.openrtp.namespaces.rtc.version03.DocServiceport;
import org.openrtp.namespaces.rtc.version03.LanguageExt;
import org.openrtp.namespaces.rtc.version03.Library;
import org.openrtp.namespaces.rtc.version03.ObjectFactory;
import org.openrtp.namespaces.rtc.version03.Position;
import org.openrtp.namespaces.rtc.version03.Property;
import org.openrtp.namespaces.rtc.version03.RtcProfile;
import org.openrtp.namespaces.rtc.version03.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version03.Serviceport;
import org.openrtp.namespaces.rtc.version03.ServiceportExt;
import org.openrtp.namespaces.rtc.version03.TargetEnvironment;
import org.openrtp.namespaces.rts.version02.RtsProfile;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jp.go.aist.rtm.toolscommon.profiles.nl.Messages;

public class XmlHandler {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(XmlHandler.class);

	public RtsProfileExt loadXmlRts(String targetFile) throws Exception {
		LOGGER.debug("loadXmlRts: targetFile=<{}>", targetFile);
		StringBuffer stbRet = new StringBuffer();
		try( InputStreamReader isr = new InputStreamReader(new FileInputStream(targetFile), "UTF-8");
				BufferedReader br = new BufferedReader(isr) ) {
			String str = new String();
			while( (str = br.readLine()) != null ){
				stbRet.append(str + "\n");
			}
		}
		return restoreFromXmlRts(stbRet.toString());
	}

	public RtsProfileExt restoreFromXmlRts(String targetXML) throws Exception {
		RtsProfileExt result = null;
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		SAXParser parser = spfactory.newSAXParser();
		RtsXMLParser xmlParser = new RtsXMLParser();
		StringReader xmlReader = new StringReader(targetXML);
		parser.parse(new InputSource(xmlReader), xmlParser);
		String targetClass = null;
		if (xmlParser.version.equals("0.1") || xmlParser.version.equals("1.0")) {
			targetClass = "org.openrtp.namespaces.rts.version01";
		} else if (xmlParser.version.equals("0.2")) {
			targetClass = "org.openrtp.namespaces.rts.version02";
		}
		if (targetClass == null) {
			throw new Exception(Messages.getString("XmlHandler.7"));
		}
		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
		// SAXでの解析後にcloseしてしまうため，再読込
		xmlReader = new StringReader(targetXML);
		Object profile = unmarshaller.unmarshal(xmlReader);
		//
		result = (RtsProfileExt) ((JAXBElement<?>) profile).getValue();
		return result;
	}

	public boolean saveXmlRts(RtsProfile profile, String targetFile) throws Exception {
		String xmlString = convertToXmlRts(profile);

		String lineSeparator = System.getProperty( "line.separator" );
		if( lineSeparator==null || lineSeparator.equals("") ) lineSeparator = "\n";
		String xmlSplit[] = xmlString.split(lineSeparator);

		try(BufferedWriter outputFile = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8")) ) {
			for(int intIdx=0;intIdx<xmlSplit.length;intIdx++) {
				outputFile.write(xmlSplit[intIdx]);
				outputFile.newLine();
			}
		}
		return true;
	}

	public String convertToXmlRts(RtsProfile profile) throws Exception {
	    String xmlString = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.openrtp.namespaces.rts.version02");
			Marshaller marshaller = jaxbContext.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , new Boolean(true));
		    marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
					new NamespacePrefixMapperImpl(
							"http://www.openrtp.org/namespaces/rts"));
		    StringWriter xmlFileWriter = new StringWriter();
		    marshaller.marshal(profile, xmlFileWriter);
		    xmlString = xmlFileWriter.toString();
		} catch (JAXBException exception) {
			throw new Exception(Messages.getString("XmlHandler.17"), exception);
		}
		return xmlString;
	}
	//
	public static boolean validateXmlRtc(String targetString) throws Exception {
		try {
			JAXBContext jc = JAXBContext.newInstance(RtcProfile.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("schema/RtcProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StreamSource(new FileReader(targetString)))).getValue();
		} catch (JAXBException e) {
			throw new Exception(Messages.getString("XmlHandler.19"), e);
		} catch (IOException e) {
			throw new Exception(Messages.getString("XmlHandler.20"), e);
		}
		return true;
	}
	//
	//
	public String convertToXmlRtc(RtcProfile profile) throws Exception {
		String xmlString = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.openrtp.namespaces.rtc.version03");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper",
					new NamespacePrefixMapperImpl("http://www.openrtp.org/namespaces/rtc"));
			StringWriter xmlFileWriter = new StringWriter();
			marshaller.marshal(profile, xmlFileWriter);
			xmlString = xmlFileWriter.toString();
		} catch (JAXBException exception) {
			throw new Exception(Messages.getString("XmlHandler.25"), exception);
		}
		return xmlString;
	}

	public RtcProfile restoreFromXmlRtc(String targetXML) throws Exception {
		RtcProfile result = null;
	    SAXParserFactory spfactory = SAXParserFactory.newInstance();
	    SAXParser parser = spfactory.newSAXParser();
	    RtcXMLParser xmlParser = new RtcXMLParser();
	    StringReader xmlReader = new StringReader(targetXML);
	    parser.parse(new InputSource(xmlReader), xmlParser);
	    String targetClass = null;
	    if( xmlParser.version.equals("0.2") ) {
	    	targetClass = "org.openrtp.namespaces.rtc.version02";
	    } else if( xmlParser.version.equals("0.3") ) {
	    	targetClass = "org.openrtp.namespaces.rtc.version03";
	    }
	    if( targetClass==null ) {
	    	throw new Exception(Messages.getString("XmlHandler.30"));
	    }

		JAXBContext jc = JAXBContext.newInstance(targetClass);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
		//SAXでの解析後にcloseしてしまうため，再読込
	    xmlReader = new StringReader(targetXML);
	    Object profile = ((JAXBElement<?>)unmarshaller.unmarshal(xmlReader)).getValue();
	    //
	    if( xmlParser.version.equals("0.2") ) {
	    	result = convertRtcProfile02to03(profile);
	    } else {
	    	result = (RtcProfile)profile;
	    }

	    return result;
	}

	private class RtsXMLParser extends DefaultHandler {
		private String version = "";

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("rts:RtsProfile")) {
				for(int intIdx=0;intIdx<attributes.getLength();intIdx++) {
					if( attributes.getQName(intIdx).equals("rts:version") ) {
						version = attributes.getValue(intIdx);
						break;
					}
				}
			}
			super.startElement(uri, localName, qName, attributes);
		}

	}

	private class RtcXMLParser extends DefaultHandler {
		private String version = "";

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("rtc:RtcProfile")) {
				for(int intIdx=0;intIdx<attributes.getLength();intIdx++) {
					if( attributes.getQName(intIdx).equals("rtc:version") ) {
						version = attributes.getValue(intIdx);
						break;
					}
				}
			}
			super.startElement(uri, localName, qName, attributes);
		}

	}

	public static String restoreConstraint(ConstraintType source) throws Exception {
		if( source==null ) 	throw new Exception(Messages.getString("XmlHandler.38"));

		StringBuffer result = new StringBuffer();
		//
		//
		ConstraintHashType hash = source.getConstraintHashType();
		if( hash!=null ) {
			result.append("{");
			for(ConstraintType type : hash.getConstraint()) {
				result.append(type.getConstraintUnitType().getKey());
				result.append(":");
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length() > 0 ) result.deleteCharAt(result.length()-1);
			result.append("}");
			return result.toString();
		}
		//
		//
		ConstraintListType list = source.getConstraintListType();
		if( list!=null ) {
			for(ConstraintType type : list.getConstraint()) {
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length() > 0 ) result.deleteCharAt(result.length()-1);
			return result.toString();
		}
		//
		//
		ConstraintUnitType unit = source.getConstraintUnitType();
		if( unit==null ) throw new Exception(Messages.getString("XmlHandler.44"));
		////
		PropertyIsEqualTo equal = unit.getPropertyIsEqualTo();
		if( equal!=null ) {
			if( equal.getLiteral()==null || equal.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.45"));
			result.append(equal.getLiteral());
		}
		////
		PropertyIsGreaterThanOrEqualTo greaterEq = unit.getPropertyIsGreaterThanOrEqualTo();
		if( greaterEq!=null ) {
			if( greaterEq.getLiteral()==null || greaterEq.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.46"));
			result.append("x>=" + greaterEq.getLiteral());
		}
		////
		PropertyIsLessThanOrEqualTo lessEq = unit.getPropertyIsLessThanOrEqualTo();
		if( lessEq!=null ) {
			if( lessEq.getLiteral()==null || lessEq.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.48"));
			result.append("x<=" + lessEq.getLiteral());
		}
		////
		PropertyIsGreaterThan greater = unit.getPropertyIsGreaterThan();
		if( greater!=null ) {
			if( greater.getLiteral()==null || greater.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.50"));
			result.append("x>" + greater.getLiteral());
		}
		////
		PropertyIsLessThan less = unit.getPropertyIsLessThan();
		if( less!=null ) {
			if( less.getLiteral()==null || less.getLiteral().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.52"));
			result.append("x<" + less.getLiteral());
		}
		////
		PropertyIsBetween between = unit.getPropertyIsBetween();
		if( between!=null ) {
			if( between.getLowerBoundary()==null || between.getLowerBoundary().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.54"));
			if( between.getUpperBoundary()==null || between.getUpperBoundary().length()<=0 )
				throw new Exception(Messages.getString("XmlHandler.55"));
			result.append(between.getLowerBoundary() + "<=x<=" + between.getUpperBoundary());
		}
		//
		//
		Or or = unit.getOr();
		if( or!=null ) {
			result.append("(");
			for(ConstraintType type : or.getConstraint() ) {
				result.append(restoreConstraint(type));
				result.append(",");
			}
			if( result.length()>0 ) result.deleteCharAt(result.length()-1);
			result.append(")");
		}
		//
		//
		And and = unit.getAnd();
		if( and!=null ) {
			//And条件で３つ以上の要素はない
			if( and.getConstraint().size() > 2 ) throw new Exception(Messages.getString("XmlHandler.60"));

			ConstraintUnitType former = and.getConstraint().get(0).getConstraintUnitType();
			ConstraintUnitType latter = and.getConstraint().get(1).getConstraintUnitType();

			if( former.getPropertyIsGreaterThan() != null || former.getPropertyIsGreaterThanOrEqualTo() != null ) {
				if( former.getPropertyIsGreaterThan() != null ) {
					result.append(former.getPropertyIsGreaterThan().getLiteral());
					result.append("<x");
				} else	if( former.getPropertyIsGreaterThanOrEqualTo() != null ) {
					result.append(former.getPropertyIsGreaterThanOrEqualTo().getLiteral());
					result.append("<=x");
				}
				//
				if( latter.getPropertyIsLessThan() != null ) {
					result.append("<");
					result.append(latter.getPropertyIsLessThan().getLiteral());
				} else if( latter.getPropertyIsLessThanOrEqualTo() != null ) {
					result.append("<=");
					result.append(latter.getPropertyIsLessThanOrEqualTo().getLiteral());
				}
			} else if( latter.getPropertyIsGreaterThan() != null || latter.getPropertyIsGreaterThanOrEqualTo() != null ) {
				if( latter.getPropertyIsGreaterThan() != null ) {
					result.append(latter.getPropertyIsGreaterThan().getLiteral());
					result.append("<x");
				} else	if( latter.getPropertyIsGreaterThanOrEqualTo() != null ) {
					result.append(latter.getPropertyIsGreaterThanOrEqualTo().getLiteral());
					result.append("<=x");
				}
				//
				if( former.getPropertyIsLessThan() != null ) {
					result.append("<");
					result.append(former.getPropertyIsLessThan().getLiteral());
				} else if( former.getPropertyIsLessThanOrEqualTo() != null ) {
					result.append("<=");
					result.append(former.getPropertyIsLessThanOrEqualTo().getLiteral());
				}
			}
		}

		return result.toString();
	}

	public static ConstraintType convertToXmlConstraint(String source) throws Exception {
		if(source==null || source.length()==0 ) throw new Exception(Messages.getString("XmlHandler.69"));
		source = source.replace(" ", "");

		ObjectFactory factory = new ObjectFactory();
		ConstraintType result = factory.createConstraintType();

		ConstraintUnitType unit = factory.createConstraintUnitType();

		if(source.trim().startsWith("(") && source.trim().endsWith(")")) {
			String body = source.trim().substring(1,source.trim().length()-1);
			String[] elem = body.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.73"));
			Or prop = factory.createOr();
			for(int index=0;index<elem.length;index++) {
				prop.getConstraint().add(convertToXmlConstraint(elem[index]));
			}
			unit.setOr(prop);

		} else 	if(source.trim().startsWith("{") && source.trim().endsWith("}")) {
			String body = source.trim().substring(1,source.trim().length()-1);
			String[] elem = body.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.77"));
			ConstraintHashType list = factory.createConstraintHashType();
			for(int index=0;index<elem.length;index++) {
				int indsep = elem[index].trim().indexOf(":");
				if(indsep<0) throw new Exception(Messages.getString("XmlHandler.79"));
				String key = elem[index].trim().substring(0,indsep);
				if(key.length()<=0) throw new Exception(Messages.getString("XmlHandler.79"));
				String value = elem[index].trim().substring(indsep+1);
				ConstraintType hashConst = convertToXmlConstraint(value);
				hashConst.getConstraintUnitType().setKey(key);
				list.getConstraint().add(hashConst);
			}
			result.setConstraintHashType(list);
			return result;

		} else 	if( source.contains(",") ) {
			String[] elem = source.trim().split(",");
			if( elem.length<=0 ) throw new Exception(Messages.getString("XmlHandler.82"));
			ConstraintListType list = factory.createConstraintListType();
			for(int index=0;index<elem.length;index++) {
				list.getConstraint().add(convertToXmlConstraint(elem[index]));
			}
			result.setConstraintListType(list);
			return result;

		} else 	if( source.contains("=") || source.contains("<") || source.contains(">") ) {
			if(source.trim().startsWith("x") || source.trim().endsWith("x")) {
				String body = "";
				if( source.trim().startsWith("x>=") || source.trim().endsWith("<=x") ) {
					if(source.trim().startsWith("x>=")) {
						body = source.trim().substring("x>=".length());
					} else if(source.trim().endsWith("<=x")) {
						body = source.trim().substring(0,source.trim().length()-"x>=".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.96"));
					PropertyIsGreaterThanOrEqualTo prop = factory.createPropertyIsGreaterThanOrEqualTo();
					prop.setLiteral(body);
					unit.setPropertyIsGreaterThanOrEqualTo(prop);

				} else if( source.trim().startsWith("x>") || source.trim().endsWith("<x") ) {
					if(source.trim().startsWith("x>")) {
						body = source.trim().substring("x>".length());
					} else if(source.trim().endsWith("<x")) {
						body = source.trim().substring(0,source.trim().length()-"<x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.104"));
					PropertyIsGreaterThan prop = factory.createPropertyIsGreaterThan();
					prop.setLiteral(body);
					unit.setPropertyIsGreaterThan(prop);

				} else if( source.trim().startsWith("x<=") || source.trim().endsWith("=>x") ) {
					if(source.trim().startsWith("x<=")) {
						body = source.trim().substring("x<=".length());
					} else if(source.trim().endsWith("=>x")) {
						body = source.trim().substring(0,source.trim().length()-"=>x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.112"));
					PropertyIsLessThanOrEqualTo prop = factory.createPropertyIsLessThanOrEqualTo();
					prop.setLiteral(body);
					unit.setPropertyIsLessThanOrEqualTo(prop);

				} else if( source.trim().startsWith("x<") || source.trim().endsWith(">x") ) {
					if(source.trim().startsWith("x<")) {
						body = source.trim().substring("x<".length());
					} else if(source.trim().endsWith(">x")) {
						body = source.trim().substring(0,source.trim().length()-">x".length());
					}
					if(body.length()==0 || !body.matches("^[-,+]?[0-9.]*"))
						throw new Exception(Messages.getString("XmlHandler.120"));
					PropertyIsLessThan prop = factory.createPropertyIsLessThan();
					prop.setLiteral(body);
					unit.setPropertyIsLessThan(prop);
				} else {
					throw new Exception(Messages.getString("XmlHandler.121"));
				}
			} else	if( source.trim().contains("<=x<=") || source.trim().contains("=>x=>") ) {
				int index = -1;
				String lower = "";
				String upper = "";
				if( source.trim().contains("<=x<=") ) {
					index = source.trim().indexOf("<=x<=");
					lower = source.trim().substring(0,index);
					upper = source.trim().substring(index+"<=x<=".length());
				} else 	if( source.trim().contains("=>x=>") ) {
					index = source.trim().indexOf("=>x=>");
					upper = source.trim().substring(0,index);
					lower = source.trim().substring(index+"=>x=>".length());
				}
				if(lower.length()==0 || !lower.matches("^[-,+]?[0-9.]*") ||
						upper.length()==0 || !upper.matches("^[-,+]?[0-9.]*") ||
						Double.valueOf(lower).doubleValue() > Double.valueOf(upper).doubleValue()) {
					throw new Exception(Messages.getString("XmlHandler.134"));
				}
				PropertyIsBetween prop = factory.createPropertyIsBetween();
				prop.setLowerBoundary(lower);
				prop.setUpperBoundary(upper);
				unit.setPropertyIsBetween(prop);

			} else	if( source.trim().contains("x") ) {
				int index = source.trim().indexOf("x");
				String former = source.trim().substring(0,index+1);
				String latter = source.trim().substring(index);
				And prop = factory.createAnd();
				prop.getConstraint().add(convertToXmlConstraint(former));
				prop.getConstraint().add(convertToXmlConstraint(latter));
				unit.setAnd(prop);

			} else {
				throw new Exception(Messages.getString("XmlHandler.137"));
			}

		} else {
			//=
			PropertyIsEqualTo equal = factory.createPropertyIsEqualTo();
			equal.setLiteral(source);
			unit.setPropertyIsEqualTo(equal);
		}
		result.setConstraintUnitType(unit);

		return result;
	}

	public boolean validateXmlRtcBySchema(String targetString) throws Exception {
		try {
		    SAXParserFactory spfactory = SAXParserFactory.newInstance();
		    SAXParser parser = spfactory.newSAXParser();
		    RtcXMLParser xmlParser = new RtcXMLParser();
		    StringReader xmlReader = new StringReader(targetString);
		    parser.parse(new InputSource(xmlReader), xmlParser);
		    String targetClass = null;
		    if( xmlParser.version.equals("0.2") ) {
		    	targetClass = "org.openrtp.namespaces.rtc.version02";
		    } else if( xmlParser.version.equals("0.3") ) {
		    	targetClass = "org.openrtp.namespaces.rtc.version03";
		    }
		    if( targetClass==null ) {
		    	throw new Exception("XML Parse Error");
		    }

			JAXBContext jc = JAXBContext.newInstance(targetClass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(getClass().getResource("/RtcProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StringReader(targetString))).getValue();

		} catch (JAXBException e) {
			throw new JAXBException("XML Validation Error.", e);
		}
		return true;
	}

	public boolean validateXmlRtsBySchema(String targetString) throws Exception {
		try {
		    SAXParserFactory spfactory = SAXParserFactory.newInstance();
		    SAXParser parser = spfactory.newSAXParser();
		    RtsXMLParser xmlParser = new RtsXMLParser();
		    StringReader xmlReader = new StringReader(targetString);
		    parser.parse(new InputSource(xmlReader), xmlParser);
		    String targetClass = null;
		    if( xmlParser.version.equals("0.1") ) {
		    	targetClass = "org.openrtp.namespaces.rts.version01";
		    } else if( xmlParser.version.equals("0.2") ) {
		    	targetClass = "org.openrtp.namespaces.rts.version02";
		    }
		    if( targetClass==null ) {
		    	throw new Exception("XML Parse Error");
		    }

			JAXBContext jc = JAXBContext.newInstance(targetClass);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(getClass().getResource("/RtsProfile_ext.xsd"));
			unmarshaller.setSchema(schema);

			((JAXBElement<?>)unmarshaller.unmarshal(new StringReader(targetString))).getValue();

		} catch (JAXBException e) {
			throw new JAXBException("XML Validation Error.", e);
		}
		return true;
	}

	/**
	 * XMLGregorianCalendar を任意の日付で生成します。(Map指定)
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Map<String, Integer> dateY) {
		return createXMLGregorianCalendar((dateY.get("year")).intValue(), (dateY.get("month")).intValue(),
				(dateY.get("day")).intValue(), (dateY.get("hour")).intValue(), (dateY.get("minute")).intValue(),
				(dateY.get("second")).intValue());
	}

	/**
	 * XMLGregorianCalendar を任意の日付で生成します。(年、月、日、時、分、秒指定)
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(int year, int month, int day, int hourOfDay,
			int minute, int second) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(year, month - 1, day, hourOfDay, minute, second);
		c.set(GregorianCalendar.MILLISECOND, 0);
		return createXMLGregorianCalendar(c);
	}

	/**
	 * XMLGregorianCalendar を任意の日付で生成します。(文字列指定 yyyy-MM-ddTHH:mm:ss)
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			sdf.setLenient(false);
			return createXMLGregorianCalendar(sdf.parse(date));
		} catch (Exception e) {
			throw new RuntimeException("Fail to create xml date.", e);
		}
	}

	/**
	 * XMLGregorianCalendar を任意の日付で生成します。(日付指定)
	 */
	public static XMLGregorianCalendar createXMLGregorianCalendar(Date date) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		return createXMLGregorianCalendar(c);
	}

	public static XMLGregorianCalendar createXMLGregorianCalendar(GregorianCalendar cal) {
		try {
			XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			return xmlDate;
		} catch (Exception e) {
			throw new RuntimeException("Fail to create xml date.", e);
		}
	}

	private RtcProfile convertRtcProfile02to03(Object source) {
		RtcProfile result03 = null;
		org.openrtp.namespaces.rtc.version02.RtcProfile profile02 = (org.openrtp.namespaces.rtc.version02.RtcProfile)source;
		if( source==null ) return result03;
		ObjectFactory factory = new ObjectFactory();
		//BasicInfo
		result03 = factory.createRtcProfile();
		org.openrtp.namespaces.rtc.version02.BasicInfo basic02 = profile02.getBasicInfo();
		result03.setVersion("0.3");
		result03.setId(profile02.getId());
		if( basic02!=null ) {
			BasicInfoExt basic03 = factory.createBasicInfoExt();
			//Basic Profile
			basic03.setName(basic02.getName());
			basic03.setComponentType(basic02.getComponentType());
			basic03.setActivityType(basic02.getActivityType());
			basic03.setComponentKind(basic02.getComponentKind());
			basic03.setCategory(basic02.getCategory());
			basic03.setDescription(basic02.getDescription());
			basic03.setExecutionRate(basic02.getExecutionRate());
			basic03.setExecutionType(basic02.getExecutionType());
			basic03.setMaxInstances(basic02.getMaxInstances());
			basic03.setVendor(basic02.getVendor());
			basic03.setVersion(basic02.getVersion());
			basic03.setAbstract(basic02.getAbstract());
			basic03.setCreationDate(basic02.getCreationDate());
			basic03.setUpdateDate(basic02.getUpdateDate());
			basic03.setRtcType(basic02.getRtcType());
			//Doc Profile
			if( basic02 instanceof org.openrtp.namespaces.rtc.version02.BasicInfoDoc) {
				org.openrtp.namespaces.rtc.version02.DocBasic doc02 = ((org.openrtp.namespaces.rtc.version02.BasicInfoDoc)basic02).getDoc();
				if( doc02!=null ) {
					DocBasic doc03 = factory.createDocBasic();
					doc03.setDescription(doc02.getDescription());
					doc03.setInout(doc02.getInout());
					doc03.setAlgorithm(doc02.getAlgorithm());
					doc03.setCreator(doc02.getCreator());
					doc03.setLicense(doc02.getLicense());
					doc03.setReference(doc02.getReference());
					basic03.setDoc(doc03);
				}
				//Ext Profile
				if( basic02 instanceof org.openrtp.namespaces.rtc.version02.BasicInfoExt) {
					basic03.getVersionUpLogs().addAll(((org.openrtp.namespaces.rtc.version02.BasicInfoExt)basic02).getVersionUpLogs());
					basic03.setHardwareProfile(basic02.getHardwareProfile());
					basic03.setSaveProject(((org.openrtp.namespaces.rtc.version02.BasicInfoExt) basic02).getSaveProject());
					List<org.openrtp.namespaces.rtc.version02.Property> properties02 = ((org.openrtp.namespaces.rtc.version02.BasicInfoExt)basic02).getProperties();
					if( properties02!=null ) {
						List<Property> properties03 = new ArrayList<Property>();
						for( org.openrtp.namespaces.rtc.version02.Property property02 : properties02 ) {
							Property property = factory.createProperty();
							property.setName(property02.getName());
							property.setValue(property02.getValue());
							properties03.add(property);
						}
						basic03.getProperties().addAll(properties03);
					}
				}
			}
			result03.setBasicInfo(basic03);
		}
		//Actions
		org.openrtp.namespaces.rtc.version02.Actions actions02 = profile02.getActions();
		if( actions02!=null ) {
			Actions actions03 = factory.createActions();
			//Initialize
			org.openrtp.namespaces.rtc.version02.ActionStatus action02 = actions02.getOnInitialize();
			ActionStatusDoc action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnInitialize(action03);
			//Finalize
			action02 = actions02.getOnFinalize();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnFinalize(action03);
			//Startup
			action02 = actions02.getOnStartup();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnStartup(action03);
			//Shutdown
			action02 = actions02.getOnShutdown();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnShutdown(action03);
			//Activated
			action02 = actions02.getOnActivated();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnActivated(action03);
			//Deactivated
			action02 = actions02.getOnDeactivated();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnDeactivated(action03);
			//Aborting
			action02 = actions02.getOnAborting();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnAborting(action03);
			//Error
			action02 = actions02.getOnError();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnError(action03);
			//Reset
			action02 = actions02.getOnReset();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnReset(action03);
			//Execute
			action02 = actions02.getOnExecute();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnExecute(action03);
			//StateUpdate
			action02 = actions02.getOnStateUpdate();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnStateUpdate(action03);
			//RateChanged
			action02 = actions02.getOnRateChanged();
			action03 = convertAction02to03(factory, actions03, action02);
			actions03.setOnRateChanged(action03);
			//
			result03.setActions(actions03);
		}
		//Configuration
		org.openrtp.namespaces.rtc.version02.ConfigurationSet configset02 = profile02.getConfigurationSet();
		if( configset02!=null ) {
			ConfigurationSet configset03 = factory.createConfigurationSet();
			List<org.openrtp.namespaces.rtc.version02.Configuration> configList02 = configset02.getConfiguration();
			for(org.openrtp.namespaces.rtc.version02.Configuration config02 : configList02) {
				//Basic Profile
				ConfigurationExt config03 = factory.createConfigurationExt();
				config03.setName(config02.getName());
				config03.setType(config02.getType());
				config03.setDefaultValue(config02.getDefaultValue());
				config03.setUnit(config02.getUnit());
				config03.setConstraint(config02.getConstraint());
				//Doc Profile
				if( config02 instanceof org.openrtp.namespaces.rtc.version02.ConfigurationDoc) {
					org.openrtp.namespaces.rtc.version02.DocConfiguration doc02 = ((org.openrtp.namespaces.rtc.version02.ConfigurationDoc)config02).getDoc();
					if( doc02!=null ) {
						DocConfiguration docConfig03 = factory.createDocConfiguration();
						docConfig03.setDataname(doc02.getDataname());
						docConfig03.setDefaultValue(doc02.getDefaultValue());
						docConfig03.setDescription(doc02.getDescription());
						docConfig03.setUnit(doc02.getUnit());
						docConfig03.setRange(doc02.getRange());
						docConfig03.setConstraint(doc02.getConstraint());
						config03.setDoc(docConfig03);
					}
				}
				//Ext Profile
				if( config02 instanceof org.openrtp.namespaces.rtc.version02.ConfigurationExt) {
					config03.setVariableName(((org.openrtp.namespaces.rtc.version02.ConfigurationExt) config02).getVariableName());
					config03.setComment(((org.openrtp.namespaces.rtc.version02.ConfigurationExt) config02).getComment());
					List<org.openrtp.namespaces.rtc.version02.Property> properties02 = ((org.openrtp.namespaces.rtc.version02.ConfigurationExt)config02).getProperties();
					if( properties02!=null ) {
						config03.getProperties().addAll(convertRtcProperty02to03(factory, properties02));
					}
				}
				configset03.getConfiguration().add(config03);
			}
			result03.setConfigurationSet(configset03);
		}
		//DataPort
		List<org.openrtp.namespaces.rtc.version02.Dataport> dataPorts02 = profile02.getDataPorts();
		if( dataPorts02!=null ) {
			List<Dataport> dataPorts03 = new ArrayList<Dataport>();
			for( org.openrtp.namespaces.rtc.version02.Dataport dataport02 : dataPorts02 ) {
				//Basic Profile
				DataportExt dataPort03 = factory.createDataportExt();
				dataPort03.setPortType(dataport02.getPortType());
				dataPort03.setName(dataport02.getName());
				dataPort03.setType(dataport02.getType());
				dataPort03.setIdlFile(dataport02.getIdlFile());
				dataPort03.setInterfaceType(dataport02.getInterfaceType());
				dataPort03.setDataflowType(dataport02.getDataflowType());
				dataPort03.setSubscriptionType(dataport02.getSubscriptionType());
				dataPort03.setUnit(dataport02.getUnit());
				//Doc Profile
				if( dataport02 instanceof org.openrtp.namespaces.rtc.version02.DataportDoc) {
					org.openrtp.namespaces.rtc.version02.DocDataport docDataPort02 = ((org.openrtp.namespaces.rtc.version02.DataportDoc)dataport02).getDoc();
					if( docDataPort02!=null ) {
						DocDataport docDataPort03 = factory.createDocDataport();
						docDataPort03.setDescription(docDataPort02.getDescription());
						docDataPort03.setType(docDataPort02.getType());
						docDataPort03.setNumber(docDataPort02.getNumber());
						docDataPort03.setSemantics(docDataPort02.getSemantics());
						docDataPort03.setUnit(docDataPort02.getUnit());
						docDataPort03.setOccerrence(docDataPort02.getOccerrence());
						docDataPort03.setOperation(docDataPort02.getOperation());
						dataPort03.setDoc(docDataPort03);
					}
					//Ext Profile
					if( dataport02 instanceof org.openrtp.namespaces.rtc.version02.DataportExt) {
						dataPort03.setVariableName(((org.openrtp.namespaces.rtc.version02.DataportExt)dataport02).getVariableName());
						dataPort03.setPosition(getPosition((org.openrtp.namespaces.rtc.version02.DataportExt)dataport02));
						List<org.openrtp.namespaces.rtc.version02.Property> properties02 = ((org.openrtp.namespaces.rtc.version02.DataportExt)dataport02).getProperties();
						if( properties02!=null ) {
							dataPort03.getProperties().addAll(convertRtcProperty02to03(factory, properties02));
						}
					}
				}
				dataPorts03.add(dataPort03);
			}
			result03.getDataPorts().addAll(dataPorts03);
		}
		//ServicePort
		List<org.openrtp.namespaces.rtc.version02.Serviceport> servicePorts02 = profile02.getServicePorts();
		if( servicePorts02!=null ) {
			List<Serviceport> servicePorts03 = new ArrayList<Serviceport>();
			for( org.openrtp.namespaces.rtc.version02.Serviceport serviceport02 : servicePorts02 ) {
				//Basic Profile
				ServiceportExt servicePort03 = factory.createServiceportExt();
				servicePort03.setName(serviceport02.getName());
				//Doc Profile
				if( serviceport02 instanceof org.openrtp.namespaces.rtc.version02.ServiceportDoc) {
					org.openrtp.namespaces.rtc.version02.DocServiceport docServicePort02 = ((org.openrtp.namespaces.rtc.version02.ServiceportDoc)serviceport02).getDoc();
					if( docServicePort02!=null ) {
						DocServiceport docServicePort03 = factory.createDocServiceport();
						docServicePort03.setDescription(docServicePort02.getDescription());
						docServicePort03.setIfdescription(docServicePort02.getIfdescription());
						servicePort03.setDoc(docServicePort03);
					}
					//Ext Profile
					if( serviceport02 instanceof org.openrtp.namespaces.rtc.version02.ServiceportExt) {
						servicePort03.setPosition(getPosition((org.openrtp.namespaces.rtc.version02.ServiceportExt)serviceport02));
						List<org.openrtp.namespaces.rtc.version02.Property> properties02 = ((org.openrtp.namespaces.rtc.version02.ServiceportExt)serviceport02).getProperties();
						if( properties02!=null ) {
							servicePort03.getProperties().addAll(convertRtcProperty02to03(factory, properties02));
						}
					}
				}
				servicePorts03.add(servicePort03);
				//Service Interface
				List<org.openrtp.namespaces.rtc.version02.Serviceinterface> serviceIFs02 = serviceport02.getServiceInterface();
				if( serviceIFs02!=null ) {
					List<ServiceinterfaceExt> serviceIFs03 = new ArrayList<ServiceinterfaceExt>();
					for( org.openrtp.namespaces.rtc.version02.Serviceinterface serviceIF02 : serviceIFs02 ) {
						//Basic Profile
						ServiceinterfaceExt serviceIF03 = factory.createServiceinterfaceExt();
						serviceIF03.setName(serviceIF02.getName());
						serviceIF03.setDirection(serviceIF02.getDirection());
						serviceIF03.setInstanceName(serviceIF02.getInstanceName());
						serviceIF03.setIdlFile(serviceIF02.getIdlFile());
						serviceIF03.setType(serviceIF02.getType());
						//Doc Profile
						if( serviceIF02 instanceof org.openrtp.namespaces.rtc.version02.ServiceinterfaceDoc) {
							org.openrtp.namespaces.rtc.version02.DocServiceinterface docServiceIF02 = ((org.openrtp.namespaces.rtc.version02.ServiceinterfaceDoc)serviceIF02).getDoc();
							if( docServiceIF02!=null ) {
								DocServiceinterface docServiceIF03 = factory.createDocServiceinterface();
								docServiceIF03.setDescription(docServiceIF02.getDescription());
								docServiceIF03.setDocArgument(docServiceIF02.getDocArgument());
								docServiceIF03.setDocReturn(docServiceIF02.getDocReturn());
								docServiceIF03.setDocException(docServiceIF02.getDocException());
								docServiceIF03.setDocPreCondition(docServiceIF02.getDocPreCondition());
								docServiceIF03.setDocPostCondition(docServiceIF02.getDocPostCondition());
								serviceIF03.setDoc(docServiceIF03);
							}
							//Ext Profile
							if( serviceIF02 instanceof org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt) {
								serviceIF03.setVariableName(((org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt) serviceIF02).getVariableName());
								List<org.openrtp.namespaces.rtc.version02.Property> properties02 = ((org.openrtp.namespaces.rtc.version02.ServiceinterfaceExt)serviceIF02).getProperties();
								if( properties02!=null ) {
									serviceIF03.getProperties().addAll(convertRtcProperty02to03(factory, properties02));
								}
							}
						}
						serviceIFs03.add(serviceIF03);
					}
					servicePort03.getServiceInterface().addAll(serviceIFs03);
				}
			}
			result03.getServicePorts().addAll(servicePorts03);
		}
		//Language
		org.openrtp.namespaces.rtc.version02.Language lang02 = profile02.getLanguage();
		if( lang02!=null ) {
			LanguageExt lang = factory.createLanguageExt();
			lang.setKind(lang02.getKind());
			if( lang02 instanceof org.openrtp.namespaces.rtc.version02.LanguageExt) {
				org.openrtp.namespaces.rtc.version02.LanguageExt langExt02 = (org.openrtp.namespaces.rtc.version02.LanguageExt)lang02;
				if( langExt02!=null ) {
					List<org.openrtp.namespaces.rtc.version02.TargetEnvironment> envs02 = langExt02.getTargets();
					for( org.openrtp.namespaces.rtc.version02.TargetEnvironment env02 : envs02 ) {
						TargetEnvironment env = factory.createTargetEnvironment();
						env.setOs(env02.getOs());
						env.getOsVersions().addAll(new ArrayList<String>(env02.getOsVersions()));
						env.setOther(env02.getOther());
						env.setLangVersion(env02.getLangVersion());
						env.getCpus().addAll(new ArrayList<String>(env02.getCpus()));
						env.setCpuOther(env02.getCpuOther());
						for( org.openrtp.namespaces.rtc.version02.Library library : env02.getLibraries() ) {
							Library lib = factory.createLibrary();
							lib.setName(library.getName());
							lib.setVersion(library.getVersion());
							lib.setOther(library.getOther());
							env.getLibraries().add(lib);
						}
						lang.getTargets().add(env);
					}
				}
			}
			result03.setLanguage(lang);
		}

		return result03;
	}
	
	private ActionStatusDoc convertAction02to03(ObjectFactory factory, Actions actions03, org.openrtp.namespaces.rtc.version02.ActionStatus action02) {
		ActionStatusDoc action03 = null;
		if( action02!=null ) {
			action03 = factory.createActionStatusDoc();
			action03.setImplemented(action02.getImplemented());
			if( action02 instanceof org.openrtp.namespaces.rtc.version02.ActionStatusDoc ) {
				org.openrtp.namespaces.rtc.version02.DocAction docAction02 = ((org.openrtp.namespaces.rtc.version02.ActionStatusDoc)action02).getDoc();
				if( docAction02!=null ) {
					DocAction docAction03 = factory.createDocAction();
					docAction03.setDescription(docAction02.getDescription());
					docAction03.setPreCondition(docAction02.getPreCondition());
					docAction03.setPostCondition(docAction02.getPostCondition());
					action03.setDoc(docAction03);
				}
			}
		}
		return action03;
	}
	
	private List<Property> convertRtcProperty02to03(ObjectFactory factory, List<org.openrtp.namespaces.rtc.version02.Property> properties02) {
		List<Property> properties03 = new ArrayList<Property>();
		for( org.openrtp.namespaces.rtc.version02.Property property02 : properties02 ) {
			Property property = factory.createProperty();
			property.setName(property02.getName());
			property.setValue(property02.getValue());
			properties03.add(property);
		}
		return properties03;
	}
	
	private Position getPosition(
			org.openrtp.namespaces.rtc.version02.DataportExt dataport02) {
		if (dataport02.getPosition() == null) return null;
		if (dataport02.getPosition().name() == null) return null;
		return Position.fromValue(dataport02.getPosition().name().toUpperCase());
	}
	
	private Position getPosition(
			org.openrtp.namespaces.rtc.version02.ServiceportExt serviceport02) {
		if (serviceport02.getPosition() == null) return null;
		if (serviceport02.getPosition().name() == null) return null;
		return Position.fromValue(serviceport02.getPosition().name().toUpperCase());
	}
}
