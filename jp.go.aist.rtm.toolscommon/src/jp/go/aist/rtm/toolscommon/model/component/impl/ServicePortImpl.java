/**
 * <copyright>
 * </copyright>
 *
 * $Id: ServicePortImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PortPropertySource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Service Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ServicePortImpl extends PortImpl implements ServicePort {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ServicePortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.SERVICE_PORT;
	}

	@Override
	public boolean validateSourceConnector(Port source) {
		return source instanceof ServicePort;
	}

	@Override
	public boolean validateTargetConnector(Port target) {
		return target instanceof ServicePort;
	}

	@Override
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new PortPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}

		return result;
	}


} // ServicePortImpl
