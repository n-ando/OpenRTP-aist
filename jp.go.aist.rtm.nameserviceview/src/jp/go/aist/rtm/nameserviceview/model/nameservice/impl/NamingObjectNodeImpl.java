/**
 * <copyright>
 * </copyright>
 *
 * $Id: NamingObjectNodeImpl.java,v 1.7 2008/03/27 06:58:52 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.factory.NameServiceViewWrapperFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.OneReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Naming Object Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingObjectNodeImpl#getEntry <em>Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NamingObjectNodeImpl extends CorbaNodeImpl implements NamingObjectNode {

	private static final Logger LOGGER = LoggerFactory.getLogger(NamingObjectNodeImpl.class);

	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected WrapperObject entry;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NamingObjectNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NameservicePackage.Literals.NAMING_OBJECT_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isZombie() {
		Binding bind = getNameServiceReference().getBinding();
		NameComponent nc = bind.binding_name[bind.binding_name.length - 1];
		String kind = (nc == null) ? "" : nc.kind;
		List<String> targetZombies = Arrays.asList("rtc", "mgr");
		return (targetZombies.contains(kind) && getEntry() == null);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WrapperObject getEntry() {
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEntry(WrapperObject newEntry) {
		WrapperObject oldEntry = entry;
		entry = newEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAMING_OBJECT_NODE__ENTRY, oldEntry, entry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				return getEntry();
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
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				setEntry((WrapperObject)newValue);
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
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				setEntry((WrapperObject)null);
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
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				return entry != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * ネームサービスに登録されているRTCManagerのリストを返します。
	 */
	@Override
	public List<RTCManager> getRTCManagerList() {
		if (!(getEntry() instanceof RTCManager))
			return Collections.emptyList();

		RTCManager mgr = (RTCManager) getEntry();
		mgr.setPathId(getBindPath());
		List<RTCManager> list = new ArrayList<RTCManager>();
		list.add(mgr);
		return list;
	}

	private String getBindPath() {
		NameComponent[] cs = getNameServiceReference().getBinding().binding_name;
		String path = "";
		for (NameComponent c : cs) {
			if (path.length() > 0) {
				path += "/";
			}
			path += c.id;
		}
		return path;
	}

	public static final ThreadLocal<NamingObjectNode> PROXY_THREAD_LOCAL = new ThreadLocal<NamingObjectNode>();

	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					NamingObjectNodeImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							Object.class, CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) },
					true) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return link instanceof Binding 
							&& ((Binding) link).binding_type == BindingType.nobject;
				}

				@Override
				public LocalObject createLocalObject(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					NamingObjectNode result = (NamingObjectNode) super
							.createLocalObject(parent, remoteObjects, link);

					NameServiceReference nsref = ((CorbaNode) parent)
							.getNameServiceReference().createChildReference(
									(Binding) link);

					((CorbaNode) result).setNameServiceReference(nsref);
					return result;
				}

			},
			new AttributeMapping[] { new AttributeMapping(CorePackage.eINSTANCE.getCorbaWrapperObject_CorbaObject()) {
				// ゾンビの場合に、新たなオブジェクトに更新されても検出できるようにするための同期。
				// ここで実装するのはあまりきれいではない設計
				// 本来ならば、NamingContextExtで検出するべきだが、
				// ゾンビ化して新たなオブジェクトに更新されてもlinkが全く同じであるため、
				// 違うNamingObjectであると判断されないため、ここに実装することにした。

				@Override
				public Object getRemoteAttributeValue(LocalObject localObject, Object[] remoteObjects) {
					NamingObjectNode namingObjectNode = ((NamingObjectNode) localObject);
					Object result = null;
					if (namingObjectNode.eContainer() != null) {
						Binding binding = namingObjectNode.getNameServiceReference().getBinding();
						try {
							result = ((NamingContextNode) namingObjectNode.eContainer()).getCorbaObjectInterface()
									.resolve(new NameComponent[] {
											binding.binding_name[binding.binding_name.length - 1] });
							if (result != null && !SynchronizationSupport.ping(result)) {
								// LOGGER.debug("getRemoteAttributeValue: ping lost for result=<{}>", result);
								result = null;
							}
						} catch (Exception e) {
							LOGGER.debug("getRemoteAttributeValue: Fail to resolve corba object. exception=<{}>", e);
						}
					} else {
						result = namingObjectNode.getCorbaObject();
					}
					return result;
				}
			}

			}, // null
			new ReferenceMapping[] { new OneReferenceMapping(NameservicePackage.eINSTANCE.getNamingObjectNode_Entry()) {
				@Override
				public Object getNewRemoteLink(LocalObject localObject, Object[] remoteObjects) {
					return ((NamingObjectNode) localObject).getCorbaObject();
				}

				@Override
				public boolean isLinkEquals(LocalObject localObject, Object link1, Object link2) {
					if (link1 == null) {
						return (link2 == null);
					}
					return link1.equals(link2);
				}

				@Override
				public LocalObject loadLocalObjectByRemoteObject(LocalObject localObject,
						SynchronizationManager synchronizationManager, java.lang.Object link, Object[] remoteObject) {
					LocalObject result = super.loadLocalObjectByRemoteObject(localObject, synchronizationManager, link,
							remoteObject);

					if (result instanceof Component) {
						String pathId = ((NamingObjectNode) localObject).getNameServiceReference().getPathId();
						((Component) result).setPathId(pathId);
					}
					return result;
				}
			} });

	/**
	 * エントリの同期 (一括更新)
	 * 
	 * @param binding_object
	 */
	void synchronizeEntry(org.omg.CORBA.Object binding_object) {
		LocalObject lo = NameServiceViewWrapperFactory.getInstance()
				.getSynchronizationManager().createLocalObject(this,
						new Object[] { binding_object }, binding_object, true);
		if (lo == null) {
			eSet(NameservicePackage.eINSTANCE.getNamingObjectNode_Entry(), lo);
			return;
		}
		if (getEntry() != null
				&& binding_object._is_equivalent(getCorbaObject())) {
			return;
		}
		setCorbaObject(binding_object);
		if (lo instanceof Component) {
			String pathId = getNameServiceReference().getPathId();
			((Component) lo).setPathId(pathId);
		}
		eSet(NameservicePackage.eINSTANCE.getNamingObjectNode_Entry(), lo);
	}

} // NamingObjectNodeImpl
