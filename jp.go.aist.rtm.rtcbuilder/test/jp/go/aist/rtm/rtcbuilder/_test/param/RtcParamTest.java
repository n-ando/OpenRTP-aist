package jp.go.aist.rtm.rtcbuilder._test.param;

import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.TargetEnvParam;

public class RtcParamTest extends ParamTestCase<RtcParam> {

	GeneratorParam genParam;

	RtcParam rtcParam;

	@Override
	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setSchemaVersion("0.1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setSchemaVersion("0.2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setAbstract("概要１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setAbstract("概要２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCreationDate("2009/10/10");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCreationDate("2009/11/11");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setUpdateDate("2009/10/10");
			}

			@Override
			void execute2(RtcParam e) {
				e.setUpdateDate("2009/11/11");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setName("Component1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setName("Component2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCategory("Category1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCategory("Category2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDescription("説明１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDescription("説明２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setVersion("1.0.0");
			}

			@Override
			void execute2(RtcParam e) {
				e.setVersion("1.0.1");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setVender("ベンダ１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setVender("ベンダ２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setComponentType("TYPE1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setComponentType("TYPE2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActivityType("ACTIVITY1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setActivityType("ACTIVITY2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setComponentKind("KIND1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setComponentKind("KIND1,KIND2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setMaxInstance(1);
			}

			@Override
			void execute2(RtcParam e) {
				e.setMaxInstance(2);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setExecutionType("EXEC1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setExecutionType("EXEC2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setExecutionRate(0.5);
			}

			@Override
			void execute2(RtcParam e) {
				e.setExecutionRate(1.0);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setRtcType("RTC1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setRtcType("RTC2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCurrentVersionUpLog("VERSION1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCurrentVersionUpLog("VERSION2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setOutputProject("PROJ1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setOutputProject("PROJ2");
			}
		});
	}

	public void testIsUpdatedLang() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		rtcParam.setLanguage("java,python");
		assertTrue(rtcParam.isUpdated());
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		rtcParam.setLanguageArg("Java,Python");
		assertTrue(rtcParam.isUpdated());
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setArchitecture("FreeBSD");
			}

			@Override
			void execute2(RtcParam e) {
				e.setArchitecture("Linux");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocDescription("コンポーネント概要１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocDescription("コンポーネント概要２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocInOut("コンポーネント入出力１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocInOut("コンポーネント入出力２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocAlgorithm("コンポーネントアルゴリズム１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocAlgorithm("コンポーネントアルゴリズム２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocCreator("作成者１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocCreator("作成者２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocLicense("ライセンス１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocLicense("ライセンス２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocReference("参考文献１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocReference("参考文献２");
			}
		});
	}

	public void testIsUpdatedAction() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActionImplemented(0, "TRUE");
			}

			@Override
			void execute2(RtcParam e) {
				e.setActionImplemented(0, "FALSE");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActionImplemented(1, true);
			}

			@Override
			void execute2(RtcParam e) {
				e.setActionImplemented(1, false);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionOverView(2, "アクション概要１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionOverView(2, "アクション概要２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionPreCondition(2, "アクション事前条件１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionPreCondition(2, "アクション事前条件２");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionPostCondition(2, "アクション事後条件１");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionPostCondition(2, "アクション事後条件２");
			}
		});
	}

	public void testIsUpdatedInDataPort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		DataPortParam dp = new DataPortParam("in1", "TimedLong", "in1Port", 0);
		rtcParam.getInports().add(dp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		dp.setDocUnit("単位１");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getInports().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedOutDataPort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		DataPortParam dp = new DataPortParam("out1", "TimedLong", "out1Port", 1);
		rtcParam.getOutports().add(dp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		dp.setDocDescription("概要１");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getOutports().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedServicePort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ServicePortParam sp = new ServicePortParam("srv1", 0);
		rtcParam.getServicePorts().add(sp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		sp.setDocDescription("概要１");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getServicePorts().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedConfigSet() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ConfigSetParam csp = new ConfigSetParam("name1", "type1", "0");
		rtcParam.getConfigParams().add(csp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		csp.setDocConstraint("0以上の整数");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getConfigParams().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedTargetEnv() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		TargetEnvParam ep = new TargetEnvParam("1.0", "Windows XP", "", "");
		rtcParam.getTargetEnvs().add(ep);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ep.setCpuOther("その他CPU");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getTargetEnvs().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

}
