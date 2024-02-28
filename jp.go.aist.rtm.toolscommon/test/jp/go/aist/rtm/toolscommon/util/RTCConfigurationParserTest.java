package jp.go.aist.rtm.toolscommon.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

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

import org.junit.Test;
import org.openrtp.namespaces.rtc.version03.Configuration;
import org.openrtp.namespaces.rtc.version03.ConfigurationExt;
import org.openrtp.namespaces.rtc.version03.Property;
import org.openrtp.namespaces.rtc.version03.RtcProfile;


public class RTCConfigurationParserTest {
	@Test
	public void parse() throws Exception {
		RTCConfigurationParser parser = new RTCConfigurationParser();
		assertNull(parser.parse(null));
		assertNull(parser.parse(new RtcProfile()));
		List<ConfigurationSet> result = parser.parse(setupRtcProfile());
		assertEquals(3, result.size());
		verifyActiveset(parser.getActiveConfigurationSet());
		verifyConstraints(find(result, "__constraints__"));
		verifyWidgets(find(result, "__widget__"));
	}

	private void verifyWidgets(ConfigurationSet set) {
		assertEquals(4, set.getConfigurationData().size());
		verifyNameValue(set.getConfigurationData().get(0), "config2", "radio");		
		verifyNameValue(set.getConfigurationData().get(1), "int_param1", "slider.10");
		verifyNameValue(set.getConfigurationData().get(2), "vector_param0", "slider.10, spin, slider, spin");
		verifyNameValue(set.getConfigurationData().get(3), "hash_param1", "{key0:radio, key1:spin}");
	}

	private void verifyConstraints(ConfigurationSet set) {
		assertEquals(10, set.getConfigurationData().size());
		verifyNameValue(set.getConfigurationData().get(0), "config1", "x<100");
		verifyNameValue(set.getConfigurationData().get(1), "config2", "(up,down,left,right)");		
		verifyNameValue(set.getConfigurationData().get(2), "int_param0", "100");		
		verifyNameValue(set.getConfigurationData().get(3), "int_param1", "-20<x<20");		
		verifyNameValue(set.getConfigurationData().get(4), "vector_param0", "0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0, 0.0<x<10.0");		
		verifyNameValue(set.getConfigurationData().get(5), "hash_param1", "{key0:(ichi,one), key1:0.0<x<10.0}");
		verifyNameValue(set.getConfigurationData().get(6), "between", "0.0<=x<=10.0");
		verifyNameValue(set.getConfigurationData().get(7), "greaterThan", "x>10.0");
		verifyNameValue(set.getConfigurationData().get(8), "greaterThanEqual", "x>=10.0");
		verifyNameValue(set.getConfigurationData().get(9), "lessThanEqual", "x<=10.0");
	}

	private ConfigurationSet find(List<ConfigurationSet> result, String id) {
		for (ConfigurationSet set : result) {
			if (set.getId().equals(id)) return set;
		}
		return null;
	}

