package ua.ibis.avalfile.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by conti on 22.11.2016.
 */
public class PropertiesValues {

    static final String propertyFileName = "config.properties";

    private static volatile Properties prop;

    private static void init() {

        prop = new Properties();

        try(InputStream in = PropertiesValues.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getInstance() {

        Properties properties = prop;
        if (properties == null) {
            synchronized (PropertiesValues.class) {
                properties = prop;
                if(properties == null) {
                    init();
                    properties = prop;
                }
            }
        }

        return properties;
    }

}
