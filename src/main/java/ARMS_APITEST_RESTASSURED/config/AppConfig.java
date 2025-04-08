package ARMS_APITEST_RESTASSURED.config;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
//    private static Properties properties = new Properties();
//
//    static {
//        try (InputStream input = Config.class.getCla
//        ssLoader().getResourceAsStream("config.properties")) {
//            if (input == null) {
//                System.out.println("Sorry, unable to find config.properties");
//                return;
//            }
//            properties.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = AppConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getResponseTimeThreshold() {
        return properties.getProperty("response.time.threshold");
    }
}
