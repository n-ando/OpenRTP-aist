package jp.go.aist.rtm.systemeditor;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.profiles.util.LoggerUtil;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class RTSystemEditorPlugin extends AbstractUIPlugin {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RTSystemEditorPlugin.class);

	// The plug-in ID
	private static final String PLUGIN_ID = "jp.go.aist.rtm.systemeditor";

	// The shared instance
	private static RTSystemEditorPlugin plugin;

	/**
	 * The constructor
	 */
	public RTSystemEditorPlugin() {
		LoggerUtil.setup();
		LOGGER.trace("RTSystemEditorPlugin: START");

		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
		IPath targetFile = workspaceHandle.getRawLocation().append(".metadata/.plugins/org.eclipse.e4.workbench/workbench.xmi");
		if( targetFile.toFile().exists() == false ) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			MessageDialog.openError(window.getShell(), "RTSystemEditor", Messages.getString("SystemEditor.1"));
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RTSystemEditorPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * ImageRegistryにキャッシュしたイメージを返す
	 * @param path プラグインの相対パス
	 * @return　イメージ
	 */
	public static Image getCachedImage(String path) {
		return getCachedImage(getImageDescriptor(path));
	}

	/**
	 * ImageRegistryにキャッシュしたイメージを返す
	 * @param descriptor イメージディスクリプタ
	 * @return　イメージ
	 */
	public static Image getCachedImage(ImageDescriptor descriptor) {
		if (descriptor == null) return null;
		Image result = getDefault().getImageRegistry().get(descriptor.toString());
		if (result == null) {
			result = descriptor.createImage();
			getDefault().getImageRegistry().put(
					descriptor.toString(), result);
		}
		return result;
	}

}
