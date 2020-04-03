package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

/**
 * ConnectorProfileを表現するクラス
 * <p>
 * 
 * このオブジェクトは、バリューオブジェクトであることに注意すること。<br>
 * このオブジェクト自体は同期が行われないため、このオブジェクトの参照を保持し続けることは、危険である。<br>
 * 事情が許す限り、参照元のオブジェクトを参照して、必要になるたびにそこから手に入れること。
 * 
 * @model
 */
public interface ConnectorProfile extends WrapperObject, IPropertyMap{

	public static final String ANY = "Any";

	public static final String PERIODIC = "Periodic";

	public static final String NEW = "New";

	public static final String PUSH = "Push";

	public static final String SKIP = "Skip";

	public static final String[] PUSH_POLICY_TYPES = new String[] { "all",
			"fifo", "skip", "new" };

	public static final String[] BUFFER_FULL_POLICY_TYPES = new String[] {
			"overwrite", "do_nothing", "block" };

	public static final String[] BUFFER_EMPTY_POLICY_TYPES = new String[] {
			"readback", "do_nothing", "block" };

	public static interface PROP {
		static final String DATA_TYPE = "dataport.data_type";
		static final String INTERFACE_TYPE = "dataport.interface_type";
		static final String DATAFLOW_TYPE = "dataport.dataflow_type";
		static final String SUBSCRIPTION_TYPE = "dataport.subscription_type";
		static final String PUSH_RATE = "dataport.push_rate";
		static final String PUSH_POLICY = "dataport.publisher.push_policy";
		static final String SKIP_COUNT = "dataport.publisher.skip_count";
		static final String TIMESTAMP_POLICY = "dataport.timestamp_policy";
		//
		static final String OUTPORT_BUFF_LENGTH = "dataport.outport.buffer.length";
		static final String OUTPORT_FULL_POLICY = "dataport.outport.buffer.write.full_policy";
		static final String OUTPORT_WRITE_TIMEOUT = "dataport.outport.buffer.write.timeout";
		static final String OUTPORT_EMPTY_POLICY = "dataport.outport.buffer.read.empty_policy";
		static final String OUTPORT_READ_TIMEOUT = "dataport.outport.buffer.read.timeout";
		static final String OUTPORT_SERIALIZER_TYPE = "dataport.outport.marshaling_type";
		//
		static final String INPORT_BUFF_LENGTH = "dataport.inport.buffer.length";
		static final String INPORT_FULL_POLICY = "dataport.inport.buffer.write.full_policy";
		static final String INPORT_WRITE_TIMEOUT = "dataport.inport.buffer.write.timeout";
		static final String INPORT_EMPTY_POLICY = "dataport.inport.buffer.read.empty_policy";
		static final String INPORT_READ_TIMEOUT = "dataport.inport.buffer.read.timeout";
		static final String INPORT_SERIALIZER_TYPE = "dataport.inport.marshaling_type";
	}

	/**
	 * インターフェース指定子 (ref. CorbaPort)
	 */
	public static class InterfaceId {
		public static final String REQUIRED = "required";
		public static final String PROVIDED = "provided";

		public String rtc_name;
		public String port_name;
		public String if_polarity;
		public String if_tname;
		public String if_iname;

		public void setPolarityBy(PortInterfaceProfile profile) {
			if (profile == null) {
				return;
			}
			if_polarity = (profile.isRequiredPolarity()) ? REQUIRED : PROVIDED;
		}

		public static boolean isValid(String id) {
			String[] s = id.split("\\.");
			if (s.length != 6) {
				return false;
			}
			if (!"port".equals(s[1])) {
				return false;
			}
			if (!REQUIRED.equals(s[3]) && !PROVIDED.equals(s[3])) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return rtc_name + ".port." + port_name + "." + if_polarity + "."
					+ if_tname + "." + if_iname;
		}

		@Override
		public InterfaceId clone() {
			InterfaceId c = new InterfaceId();
			c.rtc_name = this.rtc_name;
			c.port_name = this.port_name;
			c.if_polarity = this.if_polarity;
			c.if_tname = this.if_tname;
			c.if_iname = this.if_iname;
			return c;
		}
	}

	/**
	 * 当該コネクタで使用されるデータフロー型を返す。
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataflowType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataflowType <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるデータフロー型を設定する。
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dataflow Type</em>' attribute.
	 * @see #getDataflowType()
	 * @generated
	 */
	void setDataflowType(String value);

	/**
	 * 当該コネクタで使用されるサブスクリプション型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getSubscriptionType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSubscriptionType <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるサブスクリプション型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Type</em>' attribute.
	 * @see #getSubscriptionType()
	 * @generated
	 */
	void setSubscriptionType(String value);

