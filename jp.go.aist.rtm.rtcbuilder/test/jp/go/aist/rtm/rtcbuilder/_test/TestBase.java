package jp.go.aist.rtm.rtcbuilder._test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;
import junit.framework.TestCase;

public class TestBase extends TestCase {
//	protected String rootPath = "C:\\Tech-Arts\\Eclipse\\jp.go.aist.rtm.rtcbuilder\\";
	protected String rootPath;
	protected String expPath;
	protected String expContent;
	protected int index;
	protected final int default_file_num = 35;

	public TestBase () {
		File fileCurrent = new File(".");
		rootPath = fileCurrent.getAbsolutePath();
		rootPath = rootPath.substring(0,rootPath.length()-1);
	}
	protected String readFile(String fileName) {
		StringBuffer stbRet = new StringBuffer();
		try( FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr) ) {
			String str = new String();
			while( (str = br.readLine()) != null ){
				stbRet.append(str + "\r\n");
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return stbRet.toString();
	}
	protected int getFileIndex(String targetName, List<GeneratedResult> targetList) {
		List<Integer> indexList = new ArrayList<Integer>();
		for( int intIdx=0; intIdx<targetList.size(); intIdx++ ) {
			if( targetList.get(intIdx).getName().contains(targetName) ) {
				indexList.add(intIdx);
			}
		}
		if(indexList.size()==0) return -1;
		else if(indexList.size()==1) return indexList.get(0);
		else {
			for(Integer index : indexList) {
				if( targetList.get(index).getName().startsWith(targetName) ) {
					return index;
				}
			}
		}
		return -1;
	}

	protected String replaceRootPath(String content) {
		String result = content.replace("__ROOT_PATH__", rootPath);
		String origPathes[] = {
				"C:\\Tech-Arts\\EclipseRTM\\jp.go.aist.rtm.rtcbuilder\\",
				"C:\\Tech-Arts\\EclipseRTM34\\jp.go.aist.rtm.rtcbuilder\\",
				"C:\\GlobalAssist\\EclipseAISTRep\\jp.go.aist.rtm.rtcbuilder\\" };
		for (String path : origPathes) {
			result = result.replace(path, rootPath);
		}
		return result;
	}

	protected void checkCode(List<GeneratedResult> result, String resourceDir,
			String fileName) {
		index = getFileIndex(fileName, result);
		expPath = resourceDir + fileName;
		expContent = readFile(expPath);
		expContent = replaceRootPath(expContent);
		expContent = expContent.replace("\uFEFF", "");
//		assertEquals(replaceBlank(expContent) , replaceBlank(result.get(index).getCode()));
		assertEquals(StringUtil.removeLastNewLine(expContent) , StringUtil.removeLastNewLine(result.get(index).getCode()));
	}

	protected void nonexist(List<GeneratedResult> result, String resourceDir,
			String fileName) {
		int i = getFileIndex(fileName, result);
		if (i != -1) {
			fail();
		}
		assertTrue(true);
	}
	
	private String replaceBlank(String source) {
		String result;
		result = source.replaceAll("(\n|\r|\n\r|\r\n){2,}", "\n");
		result = result.replaceAll("[ \t\\x0B\f]+(\n|\r|\n\r|\r\n)", "");
		return result;
	}

}