	private void verifyActiveset(ConfigurationSet activeConfigurationSet) {
		assertEquals(10, activeConfigurationSet.getConfigurationData().size());
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(0), "config1", "10");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(1), "config2", "Sample");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(2), "int_param0", "100");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(3), "int_param1", "0");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(4), "vector_param0", "1,2,3,4,5");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(5), "hash_param1", "{key0:one, key1:5.0}");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(6), "between", "5.0");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(7), "greaterThan", "15.0");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(8), "greaterThanEqual", "15.0");
		verifyNameValue(activeConfigurationSet.getConfigurationData().get(9), "lessThanEqual", "5.0");
	}

	private void verifyNameValue(Object object, String name, String value) {
		NameValue nv = (NameValue) object;
		assertEquals(name, nv.getName());
		assertEquals(value, nv.getValueAsString());
	}

	private RtcProfile setupRtcProfile() {
		RtcProfile profile = new RtcProfile();
		profile.getConfigurationSet().getConfiguration().add(setupConfig1());
		profile.getConfigurationSet().getConfiguration().add(setupConfig2());
		profile.getConfigurationSet().getConfiguration().add(setupConfig3());
		profile.getConfigurationSet().getConfiguration().add(setupConfig4());
		profile.getConfigurationSet().getConfiguration().add(setupConfig5());
		profile.getConfigurationSet().getConfiguration().add(setupConfig6());
		profile.getConfigurationSet().getConfiguration().add(setupConfig7());
		profile.getConfigurationSet().getConfiguration().add(setupConfig8());
		profile.getConfigurationSet().getConfiguration().add(setupConfig9());
		profile.getConfigurationSet().getConfiguration().add(setupConfig10());
		return profile;
	}

	private Configuration setupConfig1() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("config1");
		config.setDefaultValue("10");
		config.setConstraint(createLessThanConstraint("100"));
		return config;
	}
	private Configuration setupConfig2() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("config2");
		config.setDefaultValue("Sample");
		config.setConstraint(setupConstraint2());
		config.getProperties().add(createProperty("__widget__", "radio"));
		return config;
	}
	private Configuration setupConfig3() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("int_param0");
		config.setDefaultValue("100");
		config.setConstraint(createEqualConstraint("100"));
		return config;
	}
	private Configuration setupConfig4() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("int_param1");
		config.setDefaultValue("0");
		config.setConstraint(setupConstraint4());
		config.getProperties().add(createProperty("__widget__", "slider.10"));
		return config;
	}
	private Configuration setupConfig5() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("vector_param0");
		config.setDefaultValue("1,2,3,4,5");
		config.setConstraint(setupConstraint5());
		config.getProperties().add(createProperty("__widget__", "slider.10, spin, slider, spin"));
		return config;
	}
	private Configuration setupConfig6() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("hash_param1");
		config.setDefaultValue("{key0:one, key1:5.0}");
		config.setConstraint(setupConstraint6());
		config.getProperties().add(createProperty("__widget__", "{key0:radio, key1:spin}"));
		return config;
	}
	private Configuration setupConfig7() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("between");
		config.setDefaultValue("5.0");
		config.setConstraint(setupConstraint7());
		return config;
	}
	private Configuration setupConfig8() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("greaterThan");
		config.setDefaultValue("15.0");
		config.setConstraint(createGreaterThanConstraint("10.0"));
		return config;
	}	
	private Configuration setupConfig9() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("greaterThanEqual");
		config.setDefaultValue("15.0");
		config.setConstraint(createGreaterThanEqualConstraint("10.0"));
		return config;
	}	
	private Configuration setupConfig10() {
		ConfigurationExt config = new ConfigurationExt();
		config.setName("lessThanEqual");
		config.setDefaultValue("5.0");
		config.setConstraint(createLessThanEqualConstraint("10.0"));
		return config;
	}	

	private Property createProperty(String key, String value) {
		Property property = new Property();
		property.setName(key);
		property.setValue(value);
		return property;
	}

	private ConstraintType createLessThanConstraint(String value) {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		PropertyIsLessThan v2 = new PropertyIsLessThan();
		v2.setLiteral(value);
		v1.setPropertyIsLessThan(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}
	private ConstraintType createLessThanEqualConstraint(String value) {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		PropertyIsLessThanOrEqualTo v2 = new PropertyIsLessThanOrEqualTo();
		v2.setLiteral(value);
		v1.setPropertyIsLessThanOrEqualTo(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}
	private ConstraintType createGreaterThanConstraint(String value) {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		PropertyIsGreaterThan v2 = new PropertyIsGreaterThan();
		v2.setLiteral(value);
		v1.setPropertyIsGreaterThan(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}
	private ConstraintType createGreaterThanEqualConstraint(String value) {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		PropertyIsGreaterThanOrEqualTo v2 = new PropertyIsGreaterThanOrEqualTo();
		v2.setLiteral(value);
		v1.setPropertyIsGreaterThanOrEqualTo(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private ConstraintType setupConstraint2() {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		Or v2 = new Or();
		v2.getConstraint().add(createEqualConstraint("up"));
		v2.getConstraint().add(createEqualConstraint("down"));
		v2.getConstraint().add(createEqualConstraint("left"));
		v2.getConstraint().add(createEqualConstraint("right"));
		v1.setOr(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private ConstraintType setupConstraint4() {
		return createAndConstraint("-20", "20");
	}

	private ConstraintType setupConstraint5() {
		ConstraintType constraint = new ConstraintType();
		ConstraintListType v1 = new ConstraintListType();
		v1.getConstraint().add(createAndConstraint("0.0", "10.0"));
		v1.getConstraint().add(createAndConstraint("0.0", "10.0"));
		v1.getConstraint().add(createAndConstraint("0.0", "10.0"));
		v1.getConstraint().add(createAndConstraint("0.0", "10.0"));
		v1.getConstraint().add(createAndConstraint("0.0", "10.0"));
		constraint.setConstraintListType(v1);
		return constraint;
	}

	private ConstraintType setupConstraint6() {
		ConstraintType constraint = new ConstraintType();
		ConstraintHashType v1 = new ConstraintHashType();
		v1.getConstraint().add(setupConstraint6_1());
		v1.getConstraint().add(createAndConstraint("key1", "0.0", "10.0"));
		constraint.setConstraintHashType(v1);
		return constraint;
	}

	private ConstraintType setupConstraint6_1() {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		v1.setKey("key0");
		Or v2 = new Or();
		v2.getConstraint().add(createEqualConstraint("ichi"));
		v2.getConstraint().add(createEqualConstraint("one"));
		v1.setOr(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private ConstraintType setupConstraint7() {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		PropertyIsBetween v2 = new PropertyIsBetween();
		v2.setLowerBoundary("0.0");
		v2.setUpperBoundary("10.0");
		v1.setPropertyIsBetween(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private ConstraintType createAndConstraint(String greater, String less) {
		return createAndConstraint(null, greater, less);
	}
	private ConstraintType createAndConstraint(String key, String greater, String less) {
		ConstraintType constraint = new ConstraintType();
		ConstraintUnitType v1 = new ConstraintUnitType();
		v1.setKey(key);
		And v2 = new And();
		v2.getConstraint().add(createLessThanConstraint(less));
		v2.getConstraint().add(createGreaterThanConstraint(greater));
		v1.setAnd(v2);
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private ConstraintType createEqualConstraint(String value) {
		ConstraintType constraint = new ConstraintType();		
		ConstraintUnitType v1 = new ConstraintUnitType();
		v1.setPropertyIsEqualTo(createProperyIsEqualTo(value));	
		constraint.setConstraintUnitType(v1);
		return constraint;
	}

	private PropertyIsEqualTo createProperyIsEqualTo(String value) {
		PropertyIsEqualTo v2 = new PropertyIsEqualTo();
		v2.setLiteral(value);
		return v2;
	}
}
