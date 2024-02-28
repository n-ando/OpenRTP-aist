package jp.go.aist.rtm.rtcbuilder.java._test._100;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.fsm.ScXMLHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.java._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.java.manager.JavaGenerateManager;

public class FSMTest extends TestBase {

	protected void setUp() throws Exception {
	}

	private List<GeneratedResult> generateCode(String rtcProfile, String fsmProfile) throws Exception {
		ProfileHandler handler = new ProfileHandler(true);
		handler.addManager(new JavaGenerateManager());
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
		generator.addGenerateManager(new JavaGenerateManager());
		generator.addGenerateManager(new JavaCMakeGenerateManager());
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		return result;
	}

	private void checkGeneratedCode(List<GeneratedResult> result, String resourceDir) {
		checkCode(result, resourceDir, "README.md");
		checkCode(result, resourceDir, "build_ModuleName.xml");
		
		checkCode(result, resourceDir, "src/ModuleName.java");
		checkCode(result, resourceDir, "src/ModuleNameComp.java");
		checkCode(result, resourceDir, "src/ModuleNameImpl.java");
		checkCode(result, resourceDir, "src/ModuleNameProtocol.java");
		checkCode(result, resourceDir, "src/State01.java");
		checkCode(result, resourceDir, "src/State02.java");
		checkCode(result, resourceDir, "src/FinalState.java");
		checkCode(result, resourceDir, "src/Top.java");
		
//		checkCode(result, resourceDir, "test/src/CMakeLists.txt");
		checkCode(result, resourceDir, "test/src/ModuleNameTest.java");
		checkCode(result, resourceDir, "test/src/ModuleNameTestComp.java");
		checkCode(result, resourceDir, "test/src/ModuleNameTestImpl.java");
	}

	public void testBasic() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/basic/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/basic/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/basic/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
	
	public void testStateName() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/stateName/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/stateName/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/stateName/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		
		checkCode(result, resourceDir, "README.md");
		checkCode(result, resourceDir, "build_ModuleName.xml");
		
		checkCode(result, resourceDir, "src/ModuleName.java");
		checkCode(result, resourceDir, "src/ModuleNameComp.java");
		checkCode(result, resourceDir, "src/ModuleNameImpl.java");
		checkCode(result, resourceDir, "src/ModuleNameProtocol.java");
		checkCode(result, resourceDir, "src/State_01.java");
		checkCode(result, resourceDir, "src/State_02.java");
		checkCode(result, resourceDir, "src/FinalState.java");
		checkCode(result, resourceDir, "src/Top.java");
		
//		checkCode(result, resourceDir, "test/src/CMakeLists.txt");
		checkCode(result, resourceDir, "test/src/ModuleNameTest.java");
		checkCode(result, resourceDir, "test/src/ModuleNameTestComp.java");
		checkCode(result, resourceDir, "test/src/ModuleNameTestImpl.java");
	}
//	public void testPortName() throws Exception {
//		String rtcProfile = rootPath + "resource/FSM/portName/RTC.xml";
//		String fsmProfile = rootPath + "resource/FSM/portName/ModuleNameFSM.scxml";
//		String resourceDir = rootPath + "/resource/FSM/portName/";
//		
//		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
//		checkGeneratedCode(result, resourceDir);
//	}
	
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
	
//	public void testEventType() throws Exception {
//		String rtcProfile = rootPath + "resource/FSM/eventType/RTC.xml";
//		String fsmProfile = rootPath + "resource/FSM/eventType/ModuleNameFSM.scxml";
//		String resourceDir = rootPath + "/resource/FSM/eventType/";
//		
//		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
//		checkGeneratedCode(result, resourceDir);
//	}
//	
//	public void testEventDoc() throws Exception {
//		String rtcProfile = rootPath + "resource/FSM/eventDoc/RTC.xml";
//		String fsmProfile = rootPath + "resource/FSM/eventDoc/ModuleNameFSM.scxml";
//		String resourceDir = rootPath + "/resource/FSM/eventDoc/";
//		
//		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
//		checkGeneratedCode(result, resourceDir);
//	}
//	
	public void testStateEntry() throws Exception {
		String rtcProfile = rootPath + "resource/FSM/stateEntry/RTC.xml";
		String fsmProfile = rootPath + "resource/FSM/stateEntry/ModuleNameFSM.scxml";
		String resourceDir = rootPath + "/resource/FSM/stateEntry/";
		
		List<GeneratedResult> result = generateCode(rtcProfile, fsmProfile);
		checkGeneratedCode(result, resourceDir);
	}
}
