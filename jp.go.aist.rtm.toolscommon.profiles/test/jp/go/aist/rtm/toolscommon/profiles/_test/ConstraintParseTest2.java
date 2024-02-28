package jp.go.aist.rtm.toolscommon.profiles._test;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class ConstraintParseTest2 extends TestBase {
	
	public void testMix() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash2.xml";
		String expected = readFile(resourceFile,"\n");

//		ConstraintType type = XmlHandler.convertToXmlConstraint("{key0:(ichi,one),key1:0.0<x<10.0}");
		ConstraintType type = XmlHandler.convertToXmlConstraint("{el1:100<=x,el2:150}");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
}
