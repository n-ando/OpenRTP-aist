package jp.go.aist.rtm.toolscommon.profiles._test;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class ConstraintRestoreTest extends TestBase {
	
	public void testHash2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash2.xml";
		String source = readFile(resourceFile,"\n");

		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("{el1:x>=100,el2:150}", result);
	}

	public void testHash() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash.xml";
		String source = readFile(resourceFile,"\n");

		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("{el1:10,el2:150}", result);
	}
	//
	public void testArray2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array2.xml";
		String source = readFile(resourceFile,"\n");

		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("x>=100,x<150,200", result);
	}
	
	public void testArray() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array.xml";
		String source = readFile(resourceFile,"\n");

		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("10,150", result);
	}
	//
	public void testENUM() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Enum.xml";
		String source = readFile(resourceFile,"\n");

		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("(100,150)", result);
	}
	//
	public void testGEL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualLess.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<=x<150", result);
	}
	//
	public void testGLE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual2.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("100<x<=150", result);
	}
	
	public void testGLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<x<=150", result);
	}
	//
	public void testBet() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Bet.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<=x<=150", result);
	}
	//
	public void testLF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessF.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<-10.07", result);
	}

	public void testL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Less.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<100", result);
	}
	//
	public void testGF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterF.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>-100.8", result);
	}

	public void testG() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Greater.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>100", result);
	}
	//
	public void testLEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqualF.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<=-100.5", result);
	}

	public void testLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqual.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<=100", result);
	}
	//
	public void testGEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualF.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>=-100.5", result);
	}
	
	public void testGE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqual.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>=100", result);
	}
	
	public void testEqualStr2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr2.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("xyx", result);
	}

	public void testEqualStr() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("test", result);
	}

	public void testEqual() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualTo.xml";
		String source = readFile(resourceFile,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = handler.restoreFromXmlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100", result);
	}
}
