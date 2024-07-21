package ui.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
    public static String readProperty(String key) throws IOException {
        FileReader fileReader = new FileReader("src/test/java/ui/utils/ReadPropertiesFile.java");
        Properties properties = new Properties();
        properties.load(fileReader);

        return properties.getProperty(key);
    }
}
