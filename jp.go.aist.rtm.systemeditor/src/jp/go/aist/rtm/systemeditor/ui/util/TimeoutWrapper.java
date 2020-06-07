package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

/**
 * 時間のかかる処理を別スレッドで行い、指定された時間内に終わらなければ、エラーメッセージを表示するクラス
 */
public class TimeoutWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutWrapper.class);

	private long milliSecond;

	private ExecutorService executor;

	public static TimeoutWrapper of(long millisecond) {
		return new TimeoutWrapper(millisecond);
	}

	public static TimeoutWrapper asDefault() {
		int defaultTimeout = ToolsCommonPreferenceManager.getInstance()
				.getDefaultTimeout(ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
		return new TimeoutWrapper(defaultTimeout);
	}

	private TimeoutWrapper(long milliSecond) {
		this.milliSecond = milliSecond;
		this.executor = Executors.newFixedThreadPool(1);
	}

	public <T> T start(Callable<T> c) {
		Future<T> future = this.executor.submit(c);
		try {
			T ret = future.get(this.milliSecond, TimeUnit.MILLISECONDS);
			return ret;
		} catch (TimeoutException e) {
			// タイムアウト発生
			return null;
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Fail to get future", e);
			return null;
		}
	}

}
