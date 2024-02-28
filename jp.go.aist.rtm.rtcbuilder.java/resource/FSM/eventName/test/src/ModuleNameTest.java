// -*- Java -*-
// <rtc-template block="description">
/*!
 * @file ModuleNameTest.java
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
/*!
 * @class ModuleNameTest
 * @brief ModuleDescription
 */
public class ModuleNameTest implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {
//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "ModuleNameTest",
    	    "type_name",         "ModuleNameTest",
    	    "description",       "ModuleDescription",
    	    "version",           "1.0.0",
    	    "vendor",            "VenderName",
    	    "category",          "Category",
    	    "activity_type",     "STATIC",
    	    "max_instance",      "1",
    	    "language",          "Java",
    	    "lang_type",         "compile",
    	    ""
            };
//  </rtc-template>
    public RTObject_impl createRtc(Manager mgr) {
        return new ModuleNameTestImpl(mgr);
    }
    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new ModuleNameTest(), new ModuleNameTest());
    }
}
