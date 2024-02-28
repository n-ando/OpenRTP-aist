package jp.go.aist.rtm.rtcbuilder._test.parse;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataTypeParam;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

public class CORBAParseTest extends TestBase {

	public void testDuplicate() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> dataTypes = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\DupliDataTypes1.idl"));
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\DupliDataTypes2.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
			
		try {
			StringBuilder builder = new StringBuilder();
			IDLParamConverter.extractTypeDef(sourceContents, dataTypes, builder);
		} catch (Exception e) {
			fail();
		}
	}
	
	public void testMulti() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\MyData.idl"));
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\Basic5DataTypes.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
			
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);
		
		assertEquals(7, results.size());
		assertTrue(results.contains("RTC::MyData"));
		assertTrue(results.contains("RTC::TimedMyData"));
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}
	
	public void testMyData() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\MyData.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
			
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(2, results.size());
		assertTrue(results.contains("RTC::MyData"));
		assertTrue(results.contains("RTC::TimedMyData"));
	}
	
	public void testMyServiceTypeDef() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\MyServiceTypeDef.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(2, results.size());
		assertTrue(results.contains("Time"));
		assertTrue(results.contains("TimedState"));
	}
	
	public void testNoModule5Basics() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\NoModule5DataTypes.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(5, results.size());
		assertTrue(results.contains("Time"));
		assertTrue(results.contains("TimedState"));
		assertTrue(results.contains("TimedString"));
		assertTrue(results.contains("TimedShortSeq"));
		assertTrue(results.contains("TimedStringSeq"));
	}

	public void testMyService() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\MyService.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);
		assertEquals(0, results.size());
	}
	
	public void test5Basics() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\Basic5DataTypes.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(5, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}

	public void testBasicTypesOrg() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\BasicDataTypeOrg.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(22, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedShort"));
		assertTrue(results.contains("RTC::TimedLong"));
		assertTrue(results.contains("RTC::TimedUShort"));
		assertTrue(results.contains("RTC::TimedULong"));
		assertTrue(results.contains("RTC::TimedFloat"));
		assertTrue(results.contains("RTC::TimedDouble"));
		assertTrue(results.contains("RTC::TimedChar"));
		assertTrue(results.contains("RTC::TimedBoolean"));
		assertTrue(results.contains("RTC::TimedOctet"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedLongSeq"));
		assertTrue(results.contains("RTC::TimedUShortSeq"));
		assertTrue(results.contains("RTC::TimedULongSeq"));
		assertTrue(results.contains("RTC::TimedFloatSeq"));
		assertTrue(results.contains("RTC::TimedDoubleSeq"));
		assertTrue(results.contains("RTC::TimedCharSeq"));
		assertTrue(results.contains("RTC::TimedBooleanSeq"));
		assertTrue(results.contains("RTC::TimedOctetSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}

	public void testBasicTypes() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\BasicDataType.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(22, results.size());
		assertTrue(results.contains("RTC::Time"));
		assertTrue(results.contains("RTC::TimedState"));
		assertTrue(results.contains("RTC::TimedShort"));
		assertTrue(results.contains("RTC::TimedLong"));
		assertTrue(results.contains("RTC::TimedUShort"));
		assertTrue(results.contains("RTC::TimedULong"));
		assertTrue(results.contains("RTC::TimedFloat"));
		assertTrue(results.contains("RTC::TimedDouble"));
		assertTrue(results.contains("RTC::TimedChar"));
		assertTrue(results.contains("RTC::TimedBoolean"));
		assertTrue(results.contains("RTC::TimedOctet"));
		assertTrue(results.contains("RTC::TimedString"));
		assertTrue(results.contains("RTC::TimedShortSeq"));
		assertTrue(results.contains("RTC::TimedLongSeq"));
		assertTrue(results.contains("RTC::TimedUShortSeq"));
		assertTrue(results.contains("RTC::TimedULongSeq"));
		assertTrue(results.contains("RTC::TimedFloatSeq"));
		assertTrue(results.contains("RTC::TimedDoubleSeq"));
		assertTrue(results.contains("RTC::TimedCharSeq"));
		assertTrue(results.contains("RTC::TimedBooleanSeq"));
		assertTrue(results.contains("RTC::TimedOctetSeq"));
		assertTrue(results.contains("RTC::TimedStringSeq"));
	}
	
	public void testBasic() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\Basic.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		StringBuilder builder = new StringBuilder();
		IDLParamConverter.extractTypeDef(sourceContents, results, builder);

		assertEquals(1, results.size());
		assertTrue(results.contains("RTC::Time"));
	}
	
	public void testError() throws Exception{
		List<DataTypeParam> sourceContents = new ArrayList<DataTypeParam>();
		List<String> results = new ArrayList<String>();
		sourceContents.add(new DataTypeParam(rootPath + "\\resource\\IDL\\Error.idl"));
		
		for(int intidx=0;intidx<sourceContents.size();intidx++) {
			String idlContent = FileUtil.readFile(sourceContents.get(intidx).getFullPath());
			sourceContents.get(intidx).setContent(idlContent);
		}
		try {
			StringBuilder builder = new StringBuilder();
			IDLParamConverter.extractTypeDef(sourceContents, results, builder);
		} catch (Exception ex) {
			System.out.println("Error");
			fail();
			
		}
	}
	
}
