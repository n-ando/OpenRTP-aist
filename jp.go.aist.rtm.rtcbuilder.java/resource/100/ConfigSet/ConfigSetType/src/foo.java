// -*- Java -*-
// <rtc-template block="description">
/*!
 * @file foo.java
 * @date $Date$
 *
 * $Id$
 */
// </rtc-template>
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;
/**
 * foo
 * <p> 
 * MDesc
 */
public class foo implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {
//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
            "implementation_id", "foo",
            "type_name",         "foo",
            "description",       "MDesc",
            "version",           "1.0.3",
            "vendor",            "TA2",
            "category",          "manip2",
            "activity_type",     "STATIC2",
            "max_instance",      "3",
            "language",          "Java",
            "lang_type",         "compile",
            // Configuration variables
            "conf.default.short_param", "0",
            "conf.default.int_param", "1",
            "conf.default.long_param", "14",
            "conf.default.float_param", "0.11",
            "conf.default.double_param", "4.11",
            "conf.default.str_param0", "hoge",
            "conf.default.str_param1", "dara",
            // Widget
            // Constraints
            "conf.__type__.short_param", "short",
            "conf.__type__.int_param", "int",
            "conf.__type__.long_param", "long",
            "conf.__type__.float_param", "float",
            "conf.__type__.double_param", "double",
            "conf.__type__.str_param0", "string",
            "conf.__type__.str_param1", "string",
    	    ""
            };
//  </rtc-template>
    public RTObject_impl createRtc(Manager mgr) {
        return new fooImpl(mgr);
    }
    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new foo(), new foo());
    }
}
