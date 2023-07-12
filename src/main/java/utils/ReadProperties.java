package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import static utils.HelperMethods.testEnvironments;

public class ReadProperties {
    private final Properties configProp = new Properties();

    private ReadProperties() {
        String current_environment = testEnvironments();
        try {
            InputStream in;
            if ("DEMO".equalsIgnoreCase(current_environment)) {
                in = new FileInputStream("applicationEnvDemo.properties");
            } else if ("DEV".equalsIgnoreCase(current_environment)) {
                in = new FileInputStream("applicationEnvDev.properties");
            } else {
                in = new FileInputStream("applicationEnvQA.properties");
            }
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class LazyHolder {
        private static final ReadProperties INSTANCE = new ReadProperties();
    }

    public static ReadProperties getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Double getPropertyDouble(String key) {
        return Double.valueOf(configProp.getProperty(key));
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }
}
