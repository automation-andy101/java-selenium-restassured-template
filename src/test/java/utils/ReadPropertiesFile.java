package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
    public static String readProperty(String key) throws IOException {
        FileReader fileReader = new FileReader("src/test/resources/config.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        return properties.getProperty(key);
    }
}
