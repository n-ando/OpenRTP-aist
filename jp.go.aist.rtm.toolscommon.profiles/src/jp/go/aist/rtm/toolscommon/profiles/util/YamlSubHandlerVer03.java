package jp.go.aist.rtm.toolscommon.profiles.util;

import static jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler.createXMLGregorianCalendar;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.openrtp.namespaces.rtc.version02.And;
import org.openrtp.namespaces.rtc.version02.ConstraintHashType;
import org.openrtp.namespaces.rtc.version02.ConstraintListType;
import org.openrtp.namespaces.rtc.version02.ConstraintType;
import org.openrtp.namespaces.rtc.version02.ConstraintUnitType;
import org.openrtp.namespaces.rtc.version02.Or;
import org.openrtp.namespaces.rtc.version02.PropertyIsBetween;
import org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThan;
import org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo;

import org.openrtp.namespaces.rtc.version03.ActionStatusDoc;
import org.openrtp.namespaces.rtc.version03.Actions;
import org.openrtp.namespaces.rtc.version03.BasicInfoExt;
import org.openrtp.namespaces.rtc.version03.ConfigurationExt;
import org.openrtp.namespaces.rtc.version03.ConfigurationSet;
import org.openrtp.namespaces.rtc.version03.DataportExt;
import org.openrtp.namespaces.rtc.version03.DocAction;
import org.openrtp.namespaces.rtc.version03.DocBasic;
import org.openrtp.namespaces.rtc.version03.DocConfiguration;
import org.openrtp.namespaces.rtc.version03.DocDataport;
import org.openrtp.namespaces.rtc.version03.DocServiceinterface;
import org.openrtp.namespaces.rtc.version03.DocServiceport;
import org.openrtp.namespaces.rtc.version03.LanguageExt;
import org.openrtp.namespaces.rtc.version03.Library;
import org.openrtp.namespaces.rtc.version03.ObjectFactory;
import org.openrtp.namespaces.rtc.version03.Position;
import org.openrtp.namespaces.rtc.version03.Property;
import org.openrtp.namespaces.rtc.version03.RtcProfile;
import org.openrtp.namespaces.rtc.version03.ServiceinterfaceExt;
import org.openrtp.namespaces.rtc.version03.ServiceportExt;
import org.openrtp.namespaces.rtc.version03.TargetEnvironment;
import org.openrtp.namespaces.rtc.version03.TransmissionMethod;

import jp.go.aist.rtm.toolscommon.profiles.nl.Messages;

public class YamlSubHandlerVer03 {

