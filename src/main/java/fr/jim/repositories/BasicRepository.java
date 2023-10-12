package fr.jim.repositories;

import fr.jim.config.ConstantesDb;
import fr.jim.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BasicRepository {

    public Properties dbProperties = PropertiesUtils.getAppPropsProperties();

    public BasicRepository() {
        super();
    }

    public Connection connecter() {
        Connection connexion = null;
        try {
            Class.forName(ConstantesDb.DATABASE_DRIVER);

            connexion = DriverManager.getConnection(dbProperties.getProperty(ConstantesDb.DB_URL),
                    dbProperties.getProperty(ConstantesDb.DB_USER),
                    dbProperties.getProperty(ConstantesDb.DB_PASSWORD));


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        return connexion;
    }


}
