import RTC.TimedLong;
import RTC.TimedString;
/**
 * 
 */
public interface ModuleNameProtocol {

    void Event01_02(TimedLong data);

    void Event02_Final(TimedString data);

}