	@SuppressWarnings("unchecked")
	public RtcProfile mapToRtc(Map profileYOrg) throws Exception {
		boolean isActionAdded = false;
		RtcProfile profile = null;
		ObjectFactory factory = new ObjectFactory();
		if( profileYOrg != null ) {
			Map profileY  = (Map)profileYOrg.get("rtcProfile");
			if( profileY != null ) {
				profile = factory.createRtcProfile();
				profile.setId((String)profileY.get("id"));
				profile.setVersion((String)profileY.get("version"));
				//基本
				Map basicY = (Map)profileY.get("basicInfo");
				if( basicY != null ) {
					BasicInfoExt basic = factory.createBasicInfoExt();
					basic.setName((String)basicY.get("name"));
					basic.setComponentType((String)basicY.get("componentType"));
					basic.setActivityType((String)basicY.get("activityType"));
					basic.setComponentKind((String)basicY.get("componentKind"));
					basic.setRtcType((String)basicY.get("rtcType"));
					basic.setCategory((String)basicY.get("category"));
					basic.setDescription((String)basicY.get("description"));
					basic.setExecutionRate((Double)basicY.get("executionRate"));
					basic.setExecutionType((String)basicY.get("executionType"));
					if(basicY.get("maxInstances")!=null) basic.setMaxInstances(BigInteger.valueOf((Integer)basicY.get("maxInstances")));
					basic.setVendor((String)basicY.get("vendor"));
					basic.setVersion((String)basicY.get("version"));
					basic.setAbstract((String)basicY.get("abstract"));
					basic.setHardwareProfile((String)basicY.get("hardwareProfile"));
					basic.setCreationDate(createXMLGregorianCalendar((Map<String, Integer>) basicY.get("creationDate")));
					basic.setUpdateDate(createXMLGregorianCalendar((Map<String, Integer>) basicY.get("updateDate")));
					//Basic Doc
					Map docbasicY = (Map)basicY.get("rtcDoc::doc");
					if( docbasicY != null ) {
						DocBasic docbasic = factory.createDocBasic();
						docbasic.setDescription((String)docbasicY.get("description"));
						docbasic.setInout((String)docbasicY.get("inout"));
						docbasic.setAlgorithm((String)docbasicY.get("algorithm"));
						docbasic.setCreator((String)docbasicY.get("creator"));
						docbasic.setLicense((String)docbasicY.get("license"));
						docbasic.setReference((String)docbasicY.get("reference"));
						basic.setDoc(docbasic);
					}
					//Basic ext
					if( basicY.get("rtcExt::versionUpLogs")!=null )basic.getVersionUpLogs().addAll((List)basicY.get("rtcExt::versionUpLogs"));
					basic.setComment((String)basicY.get("rtcExt::comment"));
					basic.setSaveProject((String)basicY.get("rtcExt::saveProject"));
					//Properties
					List basicPropertiesListY = (List)basicY.get("rtcExt::properties");
					if( basicPropertiesListY != null ) {
						for(int intIdx=0;intIdx<basicPropertiesListY.size();intIdx++ ) {
							Map basicPropInfoY = (Map)basicPropertiesListY.get(intIdx);
							if( basicPropInfoY != null ) {
								Property prop = factory.createProperty();
								prop.setName((String)basicPropInfoY.get("name"));
								prop.setValue((String)basicPropInfoY.get("value"));
								basic.getProperties().add(prop);
							}
						}
					}
					profile.setBasicInfo(basic);
				}
				//Actions
				Map actionsY = (Map)profileY.get("actions");
				if( actionsY != null ) {
					Actions actions = factory.createActions();
					Map eachActionY = (Map)actionsY.get("onInitialize");
					if( eachActionY != null ) {
						actions.setOnInitialize(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onFinalize");
					if( eachActionY != null ) {
						actions.setOnFinalize(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onStartup");
					if( eachActionY != null ) {
						actions.setOnStartup(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onShutdown");
					if( eachActionY != null ) {
						actions.setOnShutdown(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onActivated");
					if( eachActionY != null ) {
						actions.setOnActivated(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onDeactivated");
					if( eachActionY != null ) {
						actions.setOnDeactivated(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onExecute");
					if( eachActionY != null ) {
						actions.setOnExecute(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onAborting");
					if( eachActionY != null ) {
						actions.setOnAborting(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onError");
					if( eachActionY != null ) {
						actions.setOnError(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onReset");
					if( eachActionY != null ) {
						actions.setOnReset(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onStateUpdate");
					if( eachActionY != null ) {
						actions.setOnStateUpdate(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onRateChanged");
					if( eachActionY != null ) {
						actions.setOnRateChanged(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onAction");
					if( eachActionY != null ) {
						actions.setOnAction(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					eachActionY = (Map)actionsY.get("onModeChanged");
					if( eachActionY != null ) {
						actions.setOnModeChanged(createActionFromYaml(eachActionY));
						isActionAdded = true;
					}
					//
					if( isActionAdded ) profile.setActions(actions);
				}

				//Data Ports
				List dataPortListY = (List)profileY.get("dataPorts");
				if( dataPortListY != null ) {
					for(int intIdx=0;intIdx<dataPortListY.size();intIdx++ ) {
						Map dataPortInfoY = (Map)dataPortListY.get(intIdx);
						if( dataPortInfoY != null ) {
							DataportExt dataport = createDataPortFromYaml(dataPortInfoY);
							profile.getDataPorts().add(dataport);
						}
					}
				}
				//Service Ports
				List servicePortListY = (List)profileY.get("servicePorts");
				if( servicePortListY != null ) {
					for(int intIdx=0;intIdx<servicePortListY.size();intIdx++ ) {
						Map servicePortY = (Map)servicePortListY.get(intIdx);
						if( servicePortY != null ) {
							ServiceportExt serviceport = createServicePortFromYaml(servicePortY);
							profile.getServicePorts().add(serviceport);
						}
					}
				}
				//Configuration
				Map configSetInfoY = (Map)profileY.get("configurationSet");
				if( configSetInfoY != null ) {
					List configsInfoY = (List)configSetInfoY.get("configuration");
					if( configsInfoY != null ) {
						ConfigurationSet configset = factory.createConfigurationSet();
						for(int intIdx=0;intIdx<configsInfoY.size();intIdx++ ) {
							Map configInfoY = (Map)configsInfoY.get(intIdx);
							if( configInfoY != null ) {
								ConfigurationExt config = factory.createConfigurationExt();
								config.setName((String)configInfoY.get("name"));
								config.setType((String)configInfoY.get("type"));
								config.setDefaultValue((String)configInfoY.get("defaultValue"));
								config.setUnit((String)configInfoY.get("unit"));
								configset.getConfiguration().add(config);
								//constraint
								Map constY = (Map)configInfoY.get("constraint");
								config.setConstraint(convertConstraint(factory, constY));
								//Doc
								Map configDocY = (Map)configInfoY.get("rtcDoc::doc");
								if( configDocY != null ) {
									DocConfiguration docconfig = factory.createDocConfiguration();
									docconfig.setDataname((String)configDocY.get("dataname"));
									docconfig.setDefaultValue((String)configDocY.get("defaultValue"));
									docconfig.setDescription((String)configDocY.get("description"));
									docconfig.setUnit((String)configDocY.get("unit"));
									docconfig.setRange((String)configDocY.get("range"));
									docconfig.setConstraint((String)configDocY.get("constraint"));

									config.setDoc(docconfig);
								}
								//Ext
								config.setVariableName((String)configInfoY.get("rtcExt::variableName"));
								config.setComment((String)configInfoY.get("rtcExt::comment"));
								List configPropertiesListY = (List)configInfoY.get("rtcExt::properties");
								if( configPropertiesListY != null ) {
									for(int intIdxif=0;intIdxif<configPropertiesListY.size();intIdxif++ ) {
										Map ifPropInfoY = (Map)configPropertiesListY.get(intIdxif);
										if( ifPropInfoY != null ) {
											Property prop = factory.createProperty();
											prop.setName((String)ifPropInfoY.get("name"));
											prop.setValue((String)ifPropInfoY.get("value"));
											config.getProperties().add(prop);
										}
									}
								}
							}
						}
						profile.setConfigurationSet(configset);
					}
				}

				//Language
				Map langInfoY = (Map)profileY.get("language");
				if( langInfoY != null ) {
					LanguageExt language = factory.createLanguageExt();
					language.setKind((String)langInfoY.get("kind"));
					//Ext
					List targetInfoY = (List)langInfoY.get("rtcExt::targets");
					if( targetInfoY != null ) {
						if( targetInfoY != null ) {
							for(int intIdx=0;intIdx<targetInfoY.size();intIdx++ ) {
								Map envInfoY = (Map)targetInfoY.get(intIdx);
								if( envInfoY != null ) {
									TargetEnvironment env = factory.createTargetEnvironment();
									env.setLangVersion((String)envInfoY.get("langVersion"));
									env.setOs((String)envInfoY.get("os"));
									env.setOther((String)envInfoY.get("other"));
									env.setCpuOther((String)envInfoY.get("cpuOther"));
									//
									List osVersionListY = (List)envInfoY.get("osVersions");
									if( osVersionListY != null ) {
										for(int intIdxos=0;intIdxos<osVersionListY.size();intIdxos++ ) {
											env.getOsVersions().add((String)osVersionListY.get(intIdxos));
										}
									}
									//
									List cpuArchListY = (List)envInfoY.get("cpus");
									if( cpuArchListY != null ) {
										for(int intIdxcpu=0;intIdxcpu<cpuArchListY.size();intIdxcpu++ ) {
											env.getCpus().add((String)cpuArchListY.get(intIdxcpu));
										}
									}
									//
									List libraruListY = (List)envInfoY.get("libraries");
									if( libraruListY != null ) {
										for(int intIdxif=0;intIdxif<libraruListY.size();intIdxif++ ) {
											Map libInfoY = (Map)libraruListY.get(intIdxif);
											if( libInfoY != null ) {
												Library lib = factory.createLibrary();
												lib.setName((String)libInfoY.get("name"));
												lib.setVersion((String)libInfoY.get("version"));
												lib.setOther((String)libInfoY.get("other"));
												env.getLibraries().add(lib);
											}
										}
									}
									language.getTargets().add(env);
								}
							}
						}
					}
					profile.setLanguage(language);
				}
			}
		}
		return profile;
	}

	private ActionStatusDoc createActionFromYaml(Map actionY) {
		ObjectFactory factory = new ObjectFactory();
		ActionStatusDoc actionStatus = factory.createActionStatusDoc();
		Boolean impl = (Boolean)actionY.get("implemented");
		if( impl!=null) {
			actionStatus.setImplementedbln(impl.booleanValue());
		} else {
			actionStatus.setImplementedbln(false);
		}

		Map docActionY = (Map)actionY.get("rtcDoc::doc");
		if( docActionY != null ) {
			DocAction docAction = factory.createDocAction();
			docAction.setDescription((String)docActionY.get("description"));
			docAction.setPreCondition((String)docActionY.get("preCondition"));
			docAction.setPostCondition((String)docActionY.get("postCondition"));
			actionStatus.setDoc(docAction);
		}
		return actionStatus;
	}

	private DataportExt createDataPortFromYaml(Map yamlMap) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		DataportExt dataport = factory.createDataportExt();
		dataport.setPortType((String)yamlMap.get("portType"));
		dataport.setName((String)yamlMap.get("name"));
		dataport.setType((String)yamlMap.get("type"));
		dataport.setIdlFile((String)yamlMap.get("idlFile"));
		dataport.setInterfaceType((String)yamlMap.get("interfaceType"));
		dataport.setDataflowType((String)yamlMap.get("dataflowType"));
		dataport.setSubscriptionType((String)yamlMap.get("subscriptionType"));
		dataport.setUnit((String)yamlMap.get("unit"));
		//constraint
		Map constY = (Map)yamlMap.get("constraint");
		dataport.setConstraint(convertConstraintV2(constY));
		//Doc
		Map portdocY = (Map)yamlMap.get("rtcDoc::doc");
		if( portdocY != null ) {
			DocDataport docdataport = factory.createDocDataport();
			docdataport.setDescription((String)portdocY.get("description"));
			docdataport.setType((String)portdocY.get("type"));
			docdataport.setNumber((String)portdocY.get("number"));
			docdataport.setSemantics((String)portdocY.get("semantics"));
			docdataport.setUnit((String)portdocY.get("unit"));
			docdataport.setOccerrence((String)portdocY.get("occurrence"));
			docdataport.setOperation((String)portdocY.get("operation"));
			dataport.setDoc(docdataport);
		}
		//Ext
		if( yamlMap.get("rtcExt::position")!=null )
			dataport.setPosition(Position.fromValue(((String)yamlMap.get("rtcExt::position")).toUpperCase()));
		dataport.setVariableName((String)yamlMap.get("rtcExt::variableName"));
		dataport.setComment((String)yamlMap.get("rtcExt::comment"));
		List dpPropertiesListY = (List)yamlMap.get("rtcExt::properties");
		if( dpPropertiesListY != null ) {
			for(int intIdx=0;intIdx<dpPropertiesListY.size();intIdx++ ) {
				Map dpPropInfoY = (Map)dpPropertiesListY.get(intIdx);
				if( dpPropInfoY != null ) {
					Property prop = factory.createProperty();
					prop.setName((String)dpPropInfoY.get("name"));
					prop.setValue((String)dpPropInfoY.get("value"));
					dataport.getProperties().add(prop);
				}
			}
		}
		//
		return dataport;
	}

	private ConstraintType convertConstraint(ObjectFactory factory, Map constY) throws Exception {
		ConstraintType result = null;
		if( constY!=null ) {
			result = factory.createConstraintType();
			if( constY.get("constraintUnitType")!=null ) {
				Map unitY = (Map)constY.get("constraintUnitType");
				ConstraintUnitType unitP = factory.createConstraintUnitType();
				result.setConstraintUnitType(unitP);
				Map targetY = null;
				String literal = null;
				//
				if( unitY.get("key")!=null ) {
					literal = (String)unitY.get("key");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.126"));
					unitP.setKey(literal);
				}
				//
				if( unitY.get("propertyIsEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.125"));
					PropertyIsEqualTo equalP = factory.createPropertyIsEqualTo();
					unitP.setPropertyIsEqualTo(equalP);
					equalP.setLiteral(literal);
					return result;

				} else if( unitY.get("propertyIsGreaterThan")!=null ) {
					targetY = (Map)unitY.get("propertyIsGreaterThan");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.134"));
					PropertyIsGreaterThan greaterP = factory.createPropertyIsGreaterThan();
					unitP.setPropertyIsGreaterThan(greaterP);
					greaterP.setLiteral(literal);
					return result;

				} else if( unitY.get("propertyIsGreaterThanOrEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsGreaterThanOrEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.124"));
					PropertyIsGreaterThanOrEqualTo greaterEqualP = factory.createPropertyIsGreaterThanOrEqualTo();
					unitP.setPropertyIsGreaterThanOrEqualTo(greaterEqualP);
					greaterEqualP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsLessThan")!=null ) {
					targetY = (Map)unitY.get("propertyIsLessThan");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.142"));
					PropertyIsLessThan lessP = factory.createPropertyIsLessThan();
					unitP.setPropertyIsLessThan(lessP);
					lessP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsLessThanOrEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsLessThanOrEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.146"));
					PropertyIsLessThanOrEqualTo lessEqualP = factory.createPropertyIsLessThanOrEqualTo();
					unitP.setPropertyIsLessThanOrEqualTo(lessEqualP);
					lessEqualP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsBetween")!=null ) {
					targetY = (Map)unitY.get("propertyIsBetween");
					String lower = (String)targetY.get("lowerBoundary");
					if( lower==null || lower.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.150"));
					String upper = (String)targetY.get("upperBoundary");
					if( upper==null || upper.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.152"));

					PropertyIsBetween betweenP = factory.createPropertyIsBetween();
					unitP.setPropertyIsBetween(betweenP);
					betweenP.setLowerBoundary(lower);
					betweenP.setUpperBoundary(upper);
					return result;

				} else 	if( unitY.get("and")!=null ) {
					targetY = (Map)unitY.get("and");
					List constraintListY = (List)targetY.get("constraint");
					And andP = factory.createAnd();
					unitP.setAnd(andP);
					for(int index=0;index<constraintListY.size();index++) {
						andP.getConstraint().add(convertConstraint(factory, (Map)constraintListY.get(index)));
					}
					return result;

				} else if( unitY.get("or")!=null ) {
					targetY = (Map)unitY.get("or");
					List constraintListY = (List)targetY.get("constraint");
					Or orP = factory.createOr();
					unitP.setOr(orP);
					for(int index=0;index<constraintListY.size();index++) {
						orP.getConstraint().add(convertConstraint(factory, (Map)constraintListY.get(index)));
					}
					return result;
				}

			} else if( constY.get("constraintListType")!=null ) {
				Map listY = (Map)constY.get("constraintListType");
				ConstraintListType listP = factory.createConstraintListType();
				result.setConstraintListType(listP);
				List constraintListY = (List)listY.get("constraint");
				for(int index=0;index<constraintListY.size();index++) {
					listP.getConstraint().add(convertConstraint(factory, (Map)constraintListY.get(index)));
				}
				return result;

			} else if( constY.get("constraintHashType")!=null ) {
				Map hashY = (Map)constY.get("constraintHashType");
				ConstraintHashType hashP = factory.createConstraintHashType();
				result.setConstraintHashType(hashP);
				List constraintListY = (List)hashY.get("constraint");
				for(int index=0;index<constraintListY.size();index++) {
					hashP.getConstraint().add(convertConstraint(factory, (Map)constraintListY.get(index)));
				}
				return result;
			}
		}
		return result;
	}

	private org.openrtp.namespaces.rtc.version02.ConstraintType convertConstraintV2(Map constY) throws Exception {
		org.openrtp.namespaces.rtc.version02.ObjectFactory factory = new org.openrtp.namespaces.rtc.version02.ObjectFactory();
		org.openrtp.namespaces.rtc.version02.ConstraintType result = null;
		if( constY!=null ) {
			result = factory.createConstraintType();
			if( constY.get("constraintUnitType")!=null ) {
				Map unitY = (Map)constY.get("constraintUnitType");
				org.openrtp.namespaces.rtc.version02.ConstraintUnitType unitP = factory.createConstraintUnitType();
				result.setConstraintUnitType(unitP);
				Map targetY = null;
				String literal = null;
				//
				if( unitY.get("key")!=null ) {
					literal = (String)unitY.get("key");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.126"));
					unitP.setKey(literal);
				}
				//
				if( unitY.get("propertyIsEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.125"));
					org.openrtp.namespaces.rtc.version02.PropertyIsEqualTo equalP = factory.createPropertyIsEqualTo();
					unitP.setPropertyIsEqualTo(equalP);
					equalP.setLiteral(literal);
					return result;

				} else if( unitY.get("propertyIsGreaterThan")!=null ) {
					targetY = (Map)unitY.get("propertyIsGreaterThan");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.134"));
					org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThan greaterP = factory.createPropertyIsGreaterThan();
					unitP.setPropertyIsGreaterThan(greaterP);
					greaterP.setLiteral(literal);
					return result;

				} else if( unitY.get("propertyIsGreaterThanOrEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsGreaterThanOrEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.124"));
					org.openrtp.namespaces.rtc.version02.PropertyIsGreaterThanOrEqualTo greaterEqualP = factory.createPropertyIsGreaterThanOrEqualTo();
					unitP.setPropertyIsGreaterThanOrEqualTo(greaterEqualP);
					greaterEqualP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsLessThan")!=null ) {
					targetY = (Map)unitY.get("propertyIsLessThan");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.142"));
					org.openrtp.namespaces.rtc.version02.PropertyIsLessThan lessP = factory.createPropertyIsLessThan();
					unitP.setPropertyIsLessThan(lessP);
					lessP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsLessThanOrEqualTo")!=null ) {
					targetY = (Map)unitY.get("propertyIsLessThanOrEqualTo");
					literal = (String)targetY.get("literal");
					if( literal==null || literal.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.146"));
					org.openrtp.namespaces.rtc.version02.PropertyIsLessThanOrEqualTo lessEqualP = factory.createPropertyIsLessThanOrEqualTo();
					unitP.setPropertyIsLessThanOrEqualTo(lessEqualP);
					lessEqualP.setLiteral(literal);
					return result;

				} else 	if( unitY.get("propertyIsBetween")!=null ) {
					targetY = (Map)unitY.get("propertyIsBetween");
					String lower = (String)targetY.get("lowerBoundary");
					if( lower==null || lower.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.150"));
					String upper = (String)targetY.get("upperBoundary");
					if( upper==null || upper.length()<=0 )
						throw new Exception(Messages.getString("YamlSubHandlerVer02.152"));

					org.openrtp.namespaces.rtc.version02.PropertyIsBetween betweenP = factory.createPropertyIsBetween();
					unitP.setPropertyIsBetween(betweenP);
					betweenP.setLowerBoundary(lower);
					betweenP.setUpperBoundary(upper);
					return result;

				} else 	if( unitY.get("and")!=null ) {
					targetY = (Map)unitY.get("and");
					List constraintListY = (List)targetY.get("constraint");
					org.openrtp.namespaces.rtc.version02.And andP = factory.createAnd();
					unitP.setAnd(andP);
					for(int index=0;index<constraintListY.size();index++) {
						andP.getConstraint().add(convertConstraintV2((Map)constraintListY.get(index)));
					}
					return result;

				} else if( unitY.get("or")!=null ) {
					targetY = (Map)unitY.get("or");
					List constraintListY = (List)targetY.get("constraint");
					org.openrtp.namespaces.rtc.version02.Or orP = factory.createOr();
					unitP.setOr(orP);
					for(int index=0;index<constraintListY.size();index++) {
						orP.getConstraint().add(convertConstraintV2((Map)constraintListY.get(index)));
					}
					return result;
				}

			} else if( constY.get("constraintListType")!=null ) {
				Map listY = (Map)constY.get("constraintListType");
				org.openrtp.namespaces.rtc.version02.ConstraintListType listP = factory.createConstraintListType();
				result.setConstraintListType(listP);
				List constraintListY = (List)listY.get("constraint");
				for(int index=0;index<constraintListY.size();index++) {
					listP.getConstraint().add(convertConstraintV2((Map)constraintListY.get(index)));
				}
				return result;

			} else if( constY.get("constraintHashType")!=null ) {
				Map hashY = (Map)constY.get("constraintHashType");
				org.openrtp.namespaces.rtc.version02.ConstraintHashType hashP = factory.createConstraintHashType();
				result.setConstraintHashType(hashP);
				List constraintListY = (List)hashY.get("constraint");
				for(int index=0;index<constraintListY.size();index++) {
					hashP.getConstraint().add(convertConstraintV2((Map)constraintListY.get(index)));
				}
				return result;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private ServiceportExt createServicePortFromYaml(Map yamlMap) {
		ObjectFactory factory = new ObjectFactory();
		ServiceportExt serviceport = factory.createServiceportExt();
		//
		serviceport.setName((String)yamlMap.get("name"));
		List srvTansListY = (List)yamlMap.get("transMethod");
		if( srvTansListY != null ) {
			for(int intIdx=0;intIdx<srvTansListY.size();intIdx++ ) {
				Map srvPropInfoY = (Map)srvTansListY.get(intIdx);
				if( srvPropInfoY != null ) {
					TransmissionMethod trans = factory.createTransmissionMethod();
					trans.setKind((String)srvPropInfoY.get("kind"));
					serviceport.getTransMethods().add(trans);
				}
			}
		}
		//Doc
		Map portdocY = (Map)yamlMap.get("rtcDoc::doc");
		if( portdocY != null ) {
			DocServiceport docserviceport = factory.createDocServiceport();
			docserviceport.setDescription((String)portdocY.get("description"));
			docserviceport.setIfdescription((String)portdocY.get("ifdescription"));
			serviceport.setDoc(docserviceport);
		}

		//Ext
		if( yamlMap.get("rtcExt::position")!=null )
			serviceport.setPosition(Position.fromValue(((String)yamlMap.get("rtcExt::position")).toUpperCase()));
		serviceport.setComment((String)yamlMap.get("rtcExt::comment"));
		List srvPropertiesListY = (List)yamlMap.get("rtcExt::properties");
		if( srvPropertiesListY != null ) {
			for(int intIdx=0;intIdx<srvPropertiesListY.size();intIdx++ ) {
				Map srvPropInfoY = (Map)srvPropertiesListY.get(intIdx);
				if( srvPropInfoY != null ) {
					Property prop = factory.createProperty();
					prop.setName((String)srvPropInfoY.get("name"));
					prop.setValue((String)srvPropInfoY.get("value"));
					serviceport.getProperties().add(prop);
				}
			}
		}

		//Service Interface
		List interfacesY = (List)yamlMap.get("serviceInterface");
		if( interfacesY != null ) {
			for(int intIdx=0;intIdx<interfacesY.size();intIdx++ ) {
				Map interfaceY = (Map)interfacesY.get(intIdx);
				ServiceinterfaceExt serviceIF = factory.createServiceinterfaceExt();
				serviceIF.setName((String)interfaceY.get("name"));
				serviceIF.setDirection((String)interfaceY.get("direction"));
				serviceIF.setInstanceName((String)interfaceY.get("instanceName"));
				serviceIF.setIdlFile((String)interfaceY.get("idlFile"));
				serviceIF.setType((String)interfaceY.get("type"));
				//Doc
				Map ifDocY = (Map)interfaceY.get("rtcDoc::doc");
				if( ifDocY != null ) {
					DocServiceinterface docserviceIF = factory.createDocServiceinterface();
					docserviceIF.setDescription((String)ifDocY.get("description"));
					docserviceIF.setDocArgument((String)ifDocY.get("docArgument"));
					docserviceIF.setDocReturn((String)ifDocY.get("docReturn"));
					docserviceIF.setDocException((String)ifDocY.get("docException"));
					docserviceIF.setDocPreCondition((String)ifDocY.get("docPreCondition"));
					docserviceIF.setDocPostCondition((String)ifDocY.get("docPostCondition"));
					serviceIF.setDoc(docserviceIF);
				}
				//Ext
				serviceIF.setVariableName((String)interfaceY.get("rtcExt::variableName"));
				serviceIF.setComment((String)interfaceY.get("rtcExt::comment"));
				List ifPropertiesListY = (List)yamlMap.get("rtcExt::properties");
				if( ifPropertiesListY != null ) {
					for(int intIdxif=0;intIdxif<ifPropertiesListY.size();intIdxif++ ) {
						Map ifPropInfoY = (Map)ifPropertiesListY.get(intIdxif);
						if( ifPropInfoY != null ) {
							Property prop = factory.createProperty();
							prop.setName((String)ifPropInfoY.get("name"));
							prop.setValue((String)ifPropInfoY.get("value"));
							serviceIF.getProperties().add(prop);
						}
					}
				}
				serviceport.getServiceInterface().add(serviceIF);
			}
		}
		return serviceport;
	}
}
