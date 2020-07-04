package com.moi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance;
    private static Properties properties;

    private PropertiesManager(){
        instance = this;
        try (FileInputStream fis = new FileInputStream(new File("enviroment.properties"))) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Can't load .properties file");
            e.printStackTrace();
        }
    }

    /** Метод для использования класса как синглтона */
    public static Properties getProperties() {
        if (instance == null) instance = new PropertiesManager();
        return properties;
    }
}
