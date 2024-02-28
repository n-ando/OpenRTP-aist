package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil.SerializerInfo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConnectorUtilTest {

	OutPort out;
	InPort in;

	@Before
	public void setUp() throws Exception {
		out = ComponentFactory.eINSTANCE.createOutPort();
		in = ComponentFactory.eINSTANCE.createInPort();
	}

	@Test
	public void testGetAllowDataTypes10_1() throws Exception {
		// 1.0 名前空間あり
		out.setDataType("RTC::TimedLong,RTC::TimedFloat,RTC::TimedDouble");
		in.setDataType("RTC::TimedLong,RTC::TimedDouble");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("RTC::TimedLong"));
		assertTrue(resultType.contains("RTC::TimedDouble"));
	}

	@Test
	public void testGetAllowDataTypes10_2() throws Exception {
		// 1.0 名前空間不一致
		out.setDataType("TimedLong,TimedFloat,RTC::TimedDouble");
		in.setDataType("RTC::TimedLong,RTC::TimedDouble");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(1, result.size());
		assertTrue(resultType.contains("RTC::TimedDouble"));
	}

	@Test
	public void testGetAllowDataTypes10_3() throws Exception {
		// 1.0 Any含む(source)
		out.setDataType("TimedLong,Any");
		in.setDataType("RTC::TimedFloat,RTC::TimedDouble");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("RTC::TimedFloat"));
		assertTrue(resultType.contains("RTC::TimedDouble"));
	}

	@Test
	public void testGetAllowDataTypes10_4() throws Exception {
		// 1.0 Any含む(target)
		out.setDataType("TimedLong,RTC::TimedDouble");
		in.setDataType("Any,TimedLong");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("TimedLong"));
		assertTrue(resultType.contains("RTC::TimedDouble"));
	}

	@Test
	public void testGetAllowDataTypes11_1() throws Exception {
		// 1.1 IFR一致
		out
				.setDataType("IDL:RTC/TimedLong:1.0,IDL:RTC/TimedFloat:1.0,IDL:RTC/TimedDouble:1.0");
		in.setDataType("IDL:RTC/TimedLong:1.0,IDL:RTC/TimedDouble:1.0");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("IDL:RTC/TimedLong:1.0"));
		assertTrue(resultType.contains("IDL:RTC/TimedDouble:1.0"));
	}

	@Test
	public void testGetAllowDataTypes11_2() throws Exception {
		// 1.1 IFR不一致(バージョン)
		out
				.setDataType("IDL:RTC/TimedLong:1.1,IDL:RTC/TimedFloat:1.0,IDL:RTC/TimedDouble:1.0");
		in.setDataType("IDL:RTC/TimedLong:1.0,IDL:RTC/TimedDouble:1.0");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(1, result.size());
		assertTrue(resultType.contains("IDL:RTC/TimedDouble:1.0"));
	}

	@Test
	public void testGetAllowDataTypes11_3() throws Exception {
		// 1.1/1.0 混在(後方一致でIFR形式を返す)
		out.setDataType("RTC::TimedLong,RTC::TimedFloat,TimedDouble");
		in.setDataType("IDL:RTC/TimedLong:1.0,IDL:RTC/TimedDouble:1.0");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("IDL:RTC/TimedLong:1.0"));
		assertTrue(resultType.contains("IDL:RTC/TimedDouble:1.0"));
	}

	@Test
	public void testGetAllowDataTypes11_4() throws Exception {
		// 1.1/1.0 混在(後方一致でIFR形式を返す)
		out
				.setDataType("IDL:RTC/TimedLong:1.0,IDL:RTC/TimedFloat:1.0,IDL:RTC/TimedDouble:1.0");
		in.setDataType("RTC::TimedLong,TimedDouble");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(2, result.size());
		assertTrue(resultType.contains("IDL:RTC/TimedLong:1.0"));
		assertTrue(resultType.contains("IDL:RTC/TimedDouble:1.0"));
	}

	@Test
	public void testGetAllowDataTypes11_5() throws Exception {
		// 1.1/1.0 名前空間が不一致
		out.setDataType("RTC::TimedLong,AAA::TimedFloat,TimedDouble");
		in
				.setDataType("IDL:TimedLong:1.0,IDL:RTC/TimedFloat:1.0,IDL:AAA/BBB/TimedDouble:1.0");
		List<SerializerInfo> result = ConnectorUtil.getAllowDataTypes(out, in);
		List<String> resultType = new ArrayList<String>();
		for(SerializerInfo each : result) {
			resultType.add(each.dataType);
		}

		assertEquals(1, result.size());
		assertTrue(resultType.contains("IDL:AAA/BBB/TimedDouble:1.0"));
	}

	@Test
	public void testGetAllowDataflowTypes1() throws Exception {
		out.setDataflowType("push,Pull");
		in.setDataflowType("pull");
		List<String> result = ConnectorUtil.getAllowDataflowTypes(out, in);

		assertEquals(1, result.size());
		assertTrue(result.contains("Pull"));
	}

	@Test
	public void testGetAllowDataflowTypes2() throws Exception {
		out.setDataflowType("Any");
		in.setDataflowType("pull,push");
		List<String> result = ConnectorUtil.getAllowDataflowTypes(out, in);

		assertEquals(2, result.size());
		assertTrue(result.contains("pull"));
		assertTrue(result.contains("push"));
	}

	@Test
	public void testGetAllowSubscriptionTypes1() throws Exception {
		out.setSubscriptionType("flush,periodic");
		in.setSubscriptionType("periodic");
		List<String> result = ConnectorUtil.getAllowSubscriptionTypes(out, in);

		assertEquals(1, result.size());
		assertTrue(result.contains("periodic"));
	}

	@Test
	public void testGetAllowSubscriptionTypes2() throws Exception {
		out.setSubscriptionType("Any");
		in.setSubscriptionType("flush,new");
		List<String> result = ConnectorUtil.getAllowSubscriptionTypes(out, in);

		assertEquals(2, result.size());
		assertTrue(result.contains("flush"));
		assertTrue(result.contains("new"));
	}

}
