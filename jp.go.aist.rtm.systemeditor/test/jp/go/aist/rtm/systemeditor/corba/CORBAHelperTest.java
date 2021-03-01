package jp.go.aist.rtm.systemeditor.corba;

import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_EXPORTED_PORTS;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_IMPLEMENTATION_ID;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_INSTANCE_NAME;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_LANGUAGE;
import static jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter.KEY_MANAGER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.corba.CORBAHelper.CreateComponentParameter;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaComponentImpl;

import org.junit.Test;

public class CORBAHelperTest {

	/**
	 * コンポーネント生成のパラメータ作成
	 */
	@Test
	public void testFactoryBuildCreateComponentParam() {
		{
			CorbaComponent comp = new CorbaComponentMock();
			String act = CORBAHelper.factory().buildCreateComponentParam(comp, null);
			assertEquals(null, act);
			//
			comp.setProperty(KEY_IMPLEMENTATION_ID, "Type1");
			act = CORBAHelper.factory().buildCreateComponentParam(comp, null);
			assertEquals(null, act);
			//
			comp.setProperty(KEY_INSTANCE_NAME, "type1");
			act = CORBAHelper.factory().buildCreateComponentParam(comp, null);
			assertEquals("Type1?instance_name=type1", act);
		}
		{
			CorbaComponent comp = new CorbaComponentMock();
			comp.setProperty(KEY_IMPLEMENTATION_ID, "Type1");
			comp.setProperty(KEY_INSTANCE_NAME, "type1");
			comp.setProperty(KEY_MANAGER_NAME, "pg1");
			String act = CORBAHelper.factory().buildCreateComponentParam(comp, null);
			assertEquals("Type1?instance_name=type1&manager_name=pg1", act);
		}
	}

