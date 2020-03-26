/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.util.CorbaObjectStore;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Context Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CorbaContextHandlerImpl extends ContextHandlerImpl implements CorbaContextHandler {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CorbaContextHandlerImpl() {
		super();
		this.contextMap = null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CORBA_CONTEXT_HANDLER;
	}

	@Override
	public void sync() {
		contextMap = null;
		getContextMap();
	}

	@Override
	protected Map<String, ExecutionContext> getContextMap() {
		if (contextMap == null) {
			CorbaComponent cc = (CorbaComponent) eContainer();
			contextMap = new HashMap<String, ExecutionContext>();
			RTC.RTObject ro = cc.getCorbaObjectInterface();
			for (ExecutionContext ec : getOwnerContexts()) {
				CorbaExecutionContext cec = (CorbaExecutionContext) ec;
				RTC.ExecutionContext eo = cec.getCorbaObjectInterface();
				String id = CorbaObjectStore.eINSTANCE.findContextId(ro, eo);
				if (id != null) {
					contextMap.put(id, cec);
				}
			}
		}
		return contextMap;
	}

} //CorbaContextHandlerImpl
