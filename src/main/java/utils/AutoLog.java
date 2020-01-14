package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutoLog {
    //使用默认配置文件的配置信息，即 resources/log4j2.xml，如果找不到，使用Log4j本身的默认配置
    public static Logger log= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
}
