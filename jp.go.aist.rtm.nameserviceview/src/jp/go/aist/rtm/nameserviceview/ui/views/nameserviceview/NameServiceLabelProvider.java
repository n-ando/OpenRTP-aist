package jp.go.aist.rtm.nameserviceview.ui.views.nameserviceview;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * NameServiceViewのLabelProviderクラス
 */
public class NameServiceLabelProvider extends LabelProvider {

	/**
	 * {@inheritDoc}
	 */
	public String getText(Object obj) {
		IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) AdapterUtil
				.getAdapter(obj, IWorkbenchAdapter.class);
		String result = null;
		if (workbenchAdapter != null) {
			result = workbenchAdapter.getLabel(obj);
		}
		if (result == null) {
			result = obj.toString();
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 * 現状、エントリごとにアイコンを変化させる処理は、NamingObjectNode（CORBA専用）だけに対応している
	 */
	public Image getImage(Object obj) {
		Object targetObject = getTargetObject(obj);
		IWorkbenchAdapter workbenchAdapter = ((IWorkbenchAdapter) AdapterUtil
				.getAdapter(targetObject, IWorkbenchAdapter.class));

		if (workbenchAdapter != null) {
			ImageDescriptor descriptor = workbenchAdapter
					.getImageDescriptor(targetObject);
			Image result = NameServiceViewPlugin.getCachedImage(descriptor);
			if (result != null) return result;
		}

		return NameServiceViewPlugin.getCachedImage("icons/Question.png");
	}

	private Object getTargetObject(Object obj) {
		if (obj instanceof NamingObjectNode) {
			NamingObjectNode namingObjectNode = ((NamingObjectNode) obj);

			if (namingObjectNode.isZombie() == false) {
				return namingObjectNode.getEntry();
			}
		}
		return obj;
	}
}
