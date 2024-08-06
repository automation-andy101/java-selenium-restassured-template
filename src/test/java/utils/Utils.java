package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Utils {
    public static WebDriver driver;

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
     * Get the current date/time now
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

    public static void takeScreenshot(WebDriver driver) throws IOException {
        Date d = new Date();
        String filename = d.toString().replace(":", "").replace(" ", "");

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("screenshots/" + filename + ".png");
        FileUtils.copyFile(source, destination);
    }

    public static void takeScreenshot(WebDriver driver, String filename) throws IOException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        String timeStamp = sdf.format(d);
        timeStamp = timeStamp.replace(":", "").replace(" ", "").replace("/", "");

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("screenshots/" + filename + "_" + timeStamp + ".png");
        FileUtils.copyFile(source, destination);
    }

    public static void takeScreenshotUsingAShort(WebDriver driver, String fileName) throws IOException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        String timeStamp = sdf.format(d);
        timeStamp = timeStamp.replace(":", "").replace(" ", "").replace("/", "");

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver);

        File outputfile = new File("screenshots/" + fileName  + "_" + timeStamp + ".png");
        ImageIO.write(screenshot.getImage(), "png", outputfile);
    }
}