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
            "version",           "1.0.1",
            "vendor",            "TA",
            "category",          "Manip",
            "activity_type",     "STATIC2",
            "max_instance",      "5",
            "language",          "Java",
            "lang_type",         "compile",
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
