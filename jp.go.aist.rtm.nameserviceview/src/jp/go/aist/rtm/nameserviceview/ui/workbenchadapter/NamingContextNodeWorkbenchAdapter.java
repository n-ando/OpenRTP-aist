package jp.go.aist.rtm.nameserviceview.ui.workbenchadapter;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.ModelElementWorkbenchAdapter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;

/**
 * NamingContextNodeのWorkbenchAdapter
 */
public class NamingContextNodeWorkbenchAdapter extends
		ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return NameServiceViewPlugin.getImageDescriptor("icons/Folder.png");
	}

	@Override
	public String getLabel(Object o) {
		NameServiceReference nameServiceReference = ((CorbaNode) o)
				.getNameServiceReference();
		Binding binding = nameServiceReference.getBinding();
		NameComponent[] nameComponents = binding.binding_name;

		NameComponent lastNameComponent = nameComponents[nameComponents.length - 1];
		return lastNameComponent.id + "|" + lastNameComponent.kind;
	}

}
