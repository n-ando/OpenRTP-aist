package jp.go.aist.rtm.rtcbuilder.python._test;

import jp.go.aist.rtm.rtcbuilder.python._test._100.AISTTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.BaseTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.BuildTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.ConfigSetTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.FSMTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.IDLPathTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.PyDocTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.PyIDLInheritTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.PyIDLType;
import jp.go.aist.rtm.rtcbuilder.python._test._100.PyImplTest;
import jp.go.aist.rtm.rtcbuilder.python._test._100.PyModuleTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsPy {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder.python._test._100");
		//$JUnit-BEGIN$
		suite.addTestSuite(BaseTest.class);
		suite.addTestSuite(AISTTest.class);
		suite.addTestSuite(ConfigSetTest.class);
		suite.addTestSuite(PyDocTest.class);
		suite.addTestSuite(PyIDLInheritTest.class);
		suite.addTestSuite(PyImplTest.class);
		suite.addTestSuite(PyModuleTest.class);
		suite.addTestSuite(PyIDLType.class);
		suite.addTestSuite(BuildTest.class);
		suite.addTestSuite(IDLPathTest.class);
		
		suite.addTestSuite(FSMTest.class);
		//$JUnit-END$
		return suite;
	}

}
