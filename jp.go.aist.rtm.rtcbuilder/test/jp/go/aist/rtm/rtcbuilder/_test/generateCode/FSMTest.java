package jp.go.aist.rtm.rtcbuilder._test.generateCode;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.fsm.ScXMLHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;

public class FSMTest extends TestBase {

	protected void setUp() throws Exception {
	}

	private List<GeneratedResult> generateCode(String rtcProfile, String fsmProfile) throws Exception {
		ProfileHandler handler = new ProfileHandler(true);
		GeneratorParam genParam = handler.restorefromXMLFile(rtcProfile, true);
		
		ScXMLHandler scHandler = new ScXMLHandler();
		StringBuffer buffer = new StringBuffer();
		StateParam rootState = scHandler.parseSCXML(fsmProfile, buffer);
		if(rootState!=null) {
			genParam.getRtcParam().setFsmParam(rootState);
			genParam.getRtcParam().setFsmContents(buffer.toString());
			genParam.getRtcParam().parseEvent();
			
		}
		
		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		return result;
	}

	private void checkGeneratedCode(List<GeneratedResult> result, String resourceDir) {
		checkCode(result, resourceDir, "README.md");
		
		checkCode(result, resourceDir, "include/ModuleName/CMakeLists.txt");
		checkCode(result, resourceDir, "include/ModuleName/ModuleName.h");
		checkCode(result, resourceDir, "include/ModuleName/ModuleNameFSM.h");
		
		checkCode(result, resourceDir, "src/CMakeLists.txt");
		checkCode(result, resourceDir, "src/ModuleName.cpp");
		checkCode(result, resourceDir, "src/ModuleNameComp.cpp");
		checkCode(result, resourceDir, "src/ModuleNameFSM.cpp");
		
		checkCode(result, resourceDir, "test/include/ModuleNameTest/ModuleNameTest.h");
		checkCode(result, resourceDir, "test/src/CMakeLists.txt");
		checkCode(result, resourceDir, "test/src/ModuleNameTest.cpp");
		checkCode(result, resourceDir, "test/src/ModuleNameTestComp.cpp");
	}

	public void testBasic() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/basic/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/basic/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/basic/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}

	public void testPortName() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/portName/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/portName/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/portName/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testEventName() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/eventName/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/eventName/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/eventName/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testEventCond() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/eventCond/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/eventCond/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/eventCond/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testEventType() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/eventType/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/eventType/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/eventType/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testEventDoc() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/eventDoc/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/eventDoc/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/eventDoc/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testStateEntry() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/stateEntry/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/stateEntry/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/stateEntry/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
}
