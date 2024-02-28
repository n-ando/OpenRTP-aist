package jp.go.aist.rtm.rtcbuilder._test.param;

import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class ServicePortInterfaceParamTest extends
		ParamTestCase<ServicePortInterfaceParam> {

	ServicePortParam sp;

	ServicePortInterfaceParam sip;

	@Override
	protected void setUp() throws Exception {
		sp = new ServicePortParam();
		sip = new ServicePortInterfaceParam(sp);
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setName("interface1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setName("interface2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setInstanceName("instance1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setInstanceName("instance2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setVarName("if1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setVarName("if2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setIdlFile("idlFile1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setIdlFile("idlFile2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setInterfaceType("TYPE1");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setInterfaceType("TYPE2");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setIndex(1);
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setIndex(0);
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDirection("Required");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDirection("Provided");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocDescription("概要１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocDescription("概要２");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocArgument("引数１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocArgument("引数２");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocReturn("返り値１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocReturn("返り値２");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocException("例外１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocException("例外２");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocPreCondition("事前条件１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocPreCondition("事前条件２");
			}
		});
		//
		assertUpdated(sip, new UpdateChecker() {
			@Override
			void execute1(ServicePortInterfaceParam e) {
				e.setDocPostCondition("事後条件１");
			}

			@Override
			void execute2(ServicePortInterfaceParam e) {
				e.setDocPostCondition("事後条件２");
			}
		});
	}

}
