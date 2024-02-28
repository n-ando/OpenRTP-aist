/**
 * <copyright>
 * </copyright>
 *
 * $Id: InPortImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.ui.propertysource.PortPropertySource;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>In Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class InPortImpl extends PortImpl implements InPort {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected InPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.IN_PORT;
	}

	public boolean validateSourceConnector(Port source) {
		return source instanceof OutPort; // その他の判断はOutPortに任せる
	}

	public boolean validateTargetConnector(Port target) {
		return false;
	}

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

} // InPortImpl
