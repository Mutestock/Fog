package logic;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import static java.util.logging.Logger.GLOBAL_LOGGER_NAME;
import java.util.logging.SimpleFormatter;
import javax.xml.bind.annotation.XmlElementDecl;

/**
 *
 * @author Henning
 */
public class LoggerSetup {

//    private static FileHandler fh;
//    private static SimpleFormatter sm;
    private static final Logger logger = Logger.getLogger(GLOBAL_LOGGER_NAME);
//    private static ConsoleHandler ch;

    public static Logger logSetup() {
        try {
            File logDir = new File("./logs/");
            if (!(logDir.exists())) {
                logDir.mkdir();
            }
            LogManager.getLogManager().reset();
            logger.setLevel(Level.ALL);
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.SEVERE);
            logger.addHandler(ch);
            FileHandler fh = new FileHandler("logs/MyLogFile.log", true);
            SimpleFormatter sm = new SimpleFormatter();
            fh.setFormatter(sm);
            logger.addHandler(fh);
            return logger;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "LoggerSetup: IOException", ex);
        } catch (SecurityException ex) {
            logger.log(Level.SEVERE, "LoggerSetup: SecurityException", ex);
        }
        return null;
    }
}
