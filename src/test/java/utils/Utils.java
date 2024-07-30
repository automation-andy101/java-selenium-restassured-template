package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getBaseUrl(String environment) throws IOException {
        String baseUrl = "";

        switch (environment.toLowerCase()) {
            case "dev":
                baseUrl = ReadPropertiesFile.readProperty("dev-todo-base-url");
                break;
            case "test":
                baseUrl = ReadPropertiesFile.readProperty("test-todo-base-url");
                break;
            default:
                throw new IllegalArgumentException("Unsupported environment: " + environment);
        }
        return baseUrl;
    }

    /**
     * Get tthe current date/time now
     *
     * @param format the format you want the date/time to be returned in e.g. "yyyy-MM-dd'T'HH:mm:ss"
     * @return the date/time
     */
    public static String getDateTimeNow(String format) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the desired format - "yyyy-MM-dd'T'HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Format the current date and time
        String formattedDateTime = now.format(formatter);

        // Print the formatted date and time
        System.out.println(formattedDateTime);

        return formattedDateTime;
    }
}