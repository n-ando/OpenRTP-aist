package jp.go.aist.rtm.toolscommon.profiles._test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for All Languages");
		//$JUnit-BEGIN$
		suite.addTestSuite(XMLTest.class);
		suite.addTestSuite(YAMLTest.class);
		//
		suite.addTestSuite(ConstraintParseTest.class);
		suite.addTestSuite(ConstraintParseTest2.class);
		suite.addTestSuite(ConstraintRestoreTest.class);
		suite.addTestSuite(ConstraintParseTestYaml.class);
		suite.addTestSuite(ConstraintRestoreTestYaml.class);
		//$JUnit-END$
		return suite;
	}

}
