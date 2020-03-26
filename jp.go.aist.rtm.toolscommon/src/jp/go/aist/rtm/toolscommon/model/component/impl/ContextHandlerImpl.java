/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ContextHandlerPropertySource;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ContextHandlerImpl extends EObjectImpl implements ContextHandler {

	/** IDとExecutionContextを対応付けて格納 */
	Map<String, ExecutionContext> contextMap = new HashMap<String, ExecutionContext>();

	protected Map<String, ExecutionContext> getContextMap() {
		if (contextMap == null) {
			contextMap = new HashMap<String, ExecutionContext>();
		}
		return contextMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextHandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.CONTEXT_HANDLER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutionContext setContext(String id, ExecutionContext ec) {
		getContextMap().put(id, ec);
		return ec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutionContext getContext(String id) {
		return getContextMap().get(id);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getId(ExecutionContext ec) {
		for (String id : getContextMap().keySet()) {
			ExecutionContext e = getContextMap().get(id);
			if (e != null && e.equals(ec)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ExecutionContext removeContext(String id) {
		return getContextMap().remove(id);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String removeId(ExecutionContext ec) {
		String removeId = getId(ec);
		if (removeId != null) {
			getContextMap().remove(removeId);
		}
		return removeId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void sync() {
		getContextMap().clear();
		for (int i = 0; i < getOwnerContexts().size(); i++) {
			ExecutionContext ec = getOwnerContexts().get(i);
			String id = getType() + i;
			setContext(id, ec);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public List<ExecutionContext> values() {
		List<ExecutionContext> result = new ArrayList<ExecutionContext>();
		result.addAll(getContextMap().values());
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public List<String> keys() {
		List<String> result = new ArrayList<String>();
		result.addAll(getContextMap().keySet());
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void clear() {
		getContextMap().clear();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getType() {
		int featureId = eContainerFeatureID();
		if (featureId == EOPPOSITE_FEATURE_BASE
				- ComponentPackage.COMPONENT__EXECUTION_CONTEXT_HANDLER) {
			return "owned";
		} else if (featureId == EOPPOSITE_FEATURE_BASE
				- ComponentPackage.COMPONENT__PARTICIPATION_CONTEXT_HANDLER) {
			return "participate";
		}
		return "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ExecutionContext> getOwnerContexts() {
		Component owner = (Component) eContainer();
		int featureId = eContainerFeatureID();
		if (featureId == EOPPOSITE_FEATURE_BASE
				- ComponentPackage.COMPONENT__EXECUTION_CONTEXT_HANDLER) {
			return owner.getExecutionContexts();
		} else if (featureId == EOPPOSITE_FEATURE_BASE
				- ComponentPackage.COMPONENT__PARTICIPATION_CONTEXT_HANDLER) {
			return owner.getParticipationContexts();
		}
		return new BasicEList<ExecutionContext>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ContextHandlerPropertySource(this);
		}
		return result;
	}

} //ContextHandlerImpl
