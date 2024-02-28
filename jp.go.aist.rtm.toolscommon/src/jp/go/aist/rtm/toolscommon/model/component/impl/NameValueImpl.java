/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameValueImpl.java,v 1.1 2008/01/29 05:52:23 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.core.impl.WrapperObjectImpl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Name Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl#getName <em>Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.NameValueImpl#getTypeName <em>Type Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NameValueImpl extends WrapperObjectImpl implements NameValue {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected String typeName = TYPE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NameValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.NAME_VALUE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.NAME_VALUE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setValue(String newValue) {
		setTypeName(null);
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.NAME_VALUE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(String newTypeName) {
		String oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.NAME_VALUE__TYPE_NAME, oldTypeName, typeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.NAME_VALUE__NAME:
				return getName();
			case ComponentPackage.NAME_VALUE__VALUE:
				return getValue();
			case ComponentPackage.NAME_VALUE__TYPE_NAME:
				return getTypeName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.NAME_VALUE__NAME:
				setName((String)newValue);
				return;
			case ComponentPackage.NAME_VALUE__VALUE:
				setValue((String)newValue);
				return;
			case ComponentPackage.NAME_VALUE__TYPE_NAME:
				setTypeName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.NAME_VALUE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ComponentPackage.NAME_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ComponentPackage.NAME_VALUE__TYPE_NAME:
				setTypeName(TYPE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.NAME_VALUE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ComponentPackage.NAME_VALUE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ComponentPackage.NAME_VALUE__TYPE_NAME:
				return TYPE_NAME_EDEFAULT == null ? typeName != null : !TYPE_NAME_EDEFAULT.equals(typeName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", value: ");
		result.append(value);
		result.append(", typeName: ");
		result.append(typeName);
		result.append(')');
		return result.toString();
	}

	/**
	 * CSVからList<String>を作成する
	 * <p>
	 * csvがnullの場合には空リストを返す
	 * 
	 * @param csv
	 *            csv
	 * @return
	 */
	public static List<String> csv2List(String csv) {
		List<String> result = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(csv == null ? "" : csv,
				",");
		while (tokenizer.hasMoreTokens()) {
			result.add(tokenizer.nextToken().trim());
		}

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NameValue == false) {
			return false;
		}

		NameValue n = (NameValue) obj;

		return new EqualsBuilder().append(getName(), n.getName()).isEquals()
				&& getValue().equals(n.getValue()); 

	}

	public String getValueAsString() {
		if (getValue() == null) return getTypeName();
		return getValue();
	}

	public List<String> getValueAsStringList() {
		return csv2List(getValueAsString());
	}

	/**
	 * NameValueのリストからnameを基に検索を行う
	 * 
	 * @param nameValues 
	 * @param name　検索キー
	 * @return　検索結果
	 */
	public static NameValue findByName(List<NameValue> nameValues, String name) {
		for (NameValue nameValue : nameValues) {
			if (name.equals(nameValue.getName())) {
				return nameValue;
			}
		}

		return null;
	}

} // NameValueImpl
