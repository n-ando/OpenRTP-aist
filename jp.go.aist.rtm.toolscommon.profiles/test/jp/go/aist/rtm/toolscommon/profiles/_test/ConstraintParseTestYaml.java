package jp.go.aist.rtm.toolscommon.profiles._test;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.profiles.util.YamlHandler;

import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class ConstraintParseTestYaml extends TestBase {
	
	public void testHash2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash2.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("{el1:x>=100,el2:150}");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testHash() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("{el1:10,el2:150}");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testArray2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array2.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x,x<150,200");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testArray() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("10,150");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testENUM() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Enum.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("(100,150)");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGEL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualLess.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x<150");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGLE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual2.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("150=>x>100");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testGLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<x<=150");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testBet() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Bet.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x<=150");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testLF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessF.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<-10.07");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Less.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<100");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterF.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>-100.8");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testG2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Greater.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<x");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	
	//
	public void testLEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqualF.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<=-100.5");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqual.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<=100");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqual.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testGEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualF.yaml";
		String expected = readFile(resourceFile,"\r\n");
		

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>=-100.5");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
	
	//
	public void testEqualStr2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr2.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("xyx");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testEqualStr() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("test");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}

	public void testEqual() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualTo.yaml";
		String expected = readFile(resourceFile,"\r\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100");
		RtcProfile profile = createConstraintBase(type);
		YamlHandler handler = new YamlHandler();
		String result = handler.convertToYamlRtc(profile);

		assertEquals(expected, result);
	}
}
