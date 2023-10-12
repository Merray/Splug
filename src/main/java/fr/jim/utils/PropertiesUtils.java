package fr.jim.utils;

import fr.jim.config.ConstantesDb;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtils.class);

    public PropertiesUtils() {
        super();
    }

    public static Properties getAppPropsProperties() {

        Properties appProps = new Properties();

        try {
            appProps.load(new FileInputStream(ConstantesDb.PATH_TO_APPLICATION_PROPERTIES));
        } catch (IOException e) {
            System.out.println(e);
        }

        return appProps;
    }
}
