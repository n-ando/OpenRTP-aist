package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import org.eclipse.emf.common.util.EMap;

/**
 * ポート間接続を表現するクラス
 * 
 * @model
 */
public interface PortConnector extends WrapperObject {

	/**
	 * 接続情報を返す
	 * @model containment="true"
	 */
	public ConnectorProfile getConnectorProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getConnectorProfile <em>Connector Profile</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Profile</em>' containment reference.
	 * @see #getConnectorProfile()
	 * @generated
	 */
	void setConnectorProfile(ConnectorProfile value);

	/**
	 * Returns the value of the '<em><b>Routing Constraint</b></em>' map.
	 * The key is of type {@link java.lang.Integer},
	 * and the value is of type {@link jp.go.aist.rtm.toolscommon.model.core.Point},
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタのベンドポイントの集合をマップで返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routing Constraint</em>' map.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_RoutingConstraint()
	 * @model mapType="jp.go.aist.rtm.toolscommon.model.component.EIntegerObjectToPointMapEntry&lt;org.eclipse.emf.ecore.EIntegerObject, jp.go.aist.rtm.toolscommon.model.core.Point&gt;"
	 * @generated
	 */
	EMap<Integer, Point> getRoutingConstraint();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続元であるポートを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Port)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_Source()
	 * @model transient="true"
	 * @generated
	 */
	Port getSource();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Port value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続先であるポートを返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Port)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_Target()
	 * @model
	 * @generated
	 */
	Port getTarget();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Port value);

	/**
	 * <!-- begin-user-doc -->
	 * コネクタを作成するメッセージをリモートに対して送信する
	 * オフラインの場合は、対象となるポートにコネクタプロファイルを追加する
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean createConnectorR();

	/**
	 * <!-- begin-user-doc -->
	 * コネクタを削除するメッセージをリモートに対して送信する
	 * オフラインの場合は、対象となるポートからコネクタプロファイルを削除する
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean deleteConnectorR();

}
