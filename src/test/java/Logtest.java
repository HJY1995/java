
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.AutoLog;

public class Logtest {
    public static void main(String[] args) {

            // TODO Auto-generated method stub
            //创建log4j的logger对象
            Logger logger = AutoLog.log;

            // Use the default configuration.


            // Set the logger level to Level.INFO
//		    logger.setLevel(Level.ERROR);

            // This request will be disabled since Level.DEBUG < Level.INFO.
        logger.trace("dasdasdasdasdsad");
            logger.debug("This is debug.");

            // These requests will be enabled.
            logger.info("This is an info.");
            logger.info("----------------------------ceshikaishi---------------------");
            logger.warn("This is a warning.");
            try {
                Integer.parseInt("ss");
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
//				e.printStackTrace();
                logger.error(e,e.fillInStackTrace());
            }

            logger.fatal("This is a fatal error.");




    }
}
