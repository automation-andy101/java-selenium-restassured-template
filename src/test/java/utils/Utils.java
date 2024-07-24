package utils;

import java.io.IOException;

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
}