	/**
	 * 複合コンポーネント生成のパラメータ作成１
	 */
	@Test
	public void testFactoryBuildCreateCompositeComponentParam1() {
		{
			CorbaComponent comp = new CorbaComponentMock();
			String act = CORBAHelper.factory()
					.buildCreateCompositeComponentParam(comp, null);
			assertEquals(null, act);
			//
			comp = new CorbaComponentMock();
			comp.setCategoryL("composite.ECShared");
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					comp, null);
			assertEquals(null, act);
			//
			comp.setProperty(KEY_INSTANCE_NAME, "type1");
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					comp, null);
			assertEquals("ECSharedComposite?instance_name=type1", act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					comp, "");
			assertEquals(
					"ECSharedComposite?instance_name=type1&exported_ports=",
					act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					comp, "type1.out");
			assertEquals(
					"ECSharedComposite?instance_name=type1&exported_ports=type1.out",
					act);
		}
		{
			CorbaComponent comp = new CorbaComponentMock();
			comp.setCategoryL("composite.ECShared");
			comp.setProperty(KEY_INSTANCE_NAME, "type1");
			comp.setProperty(KEY_MANAGER_NAME, "pg1");
			String act = CORBAHelper.factory()
					.buildCreateCompositeComponentParam(comp, "type1.out");
			assertEquals(
					"ECSharedComposite?instance_name=type1&exported_ports=type1.out&manager_name=pg1",
					act);
		}
	}

	/**
	 * 複合コンポーネント生成のパラメータ作成２
	 */
	@Test
	public void testFactoryBuildCreateCompositeComponentParam2() {
		{
			String act = CORBAHelper.factory()
					.buildCreateCompositeComponentParam(null, null, null);
			assertEquals(null, act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					"ECShared", null, null);
			assertEquals(null, act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					"ECShared", "type1", null);
			assertEquals("ECSharedComposite?instance_name=type1", act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					"ECShared", "type1", "");
			assertEquals(
					"ECSharedComposite?instance_name=type1&exported_ports=",
					act);
			//
			act = CORBAHelper.factory().buildCreateCompositeComponentParam(
					"ECShared", "type1", "type1.out");
			assertEquals(
					"ECSharedComposite?instance_name=type1&exported_ports=type1.out",
					act);
		}
	}

	/**
	 * コンポーネント生成パラメータ : 基本パラメータ
	 */
	@Test
	public void testCreateComponentParameter() {
		CreateComponentParameter param = new CreateComponentParameter("Type2");
		assertEquals("Type2", param.getParam(KEY_IMPLEMENTATION_ID));
		assertEquals("Type2", param.buildCommand());
		param.setParam(KEY_IMPLEMENTATION_ID, "Type1");
		assertEquals("Type1", param.getParam(KEY_IMPLEMENTATION_ID));
		assertEquals("Type1", param.buildCommand());

		param.setInstanceName("type2");
		assertEquals("type2", param.getInstanceName());
		assertEquals("type2", param.getParam(KEY_INSTANCE_NAME));
		assertEquals("Type1?instance_name=type2", param.buildCommand());
		param.setParam(KEY_INSTANCE_NAME, "type1");
		assertEquals("type1", param.getInstanceName());
		assertEquals("type1", param.getParam(KEY_INSTANCE_NAME));
		assertEquals("Type1?instance_name=type1", param.buildCommand());

		param.setManagerName("pg2");
		assertEquals("pg2", param.getManagerName());
		assertEquals("pg2", param.getParam(KEY_MANAGER_NAME));
		assertEquals("Type1?instance_name=type1&manager_name=pg2",
				param.buildCommand());
		param.setParam(KEY_MANAGER_NAME, "pg1");
		assertEquals("pg1", param.getManagerName());
		assertEquals("pg1", param.getParam(KEY_MANAGER_NAME));
		assertEquals("Type1?instance_name=type1&manager_name=pg1",
				param.buildCommand());

		param.setLanguage("Java");
		assertEquals("Java", param.getLanguage());
		assertEquals("Java", param.getParam(KEY_LANGUAGE));
		assertEquals(
				"Type1?instance_name=type1&manager_name=pg1&language=Java",
				param.buildCommand());
		param.setParam(KEY_LANGUAGE, "C++");
		assertEquals("C++", param.getLanguage());
		assertEquals("C++", param.getParam(KEY_LANGUAGE));
		assertEquals("Type1?instance_name=type1&manager_name=pg1&language=C++",
				param.buildCommand());

		param.setExportedPorts("type1.out");
		assertEquals("type1.out", param.getExportedPorts());
		assertEquals("type1.out", param.getParam(KEY_EXPORTED_PORTS));
		assertEquals(
				"Type1?instance_name=type1&exported_ports=type1.out&manager_name=pg1&language=C++",
				param.buildCommand());
		param.setParam(KEY_EXPORTED_PORTS, "type1.out,type1.in");
		assertEquals("type1.out,type1.in", param.getExportedPorts());
		assertEquals("type1.out,type1.in", param.getParam(KEY_EXPORTED_PORTS));
		assertEquals(
				"Type1?instance_name=type1&exported_ports=type1.out,type1.in&manager_name=pg1&language=C++",
				param.buildCommand());
		assertEquals(
				"CreateComponentParameter<implementation_id=Type1, instance_name=type1, exported_ports=type1.out,type1.in, manager_name=pg1, language=C++, {}>",
				param.toString());
	}

	/**
	 * コンポーネント生成パラメータ : 任意パラメータ
	 */
	@Test
	public void testCreateComponentParameterOtherParams() {
		CreateComponentParameter param = new CreateComponentParameter("Type1");
		param.setInstanceName("type1");
		assertEquals("Type1?instance_name=type1", param.buildCommand());

		param.setParam("name1", "value2");
		assertEquals("value2", param.getParam("name1"));
		assertEquals("Type1?instance_name=type1&name1=value2",
				param.buildCommand());
		param.setParam("name1", "value1");
		assertEquals("value1", param.getParam("name1"));
		assertEquals("Type1?instance_name=type1&name1=value1",
				param.buildCommand());

		param.setParam("name2", "value2");
		assertEquals("value2", param.getParam("name2"));
		assertEquals("Type1?instance_name=type1&name1=value1&name2=value2",
				param.buildCommand());

		param.setParams("name2=valueA&nameB=valueB");
		assertEquals("valueA", param.getParam("name2"));
		assertEquals("valueB", param.getParam("nameB"));
		assertEquals("name1=value1&name2=valueA&nameB=valueB",
				param.getParams());
		assertEquals(
				"Type1?instance_name=type1&name1=value1&name2=valueA&nameB=valueB",
				param.buildCommand());
		assertFalse(param.getParamNames().contains("instance_name"));
		assertTrue(param.getParamNames().contains("name1"));
		assertTrue(param.getParamNames().contains("name2"));
		assertTrue(param.getParamNames().contains("nameB"));
		assertEquals(
				"CreateComponentParameter<implementation_id=Type1, instance_name=type1, exported_ports=null, manager_name=null, language=null, {name1=value1, name2=valueA, nameB=valueB}>",
				param.toString());
	}

	//

	static class CorbaComponentMock extends CorbaComponentImpl {

		private Map<String, String> props = new HashMap<>();

		@Override
		public void setProperty(String key, String value) {
			this.props.put(key, value);
		}

		@Override
		public String getProperty(String key) {
			return this.props.get(key);
		}

	}

}
