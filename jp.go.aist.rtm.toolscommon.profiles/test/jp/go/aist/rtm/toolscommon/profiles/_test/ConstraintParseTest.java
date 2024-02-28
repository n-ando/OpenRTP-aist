package jp.go.aist.rtm.toolscommon.profiles._test;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class ConstraintParseTest extends TestBase {
	
	public void testHashErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("{el1:,el2:150}");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testHashErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("{:10,el2:150}");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testHash2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash2.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("{el1:x>=100,el2:150}");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}

	public void testHash() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("{el1:10,el2:150}");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testArrayErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("100<=x,,200");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testArray2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array2.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x,x<150,200");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testArray() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("10,150");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testENUMErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("(,)");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testENUMErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("()");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testENUM() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Enum.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("(100,150)");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGEL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualLess.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x<150");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
//	public void testGLEErr3() throws Exception{
//		try {
//			XmlHandler.convertToXmlConstraint("100>x=>150");
//			fail();
//		} catch( Exception ex) {
//			
//		}
//	}
	
	public void testGLE2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("150=>x>");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGLE2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("=>x>100");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testGLEErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<x<=test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGLEErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<x<=150");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGLE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual2.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("150=>x>100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testGLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<x<=150");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testBetErr3() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("100=>x=>150");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testBet2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("150=>x=>");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testBet2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("=>x=>100");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testBetErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<=x<=test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testBetErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<=x<=150");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testBet2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Bet.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("150=>x=>100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testBet() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Bet.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x<=150");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testL2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("test>x");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testL2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint(">x");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testLErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x<test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testLErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x<");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testLF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessF.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<-10.07");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}

	public void testL2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Less.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100>x");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Less.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testG2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("test<x");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testG2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<x");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testGErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x>test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x>");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterF.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>-100.8");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}

	public void testG2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Greater.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<x");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testG() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Greater.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testLE2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("test=>x");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testLE2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("=>x");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testLEErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x<=test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testLEErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x<=");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testLEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqualF.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<=-100.5");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	

	public void testLE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqual.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100=>x");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqual.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("x<=100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	//
	public void testGE2Err2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("test<=x");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGE2Err() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("<=x");
			fail();
		} catch( Exception ex) {
			
		}
	}

	public void testGE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqual.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100<=x");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testGEErr2() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x>=test");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGEErr() throws Exception{
		try {
			XmlHandler.convertToXmlConstraint("x>=");
			fail();
		} catch( Exception ex) {
			
		}
	}
	
	public void testGEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualF.xml";
		String expected = readFile(resourceFile,"\n");
		

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>=-100.5");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testGE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqual.xml";
		String expected = readFile(resourceFile,"\n");
		

		ConstraintType type = XmlHandler.convertToXmlConstraint("x>=100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
	
	public void testEqualStr2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr2.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("xyx");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}

	public void testEqualStr() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("test");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}

	public void testEqual() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualTo.xml";
		String expected = readFile(resourceFile,"\n");

		ConstraintType type = XmlHandler.convertToXmlConstraint("100");
		RtcProfile profile = createConstraintBase(type);
		XmlHandler handler = new XmlHandler();
		String result = handler.convertToXmlRtc(profile);

		assertEquals(expected, result);
	}
}