	/**
	 * サブスクリプション型が使用可能か（データフロー型がPUSHであるか）を返す
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isSubscriptionTypeAvailable();

	/**
	 * PUSH間隔が使用可能か（サブスクリプション型が使用可能でサブスクリプション型がPERIODIC）を返す
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isPushIntervalAvailable();

	/**
	 * OutportからInportに流れるデータの型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * OutportからInportに流れるデータの型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 *  当該コネクタで使用されるインターフェース型を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getInterfaceType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInterfaceType <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるインターフェース型を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Type</em>' attribute.
	 * @see #getInterfaceType()
	 * @generated
	 */
	void setInterfaceType(String value);

	/**
	 * 当該コネクタで使用されるデータ送信周期を返す
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public Double getPushRate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushRate <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 当該コネクタで使用されるデータ送信周期を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Push Rate</em>' attribute.
	 * @see #getPushRate()
	 * @generated
	 */
	void setPushRate(Double value);

	/**
	 * Returns the value of the '<em><b>Push Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Push Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Push Policy</em>' attribute.
	 * @see #setPushPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_PushPolicy()
	 * @model
	 * @generated
	 */
	String getPushPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushPolicy <em>Push Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Push Policy</em>' attribute.
	 * @see #getPushPolicy()
	 * @generated
	 */
	void setPushPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Skip Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Count</em>' attribute.
	 * @see #setSkipCount(Integer)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_SkipCount()
	 * @model
	 * @generated
	 */
	Integer getSkipCount();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSkipCount <em>Skip Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Count</em>' attribute.
	 * @see #getSkipCount()
	 * @generated
	 */
	void setSkipCount(Integer value);

	/**
	 * Returns the value of the '<em><b>Push Policy Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Push Policy Available</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Push Policy Available</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_PushPolicyAvailable()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isPushPolicyAvailable();

	/**
	 * Returns the value of the '<em><b>Skip Count Available</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Skip Count Available</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip Count Available</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_SkipCountAvailable()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isSkipCountAvailable();

	/**
	 * Returns the value of the '<em><b>Source String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続元であるポートの識別文字列を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source String</em>' attribute.
	 * @see #setSourceString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_SourceString()
	 * @model
	 * @generated
	 */
	String getSourceString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSourceString <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コネクタの接続元であるポートの識別文字列を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source String</em>' attribute.
	 * @see #getSourceString()
	 * @generated
	 */
	void setSourceString(String value);

	/**
	 * Returns the value of the '<em><b>Target String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * コネクタの接続先であるポートの識別文字列を返す
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target String</em>' attribute.
	 * @see #setTargetString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_TargetString()
	 * @model
	 * @generated
	 */
	String getTargetString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTargetString <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * コネクタの接続先であるポートの識別文字列を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target String</em>' attribute.
	 * @see #getTargetString()
	 * @generated
	 */
	void setTargetString(String value);

	/**
	 * Returns the value of the '<em><b>Outport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Serializer Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Serializer Type</em>' attribute.
	 * @see #setOutportSerializerType(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportSerializerType()
	 * @model
	 * @generated
	 */
	String getOutportSerializerType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportSerializerType <em>Outport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Serializer Type</em>' attribute.
	 * @see #getOutportSerializerType()
	 * @generated
	 */
	void setOutportSerializerType(String value);

	/**
	 * Returns the value of the '<em><b>Outport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Buffer Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Buffer Length</em>' attribute.
	 * @see #setOutportBufferLength(Integer)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportBufferLength()
	 * @model
	 * @generated
	 */
	Integer getOutportBufferLength();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferLength <em>Outport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Buffer Length</em>' attribute.
	 * @see #getOutportBufferLength()
	 * @generated
	 */
	void setOutportBufferLength(Integer value);

	/**
	 * Returns the value of the '<em><b>Outport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Buffer Full Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Buffer Full Policy</em>' attribute.
	 * @see #setOutportBufferFullPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportBufferFullPolicy()
	 * @model
	 * @generated
	 */
	String getOutportBufferFullPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferFullPolicy <em>Outport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Buffer Full Policy</em>' attribute.
	 * @see #getOutportBufferFullPolicy()
	 * @generated
	 */
	void setOutportBufferFullPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Outport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Buffer Write Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Buffer Write Timeout</em>' attribute.
	 * @see #setOutportBufferWriteTimeout(Double)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportBufferWriteTimeout()
	 * @model
	 * @generated
	 */
	Double getOutportBufferWriteTimeout();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferWriteTimeout <em>Outport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Buffer Write Timeout</em>' attribute.
	 * @see #getOutportBufferWriteTimeout()
	 * @generated
	 */
	void setOutportBufferWriteTimeout(Double value);

