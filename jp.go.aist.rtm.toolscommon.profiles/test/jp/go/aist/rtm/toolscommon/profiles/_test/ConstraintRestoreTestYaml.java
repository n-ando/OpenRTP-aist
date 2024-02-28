package jp.go.aist.rtm.toolscommon.profiles._test;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.profiles.util.YamlHandler;

import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class ConstraintRestoreTestYaml extends TestBase {
	
	public void testHash2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash2.yaml";
		String source = readFile(resourceFile,"\n");

		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("{el1:x>=100,el2:150}", result);
	}

	public void testHash() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Hash.yaml";
		String source = readFile(resourceFile,"\n");

		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("{el1:10,el2:150}", result);
	}
	//
	public void testArray2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array2.yaml";
		String source = readFile(resourceFile,"\n");

		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("x>=100,x<150,200", result);
	}
	
	public void testArray() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Array.yaml";
		String source = readFile(resourceFile,"\n");

		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("10,150", result);
	}
	//
	public void testENUM() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Enum.yaml";
		String source = readFile(resourceFile,"\n");

		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("(100,150)", result);
	}
	//
	public void testGEL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualLess.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<=x<150", result);
	}
	//
	public void testGLE2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual2.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);
		
		assertEquals("100<x<=150", result);
	}
	
	public void testGLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterLessEqual.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<x<=150", result);
	}
	//
	public void testBet() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Bet.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100<=x<=150", result);
	}
	//
	public void testLF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessF.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<-10.07", result);
	}

	public void testL() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Less.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<100", result);
	}
	//
	public void testGF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterF.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>-100.8", result);
	}

	public void testG() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\Greater.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>100", result);
	}
	//
	public void testLEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqualF.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<=-100.5", result);
	}

	public void testLE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\LessEqual.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x<=100", result);
	}
	//
	public void testGEF() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqualF.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>=-100.5", result);
	}
	
	public void testGE() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\GreaterEqual.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("x>=100", result);
	}
	
	public void testEqualStr2() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr2.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("xyx", result);
	}

	public void testEqualStr() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualToStr.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("test", result);
	}

	public void testEqual() throws Exception{
		String resourceFile = rootPath +  "\\resource\\RTC\\Constraint\\EqualTo.yaml";
		String source = readFile(resourceFile,"\n");
		YamlHandler handler = new YamlHandler();
		RtcProfile profile = handler.restoreFromYamlRtc(source);
		ConstraintType type = profile.getConfigurationSet().getConfiguration().get(0).getConstraint();
		
		String result = XmlHandler.restoreConstraint(type);

		assertEquals("100", result);
	}
}
