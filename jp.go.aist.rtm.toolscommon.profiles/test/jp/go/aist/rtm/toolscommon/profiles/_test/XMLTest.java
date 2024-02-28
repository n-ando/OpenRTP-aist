package jp.go.aist.rtm.toolscommon.profiles._test;

import org.openrtp.namespaces.rtc.version03.RtcProfile;

import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;

public class XMLTest extends TestBase {

	public void testRtcXmlExport() {
		String resourceDir = rootPath +  "\\resource\\RTC\\RtcSampleVer02.xml";
		String expected = readFile(resourceDir,"\n");
		
		SampleProfileGenerator handle = new SampleProfileGenerator();
		String result = null;
		try {
			RtcProfile profile = handle.generateProfile();
			XmlHandler handler = new XmlHandler();
			result = handler.convertToXmlRtc(profile);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(expected, result);
	}
	
	public void testRtcXmlImportVer02() throws Exception{
		String resourceDir = rootPath +  "\\resource\\RTC\\RtcSampleVer02.xml";
		String expected = readFile(resourceDir,"\n");
		XmlHandler handler = new XmlHandler();
		RtcProfile profile = null;
		try {
			profile = handler.restoreFromXmlRtc(expected);
		} catch(Exception ex) {
			fail(ex.getCause().getMessage());
		}
		checkDetailVer02(profile);
	}
}