	/**
	 * Returns the value of the '<em><b>Outport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Buffer Empty Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Buffer Empty Policy</em>' attribute.
	 * @see #setOutportBufferEmptyPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportBufferEmptyPolicy()
	 * @model
	 * @generated
	 */
	String getOutportBufferEmptyPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferEmptyPolicy <em>Outport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Buffer Empty Policy</em>' attribute.
	 * @see #getOutportBufferEmptyPolicy()
	 * @generated
	 */
	void setOutportBufferEmptyPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Outport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outport Buffer Read Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Buffer Read Timeout</em>' attribute.
	 * @see #setOutportBufferReadTimeout(Double)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_OutportBufferReadTimeout()
	 * @model
	 * @generated
	 */
	Double getOutportBufferReadTimeout();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getOutportBufferReadTimeout <em>Outport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outport Buffer Read Timeout</em>' attribute.
	 * @see #getOutportBufferReadTimeout()
	 * @generated
	 */
	void setOutportBufferReadTimeout(Double value);

	/**
	 * Returns the value of the '<em><b>Inport Buffer Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Buffer Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Buffer Length</em>' attribute.
	 * @see #setInportBufferLength(Integer)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportBufferLength()
	 * @model
	 * @generated
	 */
	Integer getInportBufferLength();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferLength <em>Inport Buffer Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Buffer Length</em>' attribute.
	 * @see #getInportBufferLength()
	 * @generated
	 */
	void setInportBufferLength(Integer value);

	/**
	 * Returns the value of the '<em><b>Inport Buffer Full Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Buffer Full Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Buffer Full Policy</em>' attribute.
	 * @see #setInportBufferFullPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportBufferFullPolicy()
	 * @model
	 * @generated
	 */
	String getInportBufferFullPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferFullPolicy <em>Inport Buffer Full Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Buffer Full Policy</em>' attribute.
	 * @see #getInportBufferFullPolicy()
	 * @generated
	 */
	void setInportBufferFullPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Inport Buffer Write Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Buffer Write Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Buffer Write Timeout</em>' attribute.
	 * @see #setInportBufferWriteTimeout(Double)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportBufferWriteTimeout()
	 * @model
	 * @generated
	 */
	Double getInportBufferWriteTimeout();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferWriteTimeout <em>Inport Buffer Write Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Buffer Write Timeout</em>' attribute.
	 * @see #getInportBufferWriteTimeout()
	 * @generated
	 */
	void setInportBufferWriteTimeout(Double value);

	/**
	 * Returns the value of the '<em><b>Inport Buffer Empty Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Buffer Empty Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Buffer Empty Policy</em>' attribute.
	 * @see #setInportBufferEmptyPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportBufferEmptyPolicy()
	 * @model
	 * @generated
	 */
	String getInportBufferEmptyPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferEmptyPolicy <em>Inport Buffer Empty Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Buffer Empty Policy</em>' attribute.
	 * @see #getInportBufferEmptyPolicy()
	 * @generated
	 */
	void setInportBufferEmptyPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Inport Buffer Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Buffer Read Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Buffer Read Timeout</em>' attribute.
	 * @see #setInportBufferReadTimeout(Double)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportBufferReadTimeout()
	 * @model
	 * @generated
	 */
	Double getInportBufferReadTimeout();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportBufferReadTimeout <em>Inport Buffer Read Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Buffer Read Timeout</em>' attribute.
	 * @see #getInportBufferReadTimeout()
	 * @generated
	 */
	void setInportBufferReadTimeout(Double value);

	/**
	 * Returns the value of the '<em><b>Inport Serializer Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inport Serializer Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inport Serializer Type</em>' attribute.
	 * @see #setInportSerializerType(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_InportSerializerType()
	 * @model
	 * @generated
	 */
	String getInportSerializerType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInportSerializerType <em>Inport Serializer Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inport Serializer Type</em>' attribute.
	 * @see #getInportSerializerType()
	 * @generated
	 */
	void setInportSerializerType(String value);

	/**
	 * Returns the value of the '<em><b>Timestamp Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp Policy</em>' attribute.
	 * @see #setTimestampPolicy(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_TimestampPolicy()
	 * @model
	 * @generated
	 */
	String getTimestampPolicy();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTimestampPolicy <em>Timestamp Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp Policy</em>' attribute.
	 * @see #getTimestampPolicy()
	 * @generated
	 */
	void setTimestampPolicy(String value);

	/**
	 * Returns the value of the '<em><b>Is Reverse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Reverse</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Reverse</em>' attribute.
	 * @see #setIsReverse(boolean)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_IsReverse()
	 * @model
	 * @generated
	 */
	boolean isIsReverse();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#isIsReverse <em>Is Reverse</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Reverse</em>' attribute.
	 * @see #isIsReverse()
	 * @generated
	 */
	void setIsReverse(boolean value);

	/**
	 * @model
	 * @return	接続情報の名称
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * 接続情報の名称を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model
	 * @return　接続情報の一意識別子
	 */
	public String getConnectorId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getConnectorId <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 接続情報の一意識別子を設定する
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Id</em>' attribute.
	 * @see #getConnectorId()
	 * @generated
	 */
	void setConnectorId(String value);

}
