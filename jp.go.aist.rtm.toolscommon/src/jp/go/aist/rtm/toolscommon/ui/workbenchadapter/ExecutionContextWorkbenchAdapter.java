package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * ExecutionContextのWorkbenchAdapter
 */
public class ExecutionContextWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/ExecutionContext.png");
	}

	@Override
	public String getLabel(Object o) {
		return Messages.getString("ExecutionContextWorkbenchAdapter.label");
	}

	@Override
	public Object[] getChildren(Object o) {
		List<Object> result = new ArrayList<Object>();
		ExecutionContext c = (ExecutionContext) o;
		if (!c.getPropertyKeys().isEmpty()) {
			result.add(c.getPropertyMap());
		}
		return result.toArray();
	}

}
