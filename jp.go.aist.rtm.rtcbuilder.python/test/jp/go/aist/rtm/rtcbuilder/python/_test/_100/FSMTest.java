package jp.go.aist.rtm.rtcbuilder.python._test._100;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.fsm.ScXMLHandler;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.python._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonCMakeGenerateManager;
import jp.go.aist.rtm.rtcbuilder.python.manager.PythonGenerateManager;

public class FSMTest extends TestBase {

	protected void setUp() throws Exception {
	}

	private List<GeneratedResult> generateCode(String rtcProfile, String fsmProfile) throws Exception {
		ProfileHandler handler = new ProfileHandler(true);
		handler.addManager(new PythonGenerateManager());
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
		generator.addGenerateManager(new PythonGenerateManager());
		generator.addGenerateManager(new PythonCMakeGenerateManager());
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);
		return result;
	}

	private void checkGeneratedCode(List<GeneratedResult> result, String resourceDir) {
		checkCode(result, resourceDir, "README.md");
		checkCode(result, resourceDir, "ModuleName.py");
		checkCode(result, resourceDir, "ModuleNameFSM.py");
		
		checkCode(result, resourceDir, "test/CMakeLists.txt");
		checkCode(result, resourceDir, "test/ModuleNameTest.py");
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
